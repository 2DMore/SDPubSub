import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MySQLQueries {
    public void insertInformation(int pasajeros, int id_unidad, double velocidad, double temperatura, double combustible, int ruta, String status, String coordenadas, String conductor){
        Connection conn = MySqlConnector.conectar();
        try{
            String query = "INSERT INTO `information`(`id`, `pasajeros`, `velocidad`, `temperatura`, `combustible`, `conductor`,`id_unidad`,`ruta`,`estatus`,`coordenadas`) VALUES (DEFAULT," + pasajeros + "," + velocidad + "," + temperatura + "," + combustible +","+conductor +"," + id_unidad +","+ruta+","+status+","+coordenadas+")";
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
                String conductor=rs.getString("conductor");
                int ruta=rs.getInt("ruta");
                String status = rs.getString("estatus");
                String coordenadas = rs.getString("coordenadas");
                messages.add(new Message("Pasajeros", "Actualmente hay " + pasajeros + " pasajeros en el VayVen ID" + id));
                messages.add(new Message("Velocidad", "La velocidad es de " + velocidad + " km/h en el VayVen ID" + id));
                messages.add(new Message("Temperatura", "La temperatura es de " + temperatura + " °C en el VayVen ID" + id));
                messages.add(new Message("Combustible", "El combustible en el VayVen ID " + id + " es de " + combustible + " litros"));
                messages.add(new Message("Conductor", "El ID del conductor del VayVen ID " + id + " es " + conductor));
                messages.add(new Message("Ruta", "La ruta del VayVen ID " + id + " es " + ruta));
                messages.add(new Message("Estatus", "El estatus del camion es: " + status));
                messages.add(new Message("Coordenadas", "La ubicacion del VayVen ID " + id + " es " + coordenadas ));
            }
                conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return messages;
    }

    public void updateInformation(int pasajeros, int id_unidad, double velocidad, double temperatura, double combustible,int ruta, String status, String coordenadas, String conductor){
        Connection conn = MySqlConnector.conectar();
        try{
            String query = "UPDATE information SET pasajeros= ?, velocidad= ?, temperatura= ?, combustible= ?, conductor= ?, ruta= ?, estatus= ?, coordenadas= ? WHERE id_unidad= ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, pasajeros);
            ps.setDouble(2, velocidad);
            ps.setDouble(3, temperatura);
            ps.setDouble(4, combustible);
            ps.setString(5, conductor);
            ps.setInt(6, ruta);
            ps.setString(7, status);
            ps.setString(8, coordenadas);
            ps.setInt(9, id_unidad);
            ps.execute();
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
