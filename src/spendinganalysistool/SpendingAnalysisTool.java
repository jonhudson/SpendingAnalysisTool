
package spendinganalysistool;

import java.util.List;

public class SpendingAnalysisTool {

    private CsvReader csvReader;
        
    public static void main(String[] args) {       
        SpendingAnalysisTool tool = new SpendingAnalysisTool(args[0]);
    }    
    
    public SpendingAnalysisTool(String file) {
        csvReader = new CsvReader(file);
        List<String[]> csv = csvReader.parse();
        
        for (String[] strArray : csv) {
            for (String str : strArray) {
                System.out.println(str);
            }
        }
    }
    
}
