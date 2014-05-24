
package spendinganalysistool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;


/**
 * Reads csv and returns as list
 * 
 */
public class CsvReader {
    
    private BufferedReader br = null;
    private String line = "";
    private final String SPLIT_BY = ",";
    private List<String[]> transactions;
    private String file;
    
    public CsvReader(String file) {
        transactions = new ArrayList<String[]>();
        this.file = file;
    }
    
    /** 
     * Reads file and parses as csv, returns ArrayList
     * containing csv rows and fields
     */
    public List<String[]> parse() {
        try {
            br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                String[] row = line.split(SPLIT_BY);
                transactions.add(row);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return transactions;        
    }
}
