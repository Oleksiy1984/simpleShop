<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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

        var Buy = function(id,index) {
            $.ajax({
                type: 'POST',
                url: '/admin/buy',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(id),
                dataType: 'json',
                cache: false,
                async: true,
                success: function (result) {
                    $('#status' + index).text(result.status);
                    $('#quantity' + index).html(result.quantity);
                }
            });
        };
        var Add = function(id,index1) {
            $.ajax({
                type: 'POST',
                url: '/admin/add',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(id),
                dataType: 'json',
                cache: false,
                async: true,
                success: function (result) {
                    $('#status' + index1).text(result.status);
                    $('#quantity' + index1).html(result.quantity);
                }
            });
        }
    </script>

</head>

<body>
<div class="container">

    <div class="jumbotron" style="margin-top: 20px;">

        <sec:authorize access="isAuthenticated()">
            <p align="right">Welcome, <sec:authentication property="principal.username"/>!
                <a class="btn btn-primary btn-sm" href="<c:url value="/logout" />" role="button">Выйти</a></p>
            <p align="right"><a href="<spring:url	value="/admin/add/"/>">Add Laptop</a></p>
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
                            <button type='button'  onclick='Buy(${item.id},${status.index})'>Buy with ajax</button>
                            <button type='button'  onclick='Add(${item.id},${status.index})'>Add with ajax</button>

                            <form action="<c:url value="/admin/buy/${item.id}" />" method="GET">
                                <input type="submit" value="Buy with reload page" />
                            </form>
                            <form action="<c:url value="/admin/add/${item.id}" />" method="GET">
                                <input type="submit" value="Add laptop with reload" />
                            </form>
                            <form action="<c:url value="/admin/edit/${item.id}" />" method="GET">
                                <input type="submit" value="Edit laptop" />
                            </form>
                            <form action="<c:url value="/admin/delete/${item.id}" />" method="GET">
                                <input type="submit" value="Delete laptop" />
                            </form>
                            <form action="<c:url value="/admin/upload/${item.id}" />" method="GET">
                                <input type="submit" value="Upload image" />
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