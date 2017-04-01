<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Index</title>

    <!-- Bootstrap core CSS -->
    <link href="<c:url value="/pages/css/bootstrap.css" />" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="<c:url value="/pages/css/jumbotron-narrow.css" />" rel="stylesheet">
    <link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <![endif]-->
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script type="text/javascript" >

        var Buy = function(index) {
            var jsonFormat = { "id" : laptop[index][0], "ram":laptop[index][1],
                "cpu":laptop[index][2],"image":laptop[index][3],
                "screen":laptop[index][4],"price":laptop[index][5],
                "status":laptop[index][6],"quantity":laptop[index][7],
                "manufacturer":{"id":laptop[index][8],"name":laptop[index][9]}};
            $.ajax({
                type: 'POST',
                url: '/user/buy',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(jsonFormat),
                dataType: 'json',
                cache: false,
                async: true,
                success: function (result) {
                    $('#status' + index).text(result.status);
                    $('#quantity' + index).html(result.quantity);
                }
            });
        };

    </script>
    <script>
        var laptop = [];
        <c:forEach items="${page}" var="item">
        var arr = [];
        arr.push("<c:out value="${item.id}" />");
        arr.push("<c:out value="${item.ram}" />");
        arr.push("<c:out value="${item.cpu}" />");
        arr.push("<c:out value="${item.image}" />");
        arr.push("<c:out value="${item.screen}" />");
        arr.push("<c:out value="${item.price}" />");
        arr.push("<c:out value="${item.status}" />");
        arr.push("<c:out value="${item.quantity}" />");
        arr.push("<c:out value="${item.manufacturer.id}" />");
        arr.push("<c:out value="${item.manufacturer.name}" />");
        laptop.push(arr);
        </c:forEach>
    </script>
</head>

<body>

<div class="container">
    <div class="jumbotron" style="margin-top: 20px;">
        <h2>Laptop shop</h2>
        <sec:authorize access="isAuthenticated()">
            <p align="right">Welcome, <sec:authentication property="principal.username"/>!
            <a class="btn btn-primary btn-sm" href="<c:url value="/logout" />" role="button">Выйти</a></p>
            <table>
                <tr>
                    <th>Image</th>
                    <th>Quantity</th>
                    <th>CPU</th>
                    <th>RAM</th>
                    <th>Screen</th>
                    <th>Status</th>
                    <th>Price</th>
                    <th>Manufacturer</th>
                    <th>Action</th>
                </tr>
                <c:forEach items="${page}" var="item" varStatus="status">
                    <tr>
                        <td>
                            <img width="200" src="<c:url value="${item.image}"/>">
                        </td>
                        <td><div id="quantity${status.index}">${item.quantity}</div></td>
                        <td>${item.cpu}</td>
                        <td>${item.ram}</td>
                        <td>${item.screen}</td>
                        <td><div id="status${status.index}">${item.status}</div></td>
                        <td>${item.price}</td>
                        <td>${item.manufacturer.name}</td>
                        <td>
                            <button type='button'  onclick='Buy(${status.index})'>Buy with ajax</button>
                            <form action="<c:url value="/user/buy/${item.id}" />" method="GET">
                                <input type="submit" value="Buy" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </sec:authorize>
    </div>

</div>
</body>
</html>