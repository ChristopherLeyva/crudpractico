package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class conexionsql {
    private Connection conectar = null; 
    private String usuario = "admin";
    private String password = "inefaable790";
    private String bd = "dbleyva";
    private String ip = "dbleyva.c1qimomc2ixs.us-east-1.rds.amazonaws.com";
    private String puerto = "3306";

    private String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;

    // Método para conectar a la base de datos
    public Connection getConexion() {
        try {
            if (conectar == null || conectar.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conectar = DriverManager.getConnection(cadena, usuario, password);
                JOptionPane.showMessageDialog(null, "Se conectó a la base de datos");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se conectó a la base de datos, error: " + e.toString());
        }
        return conectar;
    }

}
