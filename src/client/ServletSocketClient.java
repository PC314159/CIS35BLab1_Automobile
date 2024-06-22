
package client;


import model.Automobile;
import servlets.CarModelServlet;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServletSocketClient extends Thread{

    ////////// PROPERTIES //////////

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private BufferedReader stdIn;
    private Socket sock;
    private String strHost;
    private int iPort;
    private CarModelOptionsIO clientFTP;
    private SelectCarOptions clientProtocol;
    private String[] models = null;
    private Automobile auto = null;
    private CarModelServlet cms;

    ////////// CONSTRUCTORS //////////
    public ServletSocketClient(String strHost, int iPort, BufferedReader reader, CarModelServlet cms) {
        this.strHost = strHost;
        this.iPort = iPort;
        this.stdIn = reader;
        this.cms = cms;
    }

    ////////// INSTANCE METHODS //////////
    public String[] getModels() {
        return models;
    }
    public Automobile getAuto() {
        return auto;
    }

    public void establishConnection() {
        try {
            this.sock = new Socket(strHost, iPort);

            out = new ObjectOutputStream(sock.getOutputStream());
            in = new ObjectInputStream(sock.getInputStream());

            clientFTP = new CarModelOptionsIO();
            clientProtocol = new SelectCarOptions();
        }
        catch (IOException e) {
            System.err.println("Error obtaining I/O for connection to host ... ");
            System.exit(1);
        }
    }

    public void handleConnection() {
        Object fromServer, toServer = null;

        try {
            while ((fromServer = in.readObject()) != null) {
                if (clientProtocol.isAutomobile(fromServer)) {
                    System.out.println("Automobile received");
                    auto = (Automobile) fromServer;
                    toServer = "0";
                }
                else if (fromServer instanceof String[]) {
                    models = (String[]) fromServer;
                    System.out.println("model of socket "+this+"updated");
                    int a = 0;
                    while (cms.getModelName()== null) {
                        if (a%100 == 0)  {
                            System.out.println("waiting for "+cms);
                        }
                    }
                    toServer = cms.getModelName();
                    sendOutput(toServer);
                }
                System.out.println("toserver"+toServer);
                if (toServer.equals("0")) {
                    break;
                }
            }

            in.close();

        }
        catch (ClassNotFoundException e) {
            System.err.println("Error in downloaded object, object may be corrupted ... ");
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("Error in I/O stream ... ");
            System.exit(1);
        }

    }

    public void sendOutput(Object obj) {
        try {
            out.writeObject(obj);
        }
        catch (IOException e) {
            System.err.println("Error in I/O stream while sending object to host ... ");
            System.exit(1);
        }
    }

    @Override
    public void run() {
        establishConnection();
        handleConnection();
        try {
            out.close();

            sock.close();
        }
        catch (IOException e) {
            System.err.println("Error ending client connection ... ");
            System.exit(1);
        }
    }

}
