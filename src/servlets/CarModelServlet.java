package servlets;

import client.SelectCarOptions;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Automobile;

import java.io.*;
import java.net.Socket;

public class CarModelServlet extends HttpServlet {

    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket sock;
    private String strHost = "localhost";
    private int iPort = 4444;
    private SelectCarOptions clientProtocol;
    private String[] models = null;
    private Automobile auto = null;
    private String modelName = null;

    public CarModelServlet() {
    }

    public void establishConnection() {
        try {
            this.sock = new Socket(strHost, iPort);

            oos = new ObjectOutputStream(sock.getOutputStream());
            ois = new ObjectInputStream(sock.getInputStream());
            clientProtocol = new SelectCarOptions();

        }
        catch (IOException e) {
            System.err.println("Error obtaining I/O for connection to host ... ");
            System.exit(1);
        }
    }

    public void sendOutput(Object obj) {
        try {
            oos.writeObject(obj);
        }
        catch (IOException e) {
            System.err.println("Error in I/O stream while sending object to host ... ");
            System.exit(1);
        }
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        establishConnection();
        Object fromServer1 = null;
        try {
            if ((fromServer1 = ois.readObject()) != null) {
                System.out.println(fromServer1.toString());
                if (fromServer1 instanceof String[]) {
                    models = (String[]) fromServer1;
                    System.out.println("model updated");
                }
                else {
                    System.out.println("did not receive model");
                }
            }
        }
        catch (ClassNotFoundException e) {
            System.err.println("Error in downloaded object, object may be corrupted ... ");
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("Error in I/O stream ... ");
            System.exit(1);
        }
        sendOutput("2");

        Object fromServer = null;
        try {
            if ((fromServer = ois.readObject()) != null) {
                System.out.println(fromServer.toString());
                if (fromServer instanceof String[]) {
                    models = (String[]) fromServer;
                    System.out.println("model updated");
                }
                else {
                    System.out.println("did not receive model");
                }
            }
        }
        catch (ClassNotFoundException e) {
            System.err.println("Error in downloaded object, object may be corrupted ... ");
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("Error in I/O stream ... ");
            System.exit(1);
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Select a Car Model</h1>");
        out.println("<form method='post' action='cms'>");
        out.println("<select name='model'>");
        for (String model : models) {
            out.println("<option value='" + model + "'>" + model + "</option>");
        }

        out.println("</select>");
        out.println("<input type='submit' value='Select'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedModel = request.getParameter("model");
        modelName = selectedModel;
        sendOutput(modelName);

        Object fromServer = null;
        try {
            if ((fromServer = ois.readObject()) != null) {
                System.out.println(fromServer.toString());
                if (clientProtocol.isAutomobile(fromServer)) {
                    System.out.println("Automobile received");
                    auto = (Automobile) fromServer;
                }
                else {
                    System.out.println("did not receive auto");
                }
            }
        }
        catch (ClassNotFoundException e) {
            System.err.println("Error in downloaded object, object may be corrupted ... ");
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("Error in I/O stream ... ");
            System.exit(1);
        }
        sendOutput(null);

        try {
            ois.close();
            oos.close();

            sock.close();
        }
        catch (IOException e) {
            System.err.println("Error ending client connection ... ");
            System.exit(1);
        }
        request.getSession().setAttribute("auto", auto);
        response.sendRedirect("oss");
    }

    public String getModelName() {
        return modelName;
    }
}
