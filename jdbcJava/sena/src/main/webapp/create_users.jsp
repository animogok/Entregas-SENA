<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CREATE USERS</title>
</head>

<body>
    <div class = "postUser">
        <form action="create_post" method = "GET">
            <label for="choice"> SELECT WHAT YOU NEED </label>
                <select name="choice" id="choice">
                    <option value = "one">CHOOSE </option>
                    <option value ="create">CREATE USER</option>
                    <option value ="update">UPDATE USER</option>
                </select>
            <button type ="submit">SEND</button>
        </form>
    </div>

    <c:if test="${not empty choice}">
        <c:choose>
           <c:when test="${choice == 'create'}">
                <h1>CREATE USER SELECTED</h1>
                <form action="create_post" method="POST">
                    <input type="hidden" name="choice" value="create">
                    <input type="text" name="name" placeholder="name"><br>
                    <input type="text" name="surname" placeholder="surname"><br>
                    <input type="email" name="email" placeholder="email"><br>
                    <input type="text" name="id_number" placeholder="id number"><br>
                    <input type="password" name="password" placeholder="password"><br>
                    <button type="submit">SEND</button>
                </form>
            </c:when>

            <c:when test="${choice == 'update'}">
                <h1>UPDATE USER SELECTED</h1>
                <form action="create_post" method="GET">
                    <input type="hidden" name="choice" value="update">
                    <input type="number" name="id" placeholder="Enter user ID" required><br>
                    <button type="submit">Search</button>
                </form>
                <c:if test="${not empty id}">
                    <p>ID: ${id_user}, Name: ${name}, Surname: ${lastname}, Email: ${email}, ID Number: ${id_numbe}</p>
                    <form action="create_post" method="POST">
                        <input type="hidden" name="choice" value="update">
                        <input type="hidden" name="id" value="${id_user}">
                        <input type="text" name="name" value="${name}" placeholder="name"><br>
                        <input type="text" name="surname" value="${lastname}" placeholder="surname"><br>
                        <input type="email" name="email" value="${email}" placeholder="email"><br>
                        <input type="text" name="id_number" value="${id_numbe}" placeholder="id number"><br>
                        <input type="password" name="password" placeholder="password"><br>
                        <button type="submit">Update</button>
                    </form>
                </c:if>
            </c:when>
            <c:otherwise>
                <h1>PLEASE SELECT AN OPTION</h1>
            </c:otherwise>
        </c:choose>
    </c:if>
</body>
</html>
