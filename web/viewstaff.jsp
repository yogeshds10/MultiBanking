<%-- 
    Document   : viewstaff
    Created on : Apr 14, 2013, 2:25:10 AM
    Author     : client
--%>

<%@page import="multibank.entity.Staff"%>
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
        <title>List of Staff - Simple Multi-Banking</title>
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
                <h2>List of Staff</h2>
                <% if (Branch.getBranchById(Integer.parseInt(request.getParameter("idbranch"))).getStaffList().isEmpty()) {%>
                No banks found. Try registering new banks.
                <%} else {%>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Is Manager</th>
                    </tr>
                    <%for (Staff s : Branch.getBranchById(Integer.parseInt(request.getParameter("idbranch"))).getStaffList()) {%>
                    <tr>
                        <td><%=s.getIduser()%></td>
                        <td><%=s.getName()%></td>
                        <td><%=s.getAddress()%></td>
                        <td><%=s.getMobile()%></td>
                        <td><%=s.getEmail()%></td>
                        <td><%=String.valueOf(s.isManager()).toUpperCase()%></td>
                    </tr>
                    <%}%>
                </table>
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
