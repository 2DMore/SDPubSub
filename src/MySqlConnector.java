import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnector {
    public static Connection conectar() {
        // Parámetros de conexión a la base de datos
        String url = "jdbc:mysql://sql5.freesqldatabase.com:3306/sql5692165";
        String usuario = "sql5692165";
        String contraseña = "DpJDVXRqPt";

        Connection conexión = null;
        
        try {
            // Registrar el controlador JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            conexión = DriverManager.getConnection(url, usuario, contraseña);
            
            System.out.println("Conexión exitosa a la base de datos MySQL");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se pudo cargar el controlador JDBC");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: No se pudo establecer la conexión a la base de datos");
            e.printStackTrace();
        }
        
        return conexión;
    }

}
