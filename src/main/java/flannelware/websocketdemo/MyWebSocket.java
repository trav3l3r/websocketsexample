package flannelware.websocketdemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class MyWebSocket {
    
    private Socket socket;
    private ServerSocket serverSocket;

    public MyWebSocket () {}

    public void host(int port) {
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);
            socket = serverSocket.accept();
            socket.setSoTimeout(1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect(String hostAddress, int port) {
        try {
            socket = new Socket();
            socket.setReuseAddress(true);
            int timeoutMs = 3000;
            socket.connect(new InetSocketAddress(hostAddress, port), timeoutMs);
            socket.setSoTimeout(1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendSomething(String string) {
        try {
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(string.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveSomething() {
        try {
            InputStream inputStream = socket.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] readBuffer = new byte[2];
            int readBytes = 0;
            try {
                while ((readBytes = inputStream.read(readBuffer)) != 0) {
                    System.out.println("Read " + readBytes + " bytes from the socket input stream!");
                    baos.write(readBuffer);
                }
            } catch (SocketTimeoutException e) {
                // baos.write(readBuffer);
                System.out.println("No data left to read, proceeding.");
            }
            String string = new String(baos.toString());
            System.out.println("Final read from socket input stream is: [" + string + "]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
