package com.kuta.db.DAO.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HexFormat;
import java.util.List;

import com.kuta.db.DatabaseConnector;
import com.kuta.db.DAO.dbObjects.Doctor;
import com.kuta.db.DAO.dbObjects.Medication;
import com.kuta.db.DAO.dbObjects.Patient;
import com.kuta.db.DAO.dbObjects.Prescription;
import com.kuta.db.DAO.dbObjects.Prescription.Item;

/**
 * PrescriptionDAO
 */
public class PrescriptionDAO implements DAO<Prescription>{

    private DAO<Doctor> ddao;
    private DAO<Patient> pdao;
    private DAO<Medication> mdao;


    public PrescriptionDAO(DAO<Doctor> ddao, DAO<Patient> pdao, DAO<Medication> mdao) {
        this.ddao = ddao;
        this.pdao = pdao;
        this.mdao = mdao;
    }

    @Override
    public Prescription getByUUID(byte[] id) {
        String sql = 
        """
        Select patient_id,doctor_id,diagnosis,date_prescribed
        from ePrescription
        where id = ?;
        """;
        Prescription prescription = new Prescription();
        prescription.setId(id);
        try(Connection c = DatabaseConnector.getConnection()){


            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                ps.setBytes(1,id);

                try(ResultSet results = ps.executeQuery()){

                    byte[] patientId = null;
                    byte[] doctorId = null;
                    while(results.next()){
                        patientId = results.getBytes(1);
                        doctorId = results.getBytes(2);
                        prescription.setDiagnosis(results.getString(3));
                        prescription.setDatePrescribed(results.getDate(4));

                        Doctor doctor = ddao.getByUUID(doctorId);
                        prescription.setDoctor(doctor);
                        Patient patient = pdao.getByUUID(patientId);
                        prescription.setPatient(patient);

                        prescription = finishPrescription(c,prescription);
                    }
                }
            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        

        return prescription;
    }

    @Override
    public List<Prescription> getAll(){
        String sql = 
        """
        Select id,patient_id,doctor_id,diagnosis,date_prescribed
        from ePrescription;
        """;
        ArrayList<Prescription> prescriptions = new ArrayList<>();

        try(Connection c = DatabaseConnector.getConnection()){

            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){

                try(ResultSet results = ps.executeQuery()){

                    byte[] patientId = null;
                    byte[] doctorId = null;

                    while(results.next()){
                        Prescription prescription = new Prescription();
                        prescription.setId(results.getBytes(1));
                        patientId = results.getBytes(2);
                        doctorId = results.getBytes(3);
                        prescription.setDiagnosis(results.getString(4));
                        prescription.setDatePrescribed(results.getDate(5));

                        Doctor doctor = ddao.getByUUID(doctorId);
                        prescription.setDoctor(doctor);
                        Patient patient = pdao.getByUUID(patientId);
                        prescription.setPatient(patient);

                        prescription = finishPrescription(c,prescription);

                        prescriptions.add(prescription);
                    }
                }

            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

        return prescriptions;
    }

    @Override
    public boolean insert(Prescription t) {
        String sql = 
        """
        call newPrescription(?,?,?);
        """;
        try(Connection c = DatabaseConnector.getConnection()){
            c.setAutoCommit(false);

        
            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                ps.setBytes(1,t.getPatient().getId());
                ps.setBytes(2,t.getDoctor().getId());
                ps.setString(3,t.getDiagnosis());

                try(ResultSet rs = ps.executeQuery()){

                    while(rs.next()){
                        System.out.println("Returned id: "+HexFormat.of().formatHex(rs.getBytes(1)));
                        t.setId(rs.getBytes(1));

                    }
                }
            }catch(SQLException e){
                e.printStackTrace();
                c.rollback();
                return false;
            }

            sql = 
            """
            INSERT INTO Prescription_item(prescription_id,medication_id,amount,insurance_covered,description)
            values(?,?,?,?,?);
            """;
            for(Item item : t.getMeds()){
                System.out.println("Inserting item");

                try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                    ps.setBytes(1,t.getId());
                    ps.setBytes(2,item.getMed().getId());
                    ps.setInt(3,item.getAmount());
                    ps.setBoolean(4,item.isInsuranceCovered());
                    ps.setString(5,item.getDescription());

                    System.out.println(ps.executeUpdate() > 0);
                }catch(SQLException e){
                    e.printStackTrace();
                    c.rollback();
                    return false;
                }
            }

            c.commit();

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Prescription t) {
        throw new UnsupportedOperationException("Update is not available for table ePrescription. Prescriptions shouldn't be changed.");
    }

    @Override
    public boolean delete(Prescription t) {


        try(Connection c = DatabaseConnector.getConnection()){

            String sql = 
            """
            Delete from Prescription_item
            where prescription_id = ?;
            """;
            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                ps.setBytes(1,t.getId());

                ps.executeUpdate();
            }

            sql = 
            """
            Delete from ePrescription
            where id = ?;
            """;

            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                ps.setBytes(1,t.getId());

                return ps.executeUpdate() > 0;
            }


        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private Prescription finishPrescription(Connection c,Prescription p){

        String sql = 
        """
        Select amount,insurance_covered,description,Medication.id
        from Prescription_item
        inner join Medication on medication_id = Medication.id
        where prescription_id = ?;
        """;

        try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
            ps.setBytes(1,p.getId());

            ArrayList<Prescription.Item> items = new ArrayList<>();
            try(ResultSet results = ps.executeQuery()){

                while(results.next()){
                    Item item = new Item();
                    item.setAmount(results.getInt(1));
                    item.setInsuranceCovered(results.getBoolean(2));
                    item.setDescription(results.getString(3));

                    byte[] medicationId = results.getBytes(4);
                    Medication med = mdao.getByUUID(medicationId);
                    item.setMed(med);

                    items.add(item);

                }

                p.setMeds(items);
            }
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

        return p;
    }
    
}
