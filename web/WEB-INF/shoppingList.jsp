<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        
        <p>Hello, ${username}</p>
        <a href="ShoppingList?logout">Logout</a>
        <h2>List</h2>
        
        <form action="" method="post">
            <label>Add item: </label>
            <input type="test" name="item" value="">
            <input type="submit" value="Add">
        </form>
        
        
    </body>
</html>
