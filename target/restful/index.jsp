<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Home Page</title>
</head>
<body>

<h2>Users</h2>

<div align="center">
    <h1>Contact List</h1>
    <table border="1">
        <th>No</th>
        <th>Username</th>
        <th>Email</th>

        <c:forEach var="user" items="${userList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>