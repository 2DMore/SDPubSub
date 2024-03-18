import java.util.List;
import java.util.Random;

public class VayVen {
    private int id;
    private int pasajeros;
    private double velocidad;
    private double temperatura;
    private double combustible;
    private String conductor;
    private int ruta;

    public VayVen(int id){
        this.id = id;
    }

    public void insertToDB(){
        MySQLQueries sql = new MySQLQueries();
        sql.insertInformation(this.pasajeros, this.id, this.velocidad, this.temperatura, this.combustible);
    }

    public List<Message> selectFromDB(int id){
        MySQLQueries sql = new MySQLQueries();
        return sql.selectInformation(id);
    }

    public void updateVayVen(){
        setPasajeros(getRandomInt(1, 50));
        setVelocidad(getRandomDouble(20, 70));
        setTemperatura(getRandomDouble(30, 40));
        setCombustible(getRandomDouble(10, 40));
        insertToDB();
        /* 
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("Pasajeros", "Actualmente hay " + pasajeros + " pasajeros en el VayVen ID" + id));
        messages.add(new Message("Velocidad", "La velocidad es de " + velocidad + " km/h en el VayVen ID" + id));
        messages.add(new Message("Temperatura", "La temperatura es de " + temperatura + " °C en el VayVen ID" + id));
        messages.add(new Message("Combustible", "El combustible en el VayVen ID" + id + " es de " + combustible + " litros"));
        */
    }
    public List<Message> getMessages(){
        return selectFromDB(this.id);
    }
/* 
    public void updateStatus() {
        Thread thread = new Thread(() -> {
            while (true) {
                updateVayVen(); 
                insertToDB();
                printInfo(); 
                try {
                    Thread.sleep(20000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Iniciar el hilo
        thread.start();
    }*/

    public int getRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public double getRandomDouble(double min, double max){
        Random random = new Random();
        double randomValue = min + (max - min) * random.nextDouble();
        randomValue = Math.round(randomValue * 100.0) / 100.0;
        return randomValue;
    }

    public void printInfo(){
        System.out.println("ID del vehículo: " + id);
        System.out.println("Número de pasajeros: " + pasajeros);
        System.out.println("Velocidad: " + velocidad);
        System.out.println("Temperatura: " + temperatura);
        System.out.println("Nivel de combustible: " + combustible);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(int pasajeros) {
        this.pasajeros = pasajeros;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public double getCombustible() {
        return combustible;
    }

    public void setCombustible(double combustible) {
        this.combustible = combustible;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public int getRuta() {
        return ruta;
    }

    public void setRuta(int ruta) {
        this.ruta = ruta;
    }
}
