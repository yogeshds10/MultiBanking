<%-- 
    Document   : addbank
    Created on : Apr 13, 2013, 7:37:48 PM
    Author     : client
--%>

<%@page import="multibank.entity.Bank"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="style/superfish.css" rel="stylesheet" type="text/css"/>
        <link href="style/main.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Bank - Simple Multi-Banking</title>
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
                <form action="BankHandler" method="post" class="singleform">
                    <h2>Add Bank</h2>
                    <div class="ack">Note : You have to be bank manager to use this feature.</div>
                    <label>Select Bank : </label>
                    <select name="idbank" id="idbank" value="${param.idbank}">
                        <option value="-1">Select</option>
                        <%for (Bank b : Bank.getList()) {
                                if (b.getBankManager() == null) {%>
                        <option value="<%=b.getIdBank()%>"><%=b.getName()%> | <%=b.getShortCode()%></option>  
                        <%}%>
                        <%}%>                    
                    </select><br/><br/>
                    <label>Security Code : </label>
                    <input type="password" name="seccode" id="seccode"/><br/><br/>
                    <div class="submit">
                        <input type="submit" value="Add Bank"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                        <input type="reset"/>
                    </div><br/><br/>
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
                $("#gender").val('${sessionScope.cuser.gender}');
            });
        </script>
    </body>
</html>
