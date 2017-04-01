<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add laptop</title>


<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="<spring:url value="/resources/css/bootstrap-select.min.css"/>" type="text/css" />
	<link rel="stylesheet" href="<spring:url value="/resources/css/home.css"/>" type="text/css"/>
	<link rel="stylesheet" href="<spring:url value="/resources/css/global.css"/>" type="text/css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="<spring:url value="/resources/js/bootstrap-select.min.js"/>"></script>
<script src="<spring:url value="/resources/js/resource.js"/>"></script>

</head>
<body>



	<div class="container">
	
		<div class="row">
			<h2>Add laptop</h2>
		</div>
		
		<spring:url value="/admin/save" var="formUrl"/>
		
		<form:form action="${formUrl}" method="POST" modelAttribute="laptop" cssClass="col-md-8 col-md-offset-2">
			<form:errors path="*" cssClass="errorblock" element="div"/>
			<div class="row">
				
				<div class="form-group">
					<label for="laptop-ram">Ram</label>
					<form:input path="ram" cssClass="form-control" id="laptop-ram"/>
					<form:errors path="ram" cssClass="error"/>
				</div>

				<div class="form-group">
					<label for="laptop-cpu">CPU</label>
					<form:input path="cpu" cssClass="form-control"  id="laptop-cpu"/>
					<form:errors path="cpu" cssClass="error"/>
				</div>

				<div class="form-group">
					<label for="laptop-screen">Screen</label>
					<form:input path="screen" cssClass="form-control" id="laptop-screen"/>
					<form:errors path="screen" cssClass="error"/>
				</div>

				<div class="form-group">
					<form:input path="image" type="hidden" cssClass="form-control" id="laptop-image"/>
				</div>

				<div class="form-group">
					<label for="laptop-price">Price</label>
					<form:input path="price" cssClass="form-control" id="laptop-price"/>
					<form:errors path="price" cssClass="error"/>
				</div>

				<div class="form-group">
					<label for="laptop-status">Status</label>
					<form:select path="status" items="${status}" cssClass="selectpicker" id="laptop-status"/>
				</div>

				<div class="form-group">
					<label for="laptop-quantity">quantity</label>
					<form:input path="quantity" cssClass="form-control" id="laptop-quantity"/>
					<form:errors path="quantity" cssClass="error"/>
				</div>


				<div class="form-group">
					<label for="manufacturer-name">Manufacturer Name</label>
					<form:select path="manufacturer.name" items="${typeOptions}" cssClass="selectpicker" id="manufacturer-name"/>
				</div>
						
				<button type="submit" class="btn btn-default">Submit</button>

			</div>
		
		</form:form>
		<a href="<spring:url value="/admin/"/>">View all laptops</a>
	</div>
</body>
</html>