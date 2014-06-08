
package spendinganalysistool;

import spendinganalysistool.MySQLDB;
import spendinganalysistool.Database;
import spendinganalysistool.Config;
        
public class DBFactory {
    
    public static Database createDB(String typeOfDB) {
        if (typeOfDB.equals("mysql")) {
            return new MySQLDB(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        }
        
        return null;
    }
}
