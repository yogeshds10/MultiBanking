<%-- 
    Document   : addbranch
    Created on : Apr 13, 2013, 11:27:25 PM
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
        <title>Add Branch - Simple Multi-Banking</title>
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
                <form action="BranchHandler" method="post" class="singleform">
                    <h2>Add Branch to Bank </h2>
                    <label>Bank Name : </label>
                    <input type="text" value="<%=(Bank.getBankById(Integer.parseInt(request.getParameter("idbank")))).getName()%>" readonly="readonly"/><br/><br/>
                    <label>Branch Name : </label>
                    <input type="text" name="name" id="name" value="${param.name}"/><br/><br/>
                    <label>Address : </label>
                    <textarea name="address" id="address" >${param.address}</textarea><br/><br/>
                    <label>Email : </label>
                    <input type="text" name="email" id="email" value="${param.email}"/><br/><br/>
                    <label>Phone : </label>
                    <input type="text" name="phone" id="phone" value="${param.phone}"/><br/><br/>
                    <div class="submit">
                        <input type="hidden" id="idbank" name="idbank" value="${param.idbank}"/>
                        <input type="submit" value="Add Branch"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
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

