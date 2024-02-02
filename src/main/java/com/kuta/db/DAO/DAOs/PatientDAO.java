package com.kuta.db.DAO.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kuta.app.dbObjects.InsuranceCompany;
import com.kuta.app.dbObjects.Patient;
import com.kuta.db.DatabaseConnector;

/**
 * PatientDAO
 */
public class PatientDAO implements DAO<Patient>{

    private DAO<InsuranceCompany> compDAO;

    public PatientDAO(DAO<InsuranceCompany> compDAO){
        this.compDAO = compDAO;
    }

    @Override
    public Patient getByUUID(byte[] id) {
        String sql = 
        """
        Select fname,lname,birth_number,dof,gender,insurance_number,insurance_company_id
        from Patient 
        where id = ?;
        """;
        Patient patient = new Patient();
        patient.setId(id);
        byte[] companyId = null;
        try(Connection c = DatabaseConnector.getConnection()){

            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                ps.setBytes(1,id);

                try(ResultSet results = ps.executeQuery()){

                    while(results.next()){
                        patient.setFname(results.getString(1));
                        patient.setLname(results.getString(2));
                        patient.setBirthNumber(results.getString(3));
                        patient.setDof(results.getDate(4));
                        patient.setGender(results.getBoolean(5));
                        patient.setInsuranceNumber(results.getString(6));

                        companyId = results.getBytes(7);
                        patient.setInsuranceCompany(compDAO.getByUUID(companyId));
                    }
                    return patient;
                }
            }
        }catch(SQLException e){
            return null;
        }
    }

    @Override
    public List<Patient> getAll() {
        String sql = 
        """
        Select id,fname,lname,birth_number,dof,gender,insurance_number,insurance_company_id
        from Patient;
        """;
        try(Connection c = DatabaseConnector.getConnection()){

            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){

                try(ResultSet results = ps.executeQuery()){
                    List<Patient> patients = new ArrayList<>();
                    while(results.next()){
                        Patient patient= new Patient();
                        patient.setId(results.getBytes(1));
                        patient.setFname(results.getString(2));
                        patient.setLname(results.getString(3));
                        patient.setBirthNumber(results.getString(4));
                        patient.setDof(results.getDate(5));
                        patient.setGender(results.getBoolean(6));
                        patient.setInsuranceNumber(results.getString(7));
                        patient.setInsuranceCompany(compDAO.getByUUID(results.getBytes(8)));


                        patients.add(patient);
                    }
                    return patients;
                }

        }

        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean insert(Patient t) {
        String sql = """
        Insert into Patient(fname,lname,birth_number,dof,gender,insurance_number,insurance_company_id) 
        values(?,?,?,?,?,?,?);
        """;
        try(Connection c = DatabaseConnector.getConnection()){

            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                ps.setString(1,t.getFname());
                ps.setString(2,t.getLname());
                ps.setString(3,t.getBirthNumber());
                ps.setDate(4,t.getDof());
                ps.setBoolean(5,t.isGender());
                ps.setString(6,t.getInsuranceNumber());
                ps.setBytes(7,t.getInsuranceId());
                return ps.executeUpdate() > 0;
            }
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Patient t) {
        String sql = 
        """
        UPDATE Patient
        SET fname = ?,lname = ?,birth_number = ?,dof = ?,gender = ?,insurance_number = ?,insurance_company_id = ?
        where id = ?;
        """;
        try(Connection c = DatabaseConnector.getConnection()){

            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                ps.setString(1,t.getFname());
                ps.setString(2,t.getLname());
                ps.setString(3,t.getBirthNumber());
                ps.setDate(4,t.getDof());
                ps.setBoolean(5,t.isGender());
                ps.setString(6,t.getInsuranceNumber());
                ps.setBytes(7,t.getInsuranceId());
                ps.setBytes(8,t.getId());

                return ps.executeUpdate() > 0;
            }
        }catch(SQLException e){
            return false;
        }

    }

    @Override
    public boolean delete(Patient t) {
        String sql = """
        DELETE from Patient
        where id = ?;
        """;
        try(Connection c = DatabaseConnector.getConnection()){

            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                ps.setBytes(1,t.getId());

                return ps.executeUpdate() > 0;
            }
        }catch(SQLException e){
            return false;
        }

    }

    
}
