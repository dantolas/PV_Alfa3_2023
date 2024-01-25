package com.kuta.db.DAO.DAOs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kuta.db.DatabaseConnector;
import com.kuta.db.DAO.dbObjects.Doctor;

/**
 * DoctorDAO
 */
public class DoctorDAO implements DAO<Doctor>{

    @Override
    public Doctor getById(String id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
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
                    
                );


            }

        }catch(SQLException e){

        }
    }

    @Override
    public boolean save(Doctor t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public boolean update(Doctor t, String[] params) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Doctor t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    
}
