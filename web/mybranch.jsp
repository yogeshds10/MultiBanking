<%-- 
    Document   : mybranch
    Created on : Apr 14, 2013, 3:38:31 AM
    Author     : client
--%>

<%@page import="multibank.entity.Branch"%>
<%@page import="multibank.entity.Bank"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="style/superfish.css" rel="stylesheet" type="text/css"/>
        <link href="style/main.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Branch - Simple Multi-Banking</title>
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
                <form action="#" method="post" class="singleform">
                    <h2>My Branch</h2>
                    <label>Bank Name : </label>
                    <input type="text" value="<%=(Branch.getBranchById(Integer.parseInt(session.getAttribute("idbranch").toString())).getBank().getName())%>" readonly="readonly"/><br/><br/>
                    <label>Branch Name : </label>
                    <input type="text" name="name" id="name" value="<%=(Branch.getBranchById(Integer.parseInt(session.getAttribute("idbranch").toString())).getName())%>" readonly="readonly"/><br/><br/>
                    <label>Address : </label>
                    <textarea name="address" id="address" readonly="readonly"><%=(Branch.getBranchById(Integer.parseInt(session.getAttribute("idbranch").toString())).getAddress())%></textarea><br/><br/>
                    <label>Email : </label>
                    <input type="text" name="email" id="email" value="<%=(Branch.getBranchById(Integer.parseInt(session.getAttribute("idbranch").toString())).getEmail())%>" readonly="readonly"/><br/><br/>
                    <label>Phone : </label>
                    <input type="text" name="phone" id="phone" value="<%=(Branch.getBranchById(Integer.parseInt(session.getAttribute("idbranch").toString())).getPhone())%>" readonly="readonly"/><br/><br/>
                    <div class="submit" style="margin-left: 170px;">
                        <input type="hidden" id="idbranch" name="idbranch" value="${sessionScope.idbranch}" readonly="readonly"/>
                        <%if (session.getAttribute("utype").toString().equals("brc")) {%>
                        <input type="button" value="Add Staff"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                        <%}%>
                        <input type="button" value="View Staff"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                        <input type="button" value="Add Customer"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                        <input type="button" value="View Customer"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
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
                $("input[type=button]").click(function(){
                    if($(this).val() == 'Add Staff'){
                        window.location='addstaff.jsp?own=1&idbranch=${sessionScope.idbranch}';
                    }else if($(this).val() == 'Add Customer'){
                        window.location='addaccount.jsp';
                    }else if($(this).val() == 'View Customer'){
                        window.location='viewaccounts.jsp';
                    }else{
                        window.location='viewstaff.jsp?idbranch=${sessionScope.idbranch}';
                    }
                });
            });
        </script>
    </body>
</html>

