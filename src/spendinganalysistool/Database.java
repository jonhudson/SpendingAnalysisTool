

package spendinganalysistool;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Connect to database and perform queries
 */
public class Database {
    
    Connection con = null;
    
    public Database() {
        try {
            con = DriverManager.getConnection(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
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

            if (con != null) {
                con.close();
            }
        }
        
        return sources;
    }
    
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

            if (con != null) {
                con.close();
            }
        }
        
        return categories;
    }
    
    
    public String getCategory(String source) throws SQLException {
        
        PreparedStatement stmt = null;
        String sql = "SELECT c.name FROM source s JOIN category c ON s.category_id = c.id WHERE s.name = ?";
        String category = "";
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, source);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            category = rs.getString("name");
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
 
            if (stmt != null) {
                stmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
        
        return category;
    }
}
