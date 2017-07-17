<%-- 
    Document   : addaccount
    Created on : Apr 14, 2013, 5:30:54 AM
    Author     : client
--%>

<%@page import="multibank.entity.User"%>
<%@page import="multibank.entity.Branch"%>
<%@page import="multibank.entity.Bank"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="style/superfish.css" rel="stylesheet" type="text/css"/>
        <link href="style/main.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add New Account - Simple Multi-Banking</title>
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
                <div class="login">
                    <form action="AccountHandler" method="post">
                        <h2>Retail Customer</h2>
                        <label>Full Name : </label>
                        <input type="text" name="name" id="name" value="${param.name}"/><br/><br/>
                        <label>Date of Birth : </label>
                        <input type="date" name="dob" id="dob" value="${param.dob}"/><br/><br/>
                        <label>Gender : </label>
                        <Select id="gender" name="gender" value="${param.gender}">
                            <option value="-1">Select</option>
                            <option value="true">Male</option>
                            <option value="false">Female</option>
                        </Select><br/><br/>
                        <label>Address : </label>
                        <textarea name="address" id="address" >${param.address}</textarea><br/><br/>
                        <label>Email : </label>
                        <input type="text" name="email" id="email" value="${param.email}"/><br/><br/>
                        <label>Mobile : </label>
                        <input type="text" name="mobile" id="mobile" value="${param.mobile}"/><br/><br/>
                        <%if (session.getAttribute("utype").toString().equals("bnk")) {%>
                        <label>Branch : </label>
                        <select name="idbranch" id="idbranch" value="${param.idbank}">
                            <option value="-1">Select</option>
                            <%for (Branch b : Bank.getBankById(Integer.parseInt(session.getAttribute("idbank").toString())).getBranchList()) {%>
                            <option value="<%=b.getIdbranch()%>"><%=b.getName()%></option>  
                            <%}%>
                        </select><br/><br/>
                        <%}%>
                        <label>Account Type : </label>
                        <select id="type" name="type">
                            <option value="-1">Select</option>
                            <option value="Savings">Savings</option>
                            <option value="Current">Current</option>
                        </select><br/><br/>
                        <div class="submit">
                            <input type="submit" value="Register"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                            <input type="reset"/>
                        </div>
                    </form>
                </div>
                <div class="register">
                    <form action="AccountHandler" method="post">
                        <h2>Have user id ?</h2>
                        <label>User ID : </label>
                        <input type="text" name="iduser" id="iduser" /><br/><br/>
                        <%if (session.getAttribute("utype").toString().equals("bnk")) {%>
                        <label>Branch : </label>
                        <select name="idbranch" id="idbranch" value="${param.idbank}">
                            <option value="-1">Select</option>
                            <%for (Branch b : Bank.getBankById(Integer.parseInt(session.getAttribute("idbank").toString())).getBranchList()) {%>
                            <option value="<%=b.getIdbranch()%>"><%=b.getName()%></option>  
                            <%}%>
                        </select><br/><br/>
                        <%}%>
                        <label>Account Type : </label>
                        <select id="type" name="type">
                            <option value="-1">Select</option>
                            <option value="Savings">Savings</option>
                            <option value="Current">Current</option>
                        </select><br/><br/>
                        <div class="submit">
                            <input type="submit" value="Add Account"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                            <input type="reset"/>
                        </div>
                    </form>
                </div>
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
                $("#idbranch").val('${param.idbranch}')
                $("#gender").val('${param.gender}')
            });
        </script>
    </body>
</html>
