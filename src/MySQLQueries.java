import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQLQueries {
    public void insertInformation(int pasajeros, int id_unidad, double velocidad, double temperatura, double combustible, String status, String coordenadas){
        Connection conn = MySqlConnector.conectar();
        try{
            String query = "INSERT INTO `information`(`id`, `pasajeros`, `velocidad`, `temperatura`, `combustible`, `id_unidad`,`status`,`coordenadas`) VALUES (DEFAULT," + pasajeros + "," + velocidad + "," + temperatura + "," + combustible + "," + id_unidad +","+status+","+coordenadas+")";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public List<Message> selectInformation(int id){
        Connection conn = MySqlConnector.conectar();
        List<Message> messages = new ArrayList<>();
        messages.clear();
        try{
            String query = "SELECT * FROM `information` WHERE `id_unidad` =" + id +";";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.execute();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int pasajeros = rs.getInt("pasajeros");
                double velocidad = rs.getDouble("velocidad");
                double temperatura = rs.getDouble("temperatura");
                double combustible = rs.getDouble("combustible");
                messages.add(new Message("Pasajeros", "Actualmente hay " + pasajeros + " pasajeros en el VayVen ID" + id));
                messages.add(new Message("Velocidad", "La velocidad es de " + velocidad + " km/h en el VayVen ID" + id));
                messages.add(new Message("Temperatura", "La temperatura es de " + temperatura + " °C en el VayVen ID" + id));
                messages.add(new Message("Combustible", "El combustible en el VayVen ID" + id + " es de " + combustible + " litros"));
            }
                conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return messages;
    }
}
