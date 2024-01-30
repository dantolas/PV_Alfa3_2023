package com.kuta.db.DAO.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        Select patient_id,doctor_id,diagnosis
        from ePrescription
        where id = ?;
        """;
        Prescription prescription = new Prescription();
        prescription.setId(id);
        
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setBytes(1,id);

            try(ResultSet results = ps.executeQuery()){

                byte[] patientId = null;
                byte[] doctorId = null;
                while(results.next()){
                    prescription.setDiagnosis(results.getString(3));
                    patientId = results.getBytes(1);
                    doctorId = results.getBytes(2);

                    Doctor doctor = ddao.getByUUID(doctorId);
                    prescription.setDoctor(doctor);
                    Patient patient = pdao.getByUUID(patientId);
                    prescription.setPatient(patient);

                    prescription = finishPrescription(prescription);
                }
                System.out.println(ps.getConnection());

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
        Select id,patient_id,doctor_id,diagnosis
        from ePrescription;
        """;
        ArrayList<Prescription> prescriptions = new ArrayList<>();
        Prescription prescription = new Prescription();
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){

            try(ResultSet results = ps.executeQuery()){

                byte[] patientId = null;
                byte[] doctorId = null;

                while(results.next()){
                    prescription.setId(results.getBytes(1));
                    patientId = results.getBytes(2);
                    doctorId = results.getBytes(3);
                    prescription.setDiagnosis(results.getString(4));

                    Doctor doctor = ddao.getByUUID(doctorId);
                    prescription.setDoctor(doctor);
                    Patient patient = pdao.getByUUID(patientId);
                    prescription.setPatient(patient);

                    prescription = finishPrescription(prescription);

                    prescriptions.add(prescription);
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
        INSERT INTO ePrescription(patient_id,doctor_id,diagnosis)
        values(?,?,?);
        """;
        boolean querySuccess = false;
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setBytes(1,t.getPatient().getId());
            ps.setBytes(2,t.getDoctor().getId());
            ps.setString(3,t.getDiagnosis());


            querySuccess = ps.executeUpdate() > 0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

        if(!querySuccess) return querySuccess;

        sql = 
        """
        INSERT INTO Prescription_item(prescription_id,medication_id,amount,insurance_covered,description)
        values(?,?,?,?,?,?);
        """;
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setBytes(1,t.getPatient().getId());
            ps.setBytes(2,t.getDoctor().getId());
            ps.setString(3,t.getDiagnosis());

            querySuccess = ps.executeUpdate() > 0;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Prescription t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Prescription t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private Prescription finishPrescription(Prescription p){

        String sql = 
        """
        Select amount,insurance_covered,description,Medication.id
        from Prescription_item
        inner join Medication on medication_id = Medication.id
        where prescription_id = ?;
        """;

        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
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
            p.setMeds(null);
        }

        return p;
    }
    
}
