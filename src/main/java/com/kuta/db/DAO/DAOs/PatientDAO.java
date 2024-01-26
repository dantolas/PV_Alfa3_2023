package com.kuta.db.DAO.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kuta.db.DatabaseConnector;
import com.kuta.db.DAO.dbObjects.Patient;

/**
 * PatientDAO
 */
public class PatientDAO implements DAO<Patient>{

    @Override
    public Patient getByUUID(String id) {
        String sql = "Select * from Patient where id = ?;";
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setString(0,id);

            try(ResultSet results = ps.executeQuery()){

                while(results.next()){
                    Patient patient = new Patient();
                    patient.setId(results.getString(0));
                    patient.setFname(results.getString(1));
                    patient.setLname(results.getString(2));
                    patient.setBirthNumber(results.getString(3));
                    patient.setDof(results.getDate(4));
                    patient.setGender(results.getBoolean(5));
                    return patient;
                }
                return null;

            }

        }catch(SQLException e){
            return null;
        }

    }

    @Override
    public List<Patient> getAll() {
        String sql = "Select * from Patient;";
        try(
        ResultSet results = DatabaseConnector.prepSql(sql).executeQuery();
        ){
            List<Patient> patients = new ArrayList<>();
            while(results.next()){
                Patient patient= new Patient();
                patient.setId(results.getString(0));
                patient.setFname(results.getString(1));
                patient.setLname(results.getString(2));
                patient.setBirthNumber(results.getString(3));
                patient.setDof(results.getDate(4));
                patient.setGender(results.getBoolean(5));
                patients.add(patient);
            }
            return patients;

        }catch(SQLException e){
            return null;
        }

    }

    @Override
    public boolean insert(Patient t) {
        String sql = """
        Insert into Patient(fname,lname,birth_number,dof,gender) 
        values(?,?,?,?,?);
        """;
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setString(0,t.getFname());
            ps.setString(1,t.getLname());
            ps.setString(2,t.getBirthNumber());
            ps.setDate(3,t.getDof());
            ps.setBoolean(4,t.isGender());

            try(ResultSet results = ps.executeQuery()){

            }
        }catch(SQLException e){
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Patient t) {
        String sql = """
        UPDATE Patient
        set fname = ?,lname = ?, birth_number,dof= ?,gender =?
        where id = ?;
        """;
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setString(0,t.getFname());
            ps.setString(1,t.getLname());
            ps.setString(2,t.getBirthNumber());
            ps.setDate(3,t.getDof());
            ps.setBoolean(4,t.isGender());
            ps.setString(5,t.getId());

            try(ResultSet results = ps.executeQuery()){

            }
        }catch(SQLException e){
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Patient t) {
        String sql = """
        DELETE from Patient
        where id = ?;
        """;
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setString(0,t.getId());

            try(ResultSet results = ps.executeQuery()){

            }
        }catch(SQLException e){
            return false;
        }

        return true;

    }

    
}
