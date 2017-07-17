<%-- 
    Document   : profile
    Created on : Apr 13, 2013, 6:39:39 PM
    Author     : client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="style/superfish.css" rel="stylesheet" type="text/css"/>
        <link href="style/main.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile - Simple Multi-Banking</title>
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
                <form action="ProfileHandler" method="post" class="singleform">
                    <h2>Profile - User</h2>
                    <label>Full Name : </label>
                    <input type="text" name="name" id="name" value="${sessionScope.cuser.name}"/><br/><br/>
                    <label>Date of Birth : </label>
                    <input type="date" name="dob" id="dob" value="${sessionScope.cuser.dob}"/><br/><br/>
                    <label>Gender : </label>
                    <Select id="gender" name="gender" value="${sessionScope.cuser.gender}">
                        <option value="-1">Select</option>
                        <option value="true">Male</option>
                        <option value="false">Female</option>
                    </Select><br/><br/>
                    <label>Address : </label>
                    <textarea name="address" id="address" >${sessionScope.cuser.address}</textarea><br/><br/>
                    <label>Email : </label>
                    <input type="text" name="email" id="email" value="${sessionScope.cuser.email}"/><br/><br/>
                    <label>Mobile : </label>
                    <input type="text" name="mobile" id="mobile" value="${sessionScope.cuser.mobile}"/><br/><br/>
                    <label>Username : </label>
                    <input type="text" name="uname" id="uname" value="${sessionScope.cuser.uname}"/><br/><br/>
                    <div class="submit">
                        <input type="submit" value="Update"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
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
