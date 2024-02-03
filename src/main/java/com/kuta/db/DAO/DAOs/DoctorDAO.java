package com.kuta.db.DAO.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kuta.app.objectTemplates.Doctor;
import com.kuta.db.DatabaseConnector;

/**
 * A DAO implementation for Doctor table.
 */
public class DoctorDAO implements DAO<Doctor>{

    /**
     * Gets a row by UUID
     */
    @Override
    public Doctor getByUUID(byte[] id) {
        String sql = "Select * from Doctor where id = ?;";

        try(Connection c = DatabaseConnector.getConnection()){
            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                ps.setBytes(1,id);

                try(ResultSet results = ps.executeQuery()){

                    while(results.next()){
                        Doctor doctor = new Doctor(
                            results.getBytes(1),
                            results.getString(2),
                            results.getString(3),
                            results.getDate(4)
                        );
                        return doctor;
                    }
                    return null;

                }
            }

        }catch(SQLException e){
            return null;
        }
    }

    @Override
    public List<Doctor> getAll() {
        String sql = "Select * from Doctor;";
        try(Connection c = DatabaseConnector.getConnection()){
            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                try(ResultSet results = ps.executeQuery()){
                    List<Doctor> doctors = new ArrayList<>();
                    while(results.next()){
                        Doctor doctor = new Doctor(
                            results.getBytes(1),
                            results.getString(2),
                            results.getString(3),
                            results.getDate(4)
                        );
                        doctors.add(doctor);
                    }
                    return doctors;

                }

            }
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
        try(Connection c = DatabaseConnector.getConnection()){

            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                ps.setString(1,t.getFname());
                ps.setString(2,t.getLname());
                ps.setDate(3,t.getStartedPractice());

                return ps.executeUpdate() > 0;
            }

        }catch(SQLException e){
            return false;
        }

    }

    @Override
    public boolean update(Doctor t) {
        String sql = 
        """
        UPDATE Doctor 
        set fname = ?, lname = ?, started_practice = ?
        where id = ?;
        """;

        try(Connection c = DatabaseConnector.getConnection()){

            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                ps.setString(1,t.getFname());
                ps.setString(2,t.getLname());
                ps.setDate(3,t.getStartedPractice());
                ps.setBytes(4,t.getId());

                return ps.executeUpdate() > 0;
            }

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(Doctor t) {
        String sql = 
        """
        Delete from Doctor 
        where id = ?;
        """;
        try(Connection c = DatabaseConnector.getConnection()){

            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                ps.setBytes(1,t.getId());

                return ps.executeUpdate() > 0;
        }

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    
}
