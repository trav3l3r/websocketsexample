package flannelware;

import flannelware.websocketdemo.WebSocketDemo;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        if (args.length < 1) {
            System.out.println("Please specify whether you wish to host or connect.");
            return;
        }
        if (args[0].equals("host")) {
            WebSocketDemo.startHost();
        } else {
            WebSocketDemo.startClient();
        }
    }
}
