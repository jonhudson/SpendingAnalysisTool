
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
        List<String> sources = null;
        List<String> categories = null;
        
        try {
            sources = db.getSources(); 
            categories = db.getCategories(); 
        } catch (SQLException e) {
            e.printStackTrace();
        } 
                     
        for (String[] row : csvData) {
            boolean result = searchSource(row[0], sources);
            
            if (!result) {
                System.out.println("Please choose a category for " + row[0] + " by entering the "
                        + "number of a current category, or 0 to create a new category.");
                for (String cat : categories) {
                    System.out.println(cat);
                }
                
            }
        }                
    }
    
    private boolean searchSource(String source, List<String> sources) {
        for (String s : sources) {
            if (source.equals(s)) {
                return true;
            }
        }        
        
        return false;
    }
    
}
