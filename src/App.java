import java.util.List;

public class App {

    static List<Message> msgCamion1;
    static List<Message> msgCamion2;
    public static void main(String[] args) throws Exception {
        VayVen camion1 = new VayVen(1);
        VayVen camion2 = new VayVen(2);


        Publisher pasajerosPublisher = new PublisherImpl();
        Publisher velocidadPublisher = new PublisherImpl();
        Publisher tempPublisher = new PublisherImpl();
        Publisher combustiblePublisher = new PublisherImpl();
		
		
		Subscriber pasajerosSubscriber = new SubscriberImpl();
		Subscriber velocidadSubscriber = new SubscriberImpl();
		Subscriber tempSubscriber = new SubscriberImpl();
        Subscriber combustibleSubscriber = new SubscriberImpl();
        Subscriber allDataSubscriber = new SubscriberImpl();

        PubSubService pubSubService = new PubSubService();

        //Declare Subscribers 
        pasajerosSubscriber.addSubscriber("Pasajeros",pubSubService);
        velocidadSubscriber.addSubscriber("Velocidad",pubSubService);   
        tempSubscriber.addSubscriber("Temperatura", pubSubService);	
        combustibleSubscriber.addSubscriber("Combustible", pubSubService);

        allDataSubscriber.addSubscriber("Pasajeros", pubSubService);
        allDataSubscriber.addSubscriber("Velocidad", pubSubService);
        allDataSubscriber.addSubscriber("Temperatura", pubSubService);
        allDataSubscriber.addSubscriber("Combustible", pubSubService);


        Thread thread = new Thread(() -> {
            while (true) {
                //Actualizar la info y obtener los nuevos mensajes
                camion1.updateVayVen();
                camion2.updateVayVen();
                msgCamion1 = camion1.getMessages();
                msgCamion2 = camion2.getMessages();

                //publicar los mensajes
                    //camion1
                pasajerosPublisher.publish(msgCamion1.get(0), pubSubService);
                velocidadPublisher.publish(msgCamion1.get(1), pubSubService);
                tempPublisher.publish(msgCamion1.get(2), pubSubService);
                combustiblePublisher.publish(msgCamion1.get(3), pubSubService);

                pasajerosPublisher.publish(msgCamion2.get(0), pubSubService);
                velocidadPublisher.publish(msgCamion2.get(1), pubSubService);
                tempPublisher.publish(msgCamion2.get(2), pubSubService);
                combustiblePublisher.publish(msgCamion2.get(3), pubSubService);

                //Broadcast message to all subscribers. After broadcast, messageQueue will be empty in PubSubService
                pubSubService.broadcast();
                
                //Print messages of each subscriber to see which messages they got
                System.out.println("            Messages of Pasajeros Subscriber are: ");
                pasajerosSubscriber.printMessages();
                
                System.out.println("\n          Messages of Velocidad Subscriber are: ");
                velocidadSubscriber.printMessages();
                
                System.out.println("\n          Messages of All Data Subscriber are: ");
                allDataSubscriber.printMessages();
                // Esperar 20 segundos antes de la próxima ejecución
                try {
                    Thread.sleep(20000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Iniciar el hilo
        thread.start();
/* 
        camion1.updateVayVen();
        camion2.updateVayVen();
        Message msg1 = new Message("Pasajeros", "Actualmente hay " + camion1.getPasajeros() + " pasajeros en el VayVen ID" + camion1.getId());
		Message msg2 = new Message("Velocidad", "La velocidad es de " + camion1.getVelocidad() + " km/h en el VayVen ID" + camion1.getId());
        Message msg3 = new Message("Temperatura", "La temperatura es de " + camion1.getTemperatura() + " °C en el VayVen ID" + camion1.getId());
        Message msg4 = new Message("Combustible", "El combustible en el VayVen ID" + camion1.getId() + " es de " + camion1.getCombustible() + "litros");
		

		pasajerosPublisher.publish(msg1, pubSubService);
		velocidadPublisher.publish(msg2, pubSubService);
        tempPublisher.publish(msg3, pubSubService);
        combustiblePublisher.publish(msg4, pubSubService);
*/
       

        
        
    }
}
