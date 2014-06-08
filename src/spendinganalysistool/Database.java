

package spendinganalysistool;

import java.sql.SQLException;
import java.util.List;


public interface Database {
    
    public List<String> getSources() throws SQLException;
    
    public List<String> getCategories() throws SQLException;
    
    public void addSourceUnderCategory(String source, String category) throws SQLException;
    
    public void addSourceAndCategory(String source, String category) throws SQLException;
    
    public void closeConn() throws SQLException;
}
