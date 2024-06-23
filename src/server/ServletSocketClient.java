

package server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServletSocketClient extends Thread {

    ////////// PROPERTIES //////////

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket clientSocket;
    private BuildCarModelOptions protocol;

    ////////// CONSTRUCTORS //////////

    public ServletSocketClient(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    ////////// INSTANCE METHODS //////////

    public void handleConnection(Socket sock) {
        try {
            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());
        }
        catch (IOException e) {
            System.err.println("Error creating server object streams ... ");
            System.exit(1);
        }
        protocol = new BuildCarModelOptions(true);

        String menu = "\nEnter 1 to upload a new Automobile\n"
                + "Enter 2 to configure an Automobile\n"
                + "Enter 0 to terminate connection\n";
        try {
            do {
                sendOutput(menu);

                int request = Integer.parseInt(in.readObject().toString());
                if (request == 0)
                    break;

                sendOutput(protocol.setRequest(request));

                if (request >= 1 && request <= 2)
                    handleInput(request);

            } while (in.readObject() != null);

            in.close();
        }
        catch (IOException e) {
            System.err.println("Error handling client connection ... ");
            System.exit(1);
        }
        catch (ClassNotFoundException e) {
            System.err.println("Error in uploaded object, object may be corrupted ... ");
            System.exit(1);
        }
    }

    public void sendOutput(Object obj) {
        try {
            out.writeObject(obj);
        }
        catch (IOException e) {
            System.err.println("Error returning output to client ... ");
            System.exit(1);
        }
    }

    public void handleInput(int request) {
        Object fromClient = null;
        Object toClient = null;

        try {
            switch (request) {
                case 1: //Client request to build Automobile
                    fromClient = in.readObject();
                    toClient = protocol.processRequest(fromClient);
                    sendOutput(toClient);
                    break;

                case 2: //Client request to configure Automobile
                    fromClient = in.readObject().toString();
                    toClient = protocol.processRequest(fromClient);
                    sendOutput(toClient);
                    break;

                default: //Invalid requests
                    ;
            }
        }
        catch (ClassNotFoundException e) {
            System.err.println("Error in uploaded object, file may be corrupted ... ");
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("Error in retrieving object from client ... ");
            System.exit(1);
        }
    }

    @Override
    public void run() {
        handleConnection(clientSocket);

        //Close client output stream
        try {
            out.close();
        }
        catch (IOException e) {
            System.err.println("Error closing server output stream for client " + clientSocket.getInetAddress() + " ... ");
        }

        //Close client socket
        try {
            clientSocket.close();
        }
        catch (IOException e) {
            System.err.println("Error closing client socket " + clientSocket.getInetAddress() + " ... ");
        }
    }

}
