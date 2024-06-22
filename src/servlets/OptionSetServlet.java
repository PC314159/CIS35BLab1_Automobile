package servlets;

import client.DefaultSocketClient;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Automobile;
import model.OptionSet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OptionSetServlet extends HttpServlet {

    private Automobile auto;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        auto = (Automobile) request.getSession().getAttribute("auto");
        System.out.println("auto"+auto);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Configure " + auto.getMake() + " " + auto.getModel() + "</h1>");
        out.println("<form method='post' action='oss'>");

        for (String optionsName : auto.getOptionsNames()) {
            out.println("<select name=" +optionsName+">");
            for (String optionName : auto.getOptionNames(optionsName)) {
                out.println("<option value='" + optionName + "'>" + optionName + "</option>");
            }
            out.println("</select>");
        }

        out.println("<input type='hidden' name='modelName' value='" + auto.getName() + "'>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("auto", auto);
        Set<String> parameterNames = request.getParameterMap().keySet();
        for (String parameterName : parameterNames) {
            System.out.println(parameterName);
        }
        for (String optionsName : auto.getOptionsNames()) {
            String searchName = optionsName;
            System.out.println("optionsName"+optionsName);
            if(optionsName.contains(" ")) {
                searchName = optionsName.substring(0, optionsName.indexOf(" "));
            }
            String choice = request.getParameter(searchName);
            System.out.println("choice"+choice);
            auto.setOptionChoice(optionsName, choice);
        }
        response.sendRedirect("result");
    }
}
