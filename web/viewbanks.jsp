<%-- 
    Document   : viewbanks
    Created on : Apr 13, 2013, 4:37:16 PM
    Author     : client
--%>

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
        <title>List of Banks - Simple Multi-Banking</title>
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
                <h2>List of Banks</h2>
                <%if (request.getParameter("own")!=null && request.getParameter("own").equals("1")) {%>
                <% if (Bank.getList(((User) session.getAttribute("cuser"))).isEmpty()) {%>
                No banks found. Try registering new banks.
                <%} else {%>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Short Code</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Website</th>
                        <th>Branch Action(s)</th>
                    </tr>
                    <%for (Bank b : Bank.getList(((User) session.getAttribute("cuser")))) {%>
                    <tr>
                        <td><%=b.getIdBank()%></td>
                        <td><%=b.getName()%></td>
                        <td><%=b.getShortCode()%></td>
                        <td><%=b.getAddress()%></td>
                        <td><%=b.getPhone()%></td>
                        <td><%=b.getEmail()%></td>
                        <td><a href="<%=b.getWeb()%>" target="_blank"><%=b.getWeb()%></a></td>
                        <td>
                            <a href="addbranch.jsp?idbank=<%=b.getIdBank()%>">Add</a>
                            <a href="viewbranch.jsp?own=1&idbank=<%=b.getIdBank()%>">View</a>
                        </td>
                    </tr>
                    <%}%>
                </table>
                <%}%>                
                <%} else {%>
                <%if (Bank.getList().isEmpty()) {%>
                No banks found. Try registering new banks.
                <%} else {%>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Short Code</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>Email</th>
                        <th>Website</th>
                        <th>Approved</th>
                        <th>Branches</th>
                    </tr>
                    <%for (Bank b : Bank.getList()) {%>
                    <tr>
                        <td><%=b.getIdBank()%></td>
                        <td><%=b.getName()%></td>
                        <td><%=b.getShortCode()%></td>
                        <td><%=b.getAddress()%></td>
                        <td><%=b.getPhone()%></td>
                        <td><%=b.getEmail()%></td>
                        <td><a href="<%=b.getWeb()%>" target="_blank"><%=b.getWeb()%></a></td>
                        <td>
                            <%if (session.getAttribute("utype").equals("admin") && !b.isApproved()) {%>
                            <a href="ApproveBank?id=<%=b.getIdBank()%>">Approve</a>
                            <%} else {%>
                            <%=String.valueOf(b.isApproved()).toUpperCase()%>
                            <%}%>
                        </td>
                        <td><a href="viewbranch.jsp?idbank=<%=b.getIdBank()%>">View</a></td>
                    </tr>
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
