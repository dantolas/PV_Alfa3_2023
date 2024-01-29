package com.kuta.db.DAO.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kuta.db.DatabaseConnector;
import com.kuta.db.DAO.dbObjects.InsuranceCompany;

/**
 * InsuranceDAO
 */
public class InsuranceDAO implements DAO<InsuranceCompany>{

    @Override
    public InsuranceCompany getByUUID(byte[] id) {
        String sql = "Select * from InsuranceCompany where id = ?;";
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setBytes(1,id);

            try(ResultSet results = ps.executeQuery()){

                while(results.next()){
                    InsuranceCompany comp= new InsuranceCompany(
                        id,
                        results.getString(2),
                        results.getString(3),
                        results.getString(4)
                    );
                    return comp;
                }
                return null;

            }

        }catch(SQLException e){
            return null;
        }

    }

    @Override
    public List<InsuranceCompany> getAll() {
        String sql = "Select * from InsuranceCompany;";
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){

            try(ResultSet results = ps.executeQuery()){

                List<InsuranceCompany> companies = new ArrayList<>();

                while(results.next()){
                    InsuranceCompany comp= new InsuranceCompany(
                        results.getBytes(1),
                        results.getString(2),
                        results.getString(3),
                        results.getString(4)
                    );
                    companies.add(comp);
                }
                return companies;

            }

        }catch(SQLException e){
            return null;
        }

    }

    @Override
    public boolean insert(InsuranceCompany t) {
        String sql = """
        Insert into InsuranceCompany(name,country_of_origin,shortcut)
        values(?,?,?);
        """;
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setString(1,t.getName());
            ps.setString(2,t.getCountryOfOrigin());
            ps.setString(3,t.getShortcut());

            return ps.executeUpdate() > 0;

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(InsuranceCompany t) {
        String sql = """
        UPDATE InsuranceCompany
        set name = ?, country_of_origin = ?, shortcut = ?
        where id = ?;
        """;
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setString(1,t.getName());
            ps.setString(2,t.getCountryOfOrigin());
            ps.setString(3,t.getShortcut());
            ps.setBytes(4,t.getId());

            return ps.executeUpdate() > 0;

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean delete(InsuranceCompany t) {
        String sql = """
        DELETE FROM InsuranceCompany
        where id = ?;
        """;
        try(PreparedStatement ps = DatabaseConnector.prepSql(sql)){
            ps.setBytes(1,t.getId());

            return ps.executeUpdate() > 0;

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    
}
