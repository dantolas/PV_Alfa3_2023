package com.kuta.db.DAO.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kuta.db.DatabaseConnector;
import com.kuta.db.DAO.dbObjects.Doctor;

/**
 * A DAO implementation for Doctor table.
 */
public class DoctorDAO implements DAO<Doctor>{

    /**
     * Gets a row by UUID
     */
    @Override
    public Doctor getByUUID(String id) {
        String sql = "Select * from Doctor where id = ?;";
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setString(0,id);

            try(ResultSet results = ps.executeQuery()){

                while(results.next()){
                    Doctor doctor = new Doctor(
                        results.getString(0),
                        results.getString(1),
                        results.getString(2),
                        results.getDate(3)
                    );
                    return doctor;
                }
                return null;

            }

        }catch(SQLException e){
            return null;
        }
    }

    @Override
    public List<Doctor> getAll() {
        String sql = "Select * from Doctor;";
        try(
        ResultSet results = DatabaseConnector.prepSql(sql).executeQuery();
        ){
            List<Doctor> doctors = new ArrayList<>();
            while(results.next()){
                Doctor doctor = new Doctor(
                    results.getString(0),
                    results.getString(1),
                    results.getString(2),
                    results.getDate(3)
                );
                doctors.add(doctor);
            }
            return doctors;

        }catch(SQLException e){
            return null;
        }
    }

    @Override
    public boolean insert(Doctor t) {
        String sql = """
        Insert into Doctor(fname,lname,started_practice) 
        values(?,?,?);
        """;
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setString(0,t.getFname());
            ps.setString(1,t.getLname());
            ps.setDate(2,t.getStartedPractice());


            try(ResultSet results = ps.executeQuery()){

            }
        }catch(SQLException e){
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Doctor t) {
        String sql = 
        """
        UPDATE Doctor 
        set fname = ?, set lname = ?, set started_practice = ?
        where id = ?;
        """;
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setString(0,t.getFname());
            ps.setString(1,t.getLname());
            ps.setDate(2,t.getStartedPractice());
            ps.setString(3,t.getId());


            try(ResultSet results = ps.executeQuery()){

            }

        }catch(SQLException e){
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(Doctor t) {
        String sql = 
        """
        Delete from Doctor 
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
