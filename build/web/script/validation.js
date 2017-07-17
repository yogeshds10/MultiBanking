/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function validateNull(x){
    //    alert("Validating for Null");
    if(x.value.length<=0||x.value==""||x.value==null){
        //        alert("Value required");
        //        x.select();
        return false;
    }
    return true;
}
function validateSelect(x){
    if(x.value==-1){
        return false;
    }
    return true;
}
function validateRadio(x){
    if(!x.checked){
        return false;
    }
    return true;
}
function validateNumber(x){
    var patt = /\D/g;
    if(!validateNull(x)){
        return false;
    }
    else if(patt.test(x.value)){
        //        alert("Number required");
        //        x.select();
        return false;
    }
    return true;
}
function validateEmail(field)
{
    with (field)
    {
        apos=value.indexOf("@");
        dotpos=value.lastIndexOf(".");
        if (apos<1||dotpos-apos<3) 
        {
            //            alert(alerttxt);
            return false;
        }
        else {
            return true;
        }
        }
}
function validate_name(x){
    var str=x.value;
    var pat=/\W/g;
    var pat1=/\d/g;
    var pat2=/_/g;
    if(!validateNull(x)){
        return false;
    }else if(pat.test(str))  {
        //        x.select();
        //        alert("Special Characters Found...!");
        return false;
    }else if(pat1.test(str))    {
        //        x.select();
        //        alert("Numbers Not Allowed...!")
        return false;
    }else if(pat2.test(str))    {
        //        x.select();
        //        alert("Underscores not allowed...!")
        return false;
    }
    else {
        return true;
    }
}

function validateMobile(x){
    if(!validateNumber(x)){
        return false;
    }else if(x.value.length<10){
        return false;
    }else{
        return true;
    }
}
function validateDate(x) {
    if(!validateNull(x)){
        return false;
    }
    var objDate, mSeconds;
    var a=x.value.split('-');
    if (a[0] < 1000 || a[0]> 3000) {
        return false;
    }
    a[1]=a[1]-1;
    mSeconds = new Date(a[0], a[1], a[2]).getTime();
    objDate = new Date();
    objDate.setTime(mSeconds);
    if(!(objDate.getFullYear()==a[0]&&objDate.getMonth()==a[1]&&objDate.getDate()==a[2])){
        return false;
    }
    return true;
}
function validateTime(x){
    var pat=/\d{1,2}:\d{1,2}/;
    if(pat.test(x.value)){
        var a=x.value.split(':');
        if(a[0]>23||a[0]<0){
            return false;
        }
        if(a[1]<0||a[1]>59){
            return false;
        }
        return true;
    }else{
        return false;
    }
}
function validatePassword(x){
    if(!validateNull(x)){
        return false;
    }else if(x.value.length < 6){
        return false;
    }
    return true;
}
function validateYear(x){
    if(!validateNumber(x)){
        return false;
    }else if(x.value.length!=4){
        return false;
    }
    return true;
}