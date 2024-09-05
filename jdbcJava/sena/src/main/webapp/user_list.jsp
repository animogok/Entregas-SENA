<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CRUD EXAMPLE SENA</title>
</head>
<body>
<main>
    <h2>
        <br>
        <b>USER LISTS</b>
    </h2>

    <div class ="metodo_read">

        <form action="readusers_get" method = "get">
            <label for="choice"> SELECT WHAT YOU NEED </label>
                <select name="choice" id="choice">
                    <option value = "one">CHOOSE </option>
                    <option value ="users">USERS IN BD</option>
                    <option value ="user">USER</option>
                </select>
            <button type ="submit">SEND</button>
        </form>

        <c:choose>

            <c:when test="${choice == 'user'}">
                <form action = "readusers_get" method = "GET">
                    <input type = "number" name="id" placeholder="ID OF USER"> 
                    <input type = "radio" name="delete">DELETE ACCOUNT </input>
                    <input type = "radio" name="show">SHOW ACCOUNT </input>
                    <button type ="submit">SEND</button>
                </form>
            </c:when>

            <c:otherwise> 
                <ul>
                    <c:forEach var="User" items="${users}">
                        <li>
                            <p>${User.get_idUser()} ${User.getName()} ${User.getSurname()} ${User.getEmail()} ${User.getIdNumber()} ${User.getIdNumber()}</p>
                            <p>Total Usuarios</p>
                            <p><%= request.getAttribute("total") %></p>
                        </li>  
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
        <p>${id_user} ${name} ${lastname} ${User.getEmail()} ${email} ${id_numbe}</p>
        <p>${message}</p>
    </div>
</main>
</body>
</html>
