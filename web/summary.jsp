<%-- 
    Document   : summary
    Created on : Apr 14, 2013, 9:44:40 AM
    Author     : client
--%>

<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="style/superfish.css" rel="stylesheet" type="text/css"/>
        <link href="style/main.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Statment - Simple Multi-Banking</title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h1>Simple Multi - Banking</h1>
            </div>
            <div id="menu">
                <%try {
                        if (session.getAttribute("utype").toString().equals("admin")) {%>
                <%@include file="WEB-INF/jspf/amenu.jspf" %>
                <%      } else if (session.getAttribute("utype").toString().equals("user")) {%>
                <%@include file="WEB-INF/jspf/pending.jspf" %>
                <%      } else if (session.getAttribute("utype").toString().equals("bnk")) {%>
                <%@include file="WEB-INF/jspf/bankmenu.jspf" %>
                <%      } else if (session.getAttribute("utype").toString().equals("brc")) {%>
                <%@include file="WEB-INF/jspf/branchmenu.jspf" %>
                <%      } else if (session.getAttribute("utype").toString().equals("stf")) {%>
                <%@include file="WEB-INF/jspf/staffmenu.jspf" %>
                <%      } else if (session.getAttribute("utype").toString().equals("cus")) {%>
                <%@include file="WEB-INF/jspf/custmenu.jspf" %>
                <%      } else {%>
                <%@include file="WEB-INF/jspf/menu.jspf" %>
                <%      }
                } catch (Exception e) {%>
                <%@include file="WEB-INF/jspf/menu.jspf" %>    
                <%}%>
            </div>
            <div id="content">
                <p class="msg">${requestScope.msg}</p>
                <div class="welcome">Welcome ${sessionScope.name}${sessionScope.cuser.name}</div>
                <form action="StatementHandler" method="post" class="singleform">
                    <h2>Account Statment</h2>
                    <%if ((request.getParameter("hide") != null) && (request.getParameter("hide").equals("1"))) {%>
                    <%} else {%>
                    <label>Account No. : </label> 
                    <input type = "text" id = "idaccount" name = "idaccount" value = "${param.idaccount}" /> 
                    <input type = "submit" value = "Generate Statement" /><br/><br/>
                    <%}%>
                    <%if ((ResultSet) request.getAttribute("results") != null) {%>
                    <table border="1">
                        <tr>
                            <th>Transaction ID</th>
                            <th>Date & Time</th>
                            <th>Debit</th>
                            <th>Credit</th>
                            <th>Total</th>
                        </tr>
                        <%while (((ResultSet) request.getAttribute("results")).next()) {%>
                        <tr>
                            <td><%=((ResultSet) request.getAttribute("results")).getString("idtransaction")%></td>
                            <td><%=((ResultSet) request.getAttribute("results")).getString("logtime")%></td>
                            <td><%=((ResultSet) request.getAttribute("results")).getString("debit")%></td>
                            <td><%=((ResultSet) request.getAttribute("results")).getString("credit")%></td>
                            <td><%=((ResultSet) request.getAttribute("results")).getString("total")%></td>
                        </tr>  
                        <%}%>
                    </table>
                    <%} else {%>
                    <%}%>
                </form>
            </div>
            <div id="footer">&COPY; 2013 All Rights Reserved.</div>
        </div>
        <script src="script/jquery-1.7.1.min.js"></script>
        <script src="script/superfish.js"></script>
        <script src="script/hoverIntent.js"></script>
        <script src="script/validation.js"></script>
        <script>
            $(function(){
                $("ul.sf-menu").superfish(); 
            });
        </script>
    </body>
</html>