<%-- 
    Document   : register
    Created on : Apr 13, 2013, 3:27:18 PM
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
        <title>Registration - Simple Multi-Banking</title>
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
                <div class="login">
                    <form action="RegistrationHandler" method="post">
                        <h2>Registration - <%if (request.getParameter("id").equals("1")) {%>
                            Bank</h2>
                        <label>Bank Name : </label>
                        <input type="text" name="name" id="name" value="${param.name}"/><br/><br/>
                        <label>Short Code : </label>
                        <input type="text" name="shortcode" id="shortcode" value="${param.shortcode}"/><br/><br/>
                        <label>Address : </label>
                        <textarea name="address" id="address">${param.address}</textarea><br/><br/>
                        <label>Phone : </label>
                        <input type="text" name="phone" id="phone" value="${param.phone}"/><br/><br/>
                        <label>Email : </label>
                        <input type="text" name="email" id="email" value="${param.email}"/><br/><br/>
                        <label>Website : </label>
                        <input type="text" name="web" id="web" value="${param.web}"/><br/><br/>
                        <label>Security Code : </label>
                        <input type="password" name="seccode" id="seccode" value="${param.seccode}"/><br/><br/>
                        <%} else if (request.getParameter("id").equals("2")) {%>
                        User</h2>
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
                        <label>Username : </label>
                        <input type="text" name="uname" id="uname" value="${param.uname}"/><br/><br/>
                        <label>Password : </label>
                        <input type="password" name="passwd" id="passwd" value="${param.passwd}"/><br/><br/>
                        <%} else {%>
                        Retail Customer</h2>
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
                        <label>Username : </label>
                        <input type="text" name="uname" id="uname" value="${param.uname}"/><br/><br/>
                        <label>Password : </label>
                        <input type="password" name="passwd" id="passwd" value="${param.passwd}"/><br/><br/>
                        <label>Bank : </label>
                        <select name="idbank" id="idbank" value="${param.idbank}">
                            <option value="-1">Select</option>
                            <%for (Bank b : Bank.getList()) {
                                    if (b.isApproved()) {%>
                            <option value="<%=b.getIdBank()%>"><%=b.getName()%> | <%=b.getShortCode()%></option>  
                            <%}%>
                            <%}%>                 
                        </select><br/><br/>
                        <%}%>
                        <div id="branchholder"  style="display: none;">
                            <label>Branch : </label>
                            <select id="idbranch" name="idbranch">
                            </select><br/><br/>
                            <label>Account Type : </label>
                            <select id="type" name="type">
                                <option value="-1">Select</option>
                                <option value="Savings">Savings</option>
                                <option value="Current">Current</option>
                            </select><br/><br/>
                        </div>
                        <div class="submit">
                            <input type="hidden" name="id" value="${param.id}"/>
                            <input type="submit" value="Register"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
                            <input type="reset"/>
                        </div>
                    </form>
                </div>
                <div class="register">
                    <h2>Register</h2>
                    <ul>
                        <li><a href="register.jsp?id=1">Bank</a></li>
                        <li><a href="register.jsp?id=2">User</a></li>
                        <li><a href="register.jsp?id=3">Retail Customer</a></li>
                    </ul>
                    <form action="LoginHandler" method="post">
                        <h2>Login</h2>
                        <label>Username : </label>
                        <input type="text" name="uname" id="uname" /><br/><br/>
                        <label>Password : </label>
                        <input type="password" name="passwd" id="passwd" /><br/><br/>
                        <div class="submit">
                            <input type="submit" value="Login"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
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
                $("#idbank").change(function(){
                    if(validateSelect(document.getElementById('idbank'))){
                        $.get("GetBranch", {'idbank':$(this).val()}, function(data){
                            $("#idbranch").html(data);
                            $("#branchholder").show();
                        },'text');
                    }else{
                        $(".msg").text("Kindly Select Bank to proceed");
                        $("#idbranch").children().remove();
                        $("#branchholder").hide();
                    }
                });
            });
        </script>
    </body>
</html>
