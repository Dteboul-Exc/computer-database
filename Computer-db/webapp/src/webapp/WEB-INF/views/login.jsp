<!DOCTYPE html>
<html>
<head>
<title>Computer Database login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
</head>
<h2>login</h2>
     <c:if test="${param.error == 'true'}">
         <div>
          
                Failure to connect
                 
         </div>
    </c:if>
       
    <form name='f' action="${pageContext.request.contextPath}/j_spring_security_check" method='POST'>
      <table>
         <tr>
            <td>User:</td>
            <td><input type='text' name='username' value=''></td>
         </tr>
         <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
         </tr>
         <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
         </tr>
      </table>
  </form>
</html>