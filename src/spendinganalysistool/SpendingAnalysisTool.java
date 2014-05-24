
package spendinganalysistool;

import java.util.List;
import java.sql.SQLException;

public class SpendingAnalysisTool {

    Database db = null;
    
    public static void main(String[] args) {          
        SpendingAnalysisTool tool = new SpendingAnalysisTool(args[0]);        
    }    
    
    public SpendingAnalysisTool(String file) {
        
        CsvReader csvReader = new CsvReader(file);
        List<String[]> csvData = csvReader.parse();
        db = new Database();
        run(csvData);
    }
    
    private void run(List<String[]> csvData) {
                      
        for (String[] row : csvData) {
            try {
                System.out.println(db.getCategory(row[0]));
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }           
        }
                
    }
    
}
