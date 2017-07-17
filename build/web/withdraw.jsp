<%-- 
    Document   : withdraw
    Created on : Apr 14, 2013, 9:03:38 AM
    Author     : client
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="style/superfish.css" rel="stylesheet" type="text/css"/>
        <link href="style/main.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Withdraw - Simple Multi-Banking</title>
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
                <form action="WithdrawHandler" method="post" class="singleform">
                    <h2>Withdraw Cash</h2>
                    <label>Account  No. : </label>
                    <input type="text" id="idaccount" name="idaccount" value="${param.idaccount}" /><input type="button" value="Find Account"/><br/><br/>
                    <label>Branch Name : </label>
                    <input type="text" name="brname" id="brname" value="${param.brname}" readonly="readonly"/><br/><br/>
                    <label>Account Holder Name : </label>
                    <input type="text" name="uname" id="uname" value="${param.uname}" readonly="readonly"/><br/><br/>
                    <label>Acc. Type : </label>
                    <input type="text" name="type" id="type" value="${param.type}" readonly="readonly"/><br/><br/>
                    <label>Approved : </label>
                    <input type="text" name="approved" id="approved" value="${param.approved}" readonly="readonly"/><br/><br/>
                    <label>Available Amount : </label>
                    <input type="text" name="aamount" id="aamount" value="${param.aamount}" readonly="readonly"/><br/><br/>
                    <label>Amount : </label>
                    <input type="text" name="amount" id="amount" value="${param.amount}"/><br/><br/>
                    <div class="submit">
                        <input type="submit" value="Withdraw Amount"/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
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
                $("#idaccount").change(function(){
                    if(validateNumber(document.getElementById("idaccount"))) {
                        $.get("GetAccount", {idaccount:this.value}, function(data){
                            if(data['error'] ==0 ){
                                $("#brname").val(data['brname']);
                                $("#uname").val(data['uname']);
                                $("#type").val(data['type']);
                                $("#approved").val(data['approved']);
                                $("#aamount").val(data['aamount']);
                                $(".msg").text("");
                            }else{
                                $(".msg").text(data['msg']);
                                $("#brname").val("");
                                $("#uname").val("");
                                $("#type").val("");
                                $("#approved").val("");
                                $("#aamount").val("");
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