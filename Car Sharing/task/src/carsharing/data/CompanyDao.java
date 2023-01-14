package carsharing.data;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao {
    private ConnectionFactory factory;

    public CompanyDao(String fileName) {
        this.factory = new ConnectionFactory(fileName);
    }

    public void dropCompanyTable() {
        try (
                Connection connection = this.factory.getConnection();
                Statement stmt = connection.createStatement();
        ) {
            String sql =  "DROP TABLE IF EXISTS COMPANY";
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }

    public void createCompanyTable() {
        try (
                Connection connection = this.factory.getConnection();
                Statement stmt = connection.createStatement();
        ) {
            String sql =  "CREATE TABLE IF NOT EXISTS COMPANY " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255) NOT NULL UNIQUE, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }

    public void addCompany(String companyName) {
        String insert = "INSERT INTO company (id, name) VALUES (?,?)";
        try (
                Connection connection = this.factory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(insert);
        ) {
            stmt.setInt(1, 0);
            stmt.setString(2, companyName);
            stmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }
    public ArrayList<String> getListOfAllCompanies() {
        try (
                Connection connection = this.factory.getConnection();
                Statement stmt = connection.createStatement();
        ) {
            String sql =  "SELECT * FROM COMPANY";
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<String> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                list.add(id + ". " + name);
            }
            return list;
        } catch (SQLException se) {
            se.printStackTrace();
            throw new RuntimeException(se);
        }
    }
}
