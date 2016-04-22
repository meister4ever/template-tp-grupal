package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private String line = "";

    private Socket socket = null;

    InputStream inputStream = null;
    DataInputStream dataInputStream = null;
    OutputStream outputStream = null;
    DataOutputStream dataOutputStream = null;

    private CommandHandlersChain currentCommandHandlersChain = null;

    /* 'observer' and 'chain of responsibility' mix */
    public void setCurrentCommandHandlersChain(CommandHandlersChain chc) {
        this.currentCommandHandlersChain = chc;
    }

    public String getLine() {
        return line;
    }

    public boolean isCurrentlyConnected() {
        return (socket != null);
    }

    protected void connectToServer(String address, int port) {
        try {

            InetAddress ipAddress = InetAddress.getByName(address);
            socket = new Socket(ipAddress,port);

            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();

            dataInputStream = new DataInputStream(inputStream);
            dataOutputStream = new DataOutputStream(outputStream);

        } catch (UnknownHostException e) {
            System.out.println("There is no such ip address!");
        } catch (IOException e) {
            System.out.println("Unable to connect to socket!");
        } catch (IllegalArgumentException e) {
            System.out.println("Port should be <= 65535!");
        }
    }

    protected void sendLine() {
        try {
            dataOutputStream.writeUTF(line);
            dataOutputStream.flush();
        } catch (IOException e) {
            System.out.println("Unable to send line!");
        }
    }

    protected void waitAnswer() {
        try {
            String sendByServer = dataInputStream.readUTF();
            System.out.println(sendByServer);
        } catch (IOException e) {
            System.out.println("Unable to read answer from server!");
        }
    }

    protected void disconnect() {
        try {
            this.socket.close();    // after closing a socket, you cannot reuse it to share other data
            this.socket = null;
        } catch (IOException e) {
            System.out.println("Unable to close socket!");
        }
    }

    protected void run() {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        while (currentCommandHandlersChain.useHandlers(line)) {
            try {
                line = keyboard.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}