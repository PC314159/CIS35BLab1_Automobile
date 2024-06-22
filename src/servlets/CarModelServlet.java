package servlets;

import client.DefaultSocketClient;
import client.ServletSocketClient;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.io.PrintWriter;

public class CarModelServlet extends HttpServlet {

    private ServletSocketClient socketClient;

    private String modelName = null;

    public CarModelServlet() {
    }
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        socketClient = new ServletSocketClient("localhost", 4444, request.getReader(), this);
        socketClient.start();
//        String[] models =
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Select a Car Model</h1>");
        out.println("<form method='post' action='cms'>");
        out.println("<select name='model'>");
        int a = 0;
        while (socketClient.getModels()== null){
            if (a%100 == 0)  {
                System.out.println("waiting for "+socketClient);
            }
//            System.out.println("Waiting for models");
        }
        System.out.println("found models");
        String[] models = socketClient.getModels();
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

        int a = 0;
        while (socketClient.getAuto()== null){
            if (a%100 == 0)  {
                System.out.println("waiting for "+socketClient);
            }
        }

        request.getSession().setAttribute("auto", socketClient.getAuto());
        response.sendRedirect("oss");
    }

    public String getModelName() {
        return modelName;
    }
}
