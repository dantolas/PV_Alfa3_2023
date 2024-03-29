package com.kuta.db.DAO.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kuta.app.objectTemplates.Medication;
import com.kuta.db.DatabaseConnector;

/**
 * MedicationDAO
 */
public class MedicationDAO implements DAO<Medication>{

    @Override
    public Medication getByUUID(byte[] id) {
        String sql = "Select * from Medication where id = ?;";
        try(Connection c = DatabaseConnector.getConnection()){

            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                ps.setBytes(1,id);

                try(ResultSet results = ps.executeQuery()){

                    while(results.next()){
                        Medication med= new Medication(
                            results.getBytes(1),
                            results.getString(2),
                            results.getString(3),
                            results.getString(4),
                            Medication.medType.valueOf(results.getString(5))
                        );
                        return med;
                    }
                    return null;

                }
            }

        }catch(SQLException e){
            return null;
        }
    }

    @Override
    public List<Medication> getAll() {
        String sql = "Select * from Medication order by name;";
        try(Connection c = DatabaseConnector.getConnection()){

            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){

                try(ResultSet results = ps.executeQuery()){
                    List<Medication> meds= new ArrayList<>();
                    while(results.next()){
                        Medication med= new Medication(
                            results.getBytes(1),
                            results.getString(2),
                            results.getString(3),
                            results.getString(4),
                            Medication.medType.valueOf(results.getString(5))
                        );
                        meds.add(med);
                    }
                    return meds;
                }

            }

        }catch(SQLException e){
            return null;
        }
    }

    @Override
    public boolean insert(Medication t) {
        String sql = """
        Insert into Medication(name,short_description,detailed_description,type)
        values(?,?,?,?);
        """;
        try(Connection c = DatabaseConnector.getConnection()){

            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                ps.setString(1,t.getName());
                ps.setString(2,t.getShortDescription());
                ps.setString(3,t.getLongDescription());
                ps.setString(4,t.getType().toString());

                return ps.executeUpdate() > 0;
            }

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public boolean update(Medication t) {
        String sql = """
        UPDATE Medication
        set name = ?, short_description = ?, detailed_description = ?, type = ?
        where id = ?;
        """;
        try(Connection c = DatabaseConnector.getConnection()){

            try(PreparedStatement ps = DatabaseConnector.prepSql(c,sql)){
                ps.setString(1,t.getName());
                ps.setString(2,t.getShortDescription());
                ps.setString(3,t.getLongDescription());
                ps.setString(4,t.getType().toString());
                ps.setBytes(5,t.getId());

                return ps.executeUpdate() > 0;

            }
        } catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Medication t) {
        String sql = """
        DELETE FROM Medication 
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
