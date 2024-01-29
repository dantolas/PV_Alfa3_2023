package com.kuta.db.DAO.DAOs;

import java.util.List;

import com.kuta.db.DAO.dbObjects.Doctor;
import com.kuta.db.DAO.dbObjects.Medication;
import com.kuta.db.DAO.dbObjects.Patient;
import com.kuta.db.DAO.dbObjects.Prescription;

/**
 * PrescriptionDAO
 */
public class PrescriptionDAO implements DAO<Prescription>{

    private DAO<Doctor> dDAO;
    private DAO<Patient> pDAO;
    private DAO<Medication> mDAO;

    @Override
    public Prescription getByUUID(byte[] id) {
        String sql = "select * from ePrescription";
        throw new UnsupportedOperationException("Unimplemented method 'getByUUID'");
    }

    @Override
    public List<Prescription> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public boolean insert(Prescription t) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
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

    
}
