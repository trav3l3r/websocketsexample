package flannelware.websocketdemo;

public class WebSocketDemo {
    
    public static void startHost() {
        MyWebSocket host = new MyWebSocket();
        host.host(7321);
        host.sendSomething("Hello from host!");
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("Sent, about to sleep and terminate.");
                Thread.sleep(5000);
                Thread.currentThread().interrupt();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    public static void startClient() {
        MyWebSocket client = new MyWebSocket();
        client.connect("localhost", 7321);
        client.receiveSomething();
    }

}
