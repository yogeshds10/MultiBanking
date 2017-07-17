<%-- 
    Document   : addstaff
    Created on : Apr 14, 2013, 1:02:37 AM
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
        <title>Add Staff - Simple Multi-Banking</title>
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
                <form action="StaffHandler" method="post" class="singleform">
                    <h2>Add Staff to Branch </h2>
                    <label>Bank Name : </label>
                    <input type="text" value="<%=(Bank.getBankById((Branch.getBranchById(Integer.parseInt(request.getParameter("idbranch")))).getIdbank())).getName()%>" readonly="readonly"/><br/><br/>
                    <label>Branch Name : </label>
                    <input type="text" value="<%=(Branch.getBranchById(Integer.parseInt(request.getParameter("idbranch")))).getName()%>" readonly="readonly"/><br/><br/>
                    <label>Staff ID : </label>
                    <input type="text" name="iduser" id="iduser" value="${param.iduser}"/><input type="button" value="Find User"/><br/><br/>
                    <label>Is Manager ?? : </label>
                    <select name="ismanager" id="ismanager" >
                        <option value="-1">Select</option>
                        <option value="true">Yes</option>
                        <option value="false">No</option>
                    </select><br/><br/>
                    <label>Full Name : </label>
                    <input type="text" name="name" id="name" value="${param.name}"  readonly="readonly"/><br/><br/>
                    <label>Date of Birth : </label>
                    <input type="date" name="dob" id="dob" value="${param.dob}"  readonly="readonly"/><br/><br/>
                    <label>Gender : </label>
                    <Select id="gender" name="gender" readonly="readonly">
                        <option value="-1">Select</option>
                        <option value="true">Male</option>
                        <option value="false">Female</option>
                    </Select><br/><br/>
                    <label>Address : </label>
                    <textarea name="address" id="address" readonly="readonly">${param.address}</textarea><br/><br/>
                    <label>Email : </label>
                    <input type="text" name="email" id="email" value="${param.email}" readonly="readonly"/><br/><br/>
                    <label>Mobile : </label>
                    <input type="text" name="mobile" id="mobile" value="${param.mobile}" readonly="readonly"/><br/><br/>
                    <div class="submit">
                        <input type="hidden" id="own" name="own" value="${param.own}"/>
                        <input type="hidden" id="idbranch" name="idbranch" value="${param.idbranch}"/>
                        <input type="submit" value="Add Staff"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
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
                if('${param.gender}' == '0'){
                    $("#gender").val("false");
                }else if('${param.gender}' == ''){
                    $("#gender").val("-1");
                }else{
                    $("#gender").val("true");
                }
                if('${param.ismanager}' == '0'){
                    $("#ismanager").val("false");
                }else if('${param.ismanager}' == ''){
                    $("#ismanager").val("-1");
                }else{
                    $("#ismanager").val("true");
                }
                $("#iduser").change(function(){
                    if(validateNumber(document.getElementById("iduser"))) {
                        $.get("GetUser", {iduser:this.value}, function(data){
                            if(data['error'] ==0 ){
                                $("#name").val(data['name']);
                                $("#dob").val(data['dob']);
                                if(data['gender']){
                                    $("#gender").val('true');
                                }else{
                                    $("#gender").val('false');
                                }
                                $("#address").val(data['address']);
                                $("#email").val(data['email']);
                                $("#mobile").val(data['mobile']);
                            }else{
                                $(".msg").text("Invalid Staff ID.");
                                $("#name").val("");
                                $("#dob").val("");
                                $("#gender").val("-1");
                                $("#ismanager").val("-1");
                                $("#address").val("");
                                $("#email").val("");
                                $("#mobile").val("");
                            }
                        }, 'json');
                    }else{
                        $("#iduser").removeClass().addClass("error");
                    }
                });
            });
        </script>
    </body>
</html>

