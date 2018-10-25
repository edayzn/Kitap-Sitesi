
package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static ConnectionManager instance = null;
    private static final String url = "jdbc:mysql://localhost:3306/book_shop?useUnicode=true&characterEncoding=UTF-8";    
    private static final String driverName = "com.mysql.jdbc.Driver";   
    private static final String username = "root";   
    private static final String password = "1234";
    private static Connection con;
    
    //nesne oluşmasını engelledik
    private  ConnectionManager(){
    }
    
    // Dışarıdan sınıfımızı çağıracağımız metodumuz.
    // synchronized anahtar kelimemiz ile metodun aynı anda çalışmasını engelledik.
    public synchronized static ConnectionManager getInstance()
    {
        // eğer daha önce örnek oluşturulmamış ise sınıfın tek örneğini oluştur
        if(instance == null)
        {
            instance  = new ConnectionManager();
        }
        return instance;
    }
    
    //Sınıfı clone sihirli metoduyla kopyalamaya çalıştığımızda CloneNotSupportedException ile kopyalanmasını engelliyoruz.
    @Override
    public Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException("Ben eşsiz bir parçayım");
    }

    
    public  Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                con = DriverManager.getConnection(url, username, password);
            } catch (SQLException ex) {
                System.out.println("Failed to create the database connection."+ex.getMessage()); 
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found "); 
        }
        return con;
    }
}

