package com.kuta.db.DAO.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kuta.db.DatabaseConnector;
import com.kuta.db.DAO.dbObjects.Medication;

/**
 * MedicationDAO
 */
public class MedicationDAO implements DAO<Medication>{

    @Override
    public Medication getByUUID(byte[] id) {
        String sql = "Select * from Medication where id = ?;";
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setBytes(1,id);

            try(ResultSet results = ps.executeQuery()){

                while(results.next()){
                    Medication med= new Medication(
                        results.getBytes(1),
                        results.getString(2),
                        results.getString(3),
                        results.getString(4)
                    );
                    return med;
                }
                return null;

            }

        }catch(SQLException e){
            return null;
        }
    }

    @Override
    public List<Medication> getAll() {
        String sql = "Select * from Medication;";
        try(
        ResultSet results = DatabaseConnector.prepSql(sql).executeQuery();
        ){
            List<Medication> meds= new ArrayList<>();
            while(results.next()){
                    Medication med= new Medication(
                        results.getBytes(1),
                        results.getString(2),
                        results.getString(3),
                        results.getString(4),
                        results.getString(5)
                    );
                meds.add(med);
            }
            return meds;

        }catch(SQLException e){
            return null;
        }
    }

    @Override
    public boolean insert(Medication t) {
        String sql = """
        Insert into Medication(name,short_descriptionn,detailed_description,type)
        values(?,?,?,?);
        """;
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setString(1,t.getName());
            ps.setString(2,t.getType());
            ps.setDate(3,t.getStartedPractice());

            return ps.executeUpdate() > 0;

        }catch(SQLException e){
            return false;
        }


    }

    @Override
    public boolean update(Medication t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Medication t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    
}
