
package spendinganalysistool;

import java.util.List;
import java.sql.SQLException;
import java.util.Scanner;

public class SpendingAnalysisTool {

    Database db = null;
    Scanner in = new Scanner(System.in);
    
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
                System.out.println("Please choose a category for " + row[0] + " or enter a new category.");
                for (String cat : categories) {
                    System.out.print(cat + " ");
                }
                System.out.println();
                
                String category;
                category = in.next();
                if (!searchCategory(category, categories)) {
                    try {
                        db.addSourceAndCategory(row[0], category);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        db.addSourceUnderCategory(row[0], category);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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
    
    private boolean searchCategory(String category, List<String> categories) {
        for (String c : categories) {
            if (category.equals(c)) {
                return true;
            }
        }        
        
        return false;
    }
    
}
