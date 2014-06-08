

package spendinganalysistool;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import spendinganalysistool.Database;

public class MySQLDB implements Database {
    
    protected Connection con = null;
    
    public MySQLDB(String url, String user, String password) {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<String> getSources() throws SQLException {
        Statement stmt = null;
        String sql = "SELECT name FROM source";
        List<String> sources = new ArrayList<String>();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                sources.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
 
            if (stmt != null) {
                stmt.close();
            }
        }
        
        return sources;
    }
    
    @Override
    public List<String> getCategories() throws SQLException {
        Statement stmt = null;
        String sql = "SELECT name FROM category";
        List<String> categories = new ArrayList<String>();
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                categories.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
 
            if (stmt != null) {
                stmt.close();
            }
        }
        
        return categories;
    }
    
    @Override
    public void addSourceUnderCategory(String source, String category) throws SQLException {
        PreparedStatement stmt = null;
        String sql = "INSERT INTO source VALUES (null, ?, (SELECT id FROM category WHERE name = ?))";
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, source);
            stmt.setString(2, category);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { 
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    @Override
    public void addSourceAndCategory(String source, String category) throws SQLException {
        PreparedStatement insertSource = null;
        PreparedStatement insertCategory = null;                
        String sqlCategory = "INSERT INTO category VALUES (null, ?)";
        String sqlSource = "INSERT INTO source VALUES (null, ?, (SELECT id FROM category WHERE name = ?))";
        try {
            insertCategory = con.prepareStatement(sqlCategory);
            insertCategory.setString(1, category);
            insertCategory.executeUpdate();
            insertSource = con.prepareStatement(sqlSource);
            insertSource.setString(1, source);
            insertSource.setString(2, category);
            insertSource.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { 
            if (insertCategory != null) {
                insertCategory.close();
            }
            
            if (insertSource != null) {
                insertSource.close();
            }
        }
    }
    
    @Override
    public void closeConn() throws SQLException {
         if (con != null) {
            con.close();
        }
    }
}
