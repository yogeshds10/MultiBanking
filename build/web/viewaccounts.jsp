<%-- 
    Document   : viewaccount
    Created on : Apr 14, 2013, 6:33:05 AM
    Author     : client
--%>

<%@page import="multibank.entity.Customer"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="multibank.entity.Branch"%>
<%@page import="multibank.entity.User"%>
<%@page import="multibank.entity.Bank"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="style/superfish.css" rel="stylesheet" type="text/css"/>
        <link href="style/main.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Accounts - Simple Multi-Banking</title>
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
                <h2>List of Accounts</h2>
                <% if (session.getAttribute("utype").toString().equals("bnk")) {
                        ResultSet rs = Customer.getCustomerList(Bank.getBankById(Integer.parseInt(session.getAttribute("idbank").toString())));
                        if (rs == null) {%>
                No Customer found. Try adding new accounts.
                <% } else {%>
                <table border="1">
                    <tr>
                        <th>Acc. No.</th>
                        <th>Branch Name</th>
                        <th>Customer Name</th>
                        <th>Acc. Type</th>
                        <th>Mobile</th> 
                        <th>Email</th>
                        <th>Approved</th>
                    </tr>
                    <%while (rs.next()) {
                            if (request.getParameter("own") != null && request.getParameter("own").equals("1")) {
                                if (!rs.getBoolean("approved")) {%>
                    <tr>
                        <td><%=rs.getString(1)%></td>
                        <td><%=rs.getString(3)%></td>
                        <td><%=rs.getString(5)%></td>
                        <td><%=rs.getString(6)%></td>
                        <td><%=rs.getString(7)%></td>
                        <td><%=rs.getString(8)%></td>
                        <td>
                            <%if (rs.getBoolean(9)) {%>
                            <%=String.valueOf(rs.getBoolean(9)).toUpperCase()%>
                            <%} else {%>
                            <a href="ApproveCustomer?idaccount=<%=rs.getString(1)%>">Approve</a>
                            <%}%>
                        </td>
                    </tr>
                    <%}%>
                    <%} else {%>
                    <tr>
                        <td><%=rs.getString(1)%></td>
                        <td><%=rs.getString(3)%></td>
                        <td><%=rs.getString(5)%></td>
                        <td><%=rs.getString(6)%></td>
                        <td><%=rs.getString(7)%></td>
                        <td><%=rs.getString(8)%></td>
                        <td>
                            <%if (rs.getBoolean(9)) {%>
                            <%=String.valueOf(rs.getBoolean(9)).toUpperCase()%>
                            <%} else {%>
                            <a href="ApproveCustomer?idaccount=<%=rs.getString(1)%>">Approve</a>
                            <%}%>
                        </td>
                    </tr>
                    <%}%>
                    <%}%>
                </table>
                <%}%>
                <%} else {
                    ResultSet rs = Customer.getCustomerList(Branch.getBranchById(Integer.parseInt(session.getAttribute("idbranch").toString())));
                    if (rs == null) {%>
                No Customer found. Try adding new accounts.
                <% } else {%>
                <table border="1">
                    <tr>
                        <th>Acc. No.</th>
                        <th>Customer Name</th>
                        <th>Acc. Type</th>
                        <th>Mobile</th>
                        <th>Email</th>
                        <th>Approved</th>
                    </tr>
                    <%while (rs.next()) {
                            if (request.getParameter("own") != null && request.getParameter("own").equals("1")) {
                                if (!rs.getBoolean("approved")) {%>
                    <tr>
                        <td><%=rs.getString(1)%></td>
                        <td><%=rs.getString(3)%></td>
                        <td><%=rs.getString(4)%></td>
                        <td><%=rs.getString(5)%></td>
                        <td><%=rs.getString(6)%></td>
                        <td>
                            <%if (rs.getBoolean(7)) {%>
                            <%=String.valueOf(rs.getBoolean(7)).toUpperCase()%>
                            <%} else {%>
                            <a href="ApproveCustomer?idaccount=<%=rs.getString(1)%>">Approve</a>
                            <%}%>
                        </td>
                    </tr>
                    <%}%>
                    <%} else {%>
                    <tr>
                        <td><%=rs.getString(1)%></td>
                        <td><%=rs.getString(3)%></td>
                        <td><%=rs.getString(4)%></td>
                        <td><%=rs.getString(5)%></td>
                        <td><%=rs.getString(6)%></td>
                        <td>
                            <%if (rs.getBoolean(7)) {%>
                            <%=String.valueOf(rs.getBoolean(7)).toUpperCase()%>
                            <%} else {%>
                            <a href="ApproveCustomer?idaccount=<%=rs.getString(1)%>">Approve</a>
                            <%}%>
                        </td>
                    </tr>
                    <%}%>
                    <%}%>
                </table>
                <%}%>
                <%}%>
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
