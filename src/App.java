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
                for (Message m : msgCamion1) {
                    if(m.getTopic() == "Pasajeros"){
                        pasajerosPublisher.publish(m, pubSubService);
                    }
                    if(m.getTopic() == "Velocidad"){
                        velocidadPublisher.publish(m, pubSubService);
                    }
                    if(m.getTopic() == "Temperatura"){
                        tempPublisher.publish(m, pubSubService);
                    }
                    if(m.getTopic() == "Combustible"){
                        combustiblePublisher.publish(m, pubSubService);
                    }
                }
                for (Message m : msgCamion2) {
                    if(m.getTopic() == "Pasajeros"){
                        pasajerosPublisher.publish(m, pubSubService);
                    }
                    if(m.getTopic() == "Velocidad"){
                        velocidadPublisher.publish(m, pubSubService);
                    }
                    if(m.getTopic() == "Temperatura"){
                        tempPublisher.publish(m, pubSubService);
                    }
                    if(m.getTopic() == "Combustible"){
                        combustiblePublisher.publish(m, pubSubService);
                    }
                }
                //Broadcast message to all subscribers. After broadcast, messageQueue will be empty in PubSubService
                pubSubService.broadcast();
                
                //Print messages of each subscriber to see which messages they got
                System.out.println("            Messages of Pasajeros Subscriber are: ");
                pasajerosSubscriber.printMessages();
                
                System.out.println("\n          Messages of Velocidad Subscriber are: ");
                velocidadSubscriber.printMessages();
                
                System.out.println("\n          Messages of All Data Subscriber are: ");
                allDataSubscriber.printMessages();
                msgCamion1.clear();
                msgCamion2.clear();

                allDataSubscriber.getSubscriberMessages().clear();
                pasajerosSubscriber.getSubscriberMessages().clear();
                velocidadSubscriber.getSubscriberMessages().clear();   
                tempSubscriber.getSubscriberMessages().clear();	
                combustibleSubscriber.getSubscriberMessages().clear();
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
    }
}
