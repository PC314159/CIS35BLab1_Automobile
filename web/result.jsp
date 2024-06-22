<%@ page import="model.Automobile" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Automobile auto = (Automobile) request.getSession().getAttribute("auto");
//    auto.print();
    int totalPrice = auto.getTotalPrice();
%>
<html>
<head>
    <title>Configuration Summary</title>
</head>
<body>
<h1>Configuration Summary for <%= auto.getMake() %> <%= auto.getModel() %></h1>
<table border="1">
    <tr>
        <th>Option Set</th>
        <th>Selected Option</th>
        <th>Price</th>
    </tr>
    <tr>
        <th>BasePrice</th>
        <th></th>
        <th><%= auto.getBasePrice() %></th>
    </tr>
    <%
        for (String optionsName: auto.getOptionsNames()) {
            System.out.println(optionsName);
            String optionName = auto.getOptionChoiceName(optionsName);
            System.out.println(optionName);
            int optionPrice = auto.getOptionChoicePrice(optionsName);
            System.out.println(optionPrice);
    %>
    <tr>
        <td><%= optionsName %></td>
        <td><%= optionName %></td>
        <td><%= optionPrice %></td>
    </tr>
    <%
        }
    %>
</table>
<h2>Total Price: <%= totalPrice %></h2>
</body>
</html>
