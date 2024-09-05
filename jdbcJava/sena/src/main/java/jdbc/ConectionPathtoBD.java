package jdbc;

/*
 * We make de module importation to support sql into Java
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USUARIO
 */
public class ConectionPathtoBD {
    private final String bd = "user_example";
    private final String url = "jdbc:mysql://localhost:3306/";
    private final String user = "root";
    private final String password = "";
    private final String driver = "com.mysql.cj.jdbc.Driver";

    private Connection cx;

    public ConectionPathtoBD() {

    }

    public Connection get_connection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(this.driver);
            cx = DriverManager.getConnection(this.url + this.bd, this.user, this.password);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Error in the connection");
            Logger.getLogger(ConectionPathtoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cx;
    }

    public void disconnect() {
        try {
            cx.close();
        } catch (SQLException e) {
        }
    }

    public String getPassword() {
        return this.password;
    }

    public String getDriver() {
        return this.driver;
    }
}
