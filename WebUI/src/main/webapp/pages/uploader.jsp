<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Image Upload</title>
    <link href="<c:url value='/pages/css/bootstrap.css' />"  rel="stylesheet" type="text/css"/>
    <link href="<c:url value='/pages/css/app.css' />" rel="stylesheet" type="text/css"/>
</head>

<body>

<div class="form-container">
    <h1>Image Upload</h1>
    <spring:url value="/admin/upload" var="formUrl"/>
    <form:form action="${formUrl}" method="POST" modelAttribute="fileBucket" enctype="multipart/form-data" class="form-horizontal">
        <div class="row">
            <div class="form-group">
                <form:input path="id" type="hidden" cssClass="form-control" id="fileBucket-id"/>
            </div>
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="file">Upload a file</label>
                <div class="col-md-7">
                    <form:input type="file" path="file" id="file" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="file" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-actions floatRight">
                <input type="submit" value="Upload" class="btn btn-primary btn-sm">
            </div>
        </div>
    </form:form>
    <spring:url value="/admin" var="adminUrl"/>
    <a href="${adminUrl}">Home</a>
</div>
</body>
</html>