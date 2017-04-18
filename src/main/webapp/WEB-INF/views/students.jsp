<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page isELIgnored="false" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web-resources/css/students.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/web-resources/css/bootstrap.min.css" >
<script src="https://code.jquery.com/jquery-3.2.1.min.js" > </script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.min.js"></script> 
<script src="${pageContext.request.contextPath}/web-resources/js/bootstrap.min.js"></script> 
<script src="${pageContext.request.contextPath}/web-resources/js/cookieHandler.js"></script> 
<script src="${pageContext.request.contextPath}/web-resources/js/students.js"></script> 

<title>Students</title>
</head>
<body>
<div class='container-fluid'>
<h3>Students</h3>
<button class="btn btn-primary" id='newStudent'>Student Form</button> <button  class="btn btn-primary" id='viewStudent'>Student Table</button>
<div id='form'>
	<sf:form id="formActual" cssClass="form" method="POST" commandName="student">
	<div id="errors">
		<p class="success">${param.success}</p>
		<sf:errors path="firstName" cssClass="error" /><br>
		<sf:errors path="lastName" cssClass="error" /><br>
		<sf:errors path="school" cssClass="error" /><br>
		<sf:errors path="age" cssClass="error" /><br>
		<sf:errors path="gender" cssClass="error" /><br>
	</div>
		<sf:hidden path="id"/>
		<div class="form-group">
			<label >First Name: </label>
			<sf:input path="firstName" maxlength="16" cssClass="form-control" />
		</div>
		<div class="form-group">
			<label >Last Name: </label>
			 <sf:input path="lastName" maxlength="16" cssClass="form-control" />
		</div>
		<fieldset class="form-group">
    		<label>Gender:</label>
    		<div class="form-check">
		      <label class="form-check-label">
		        Male
		        <sf:radiobutton cssclass="form-check-input " path="gender" value="male"/>
		        Female
		        <sf:radiobutton cssclass="form-check-input" path="gender" value="female"/>
		      </label>
		    </div>
		</fieldset>
		<div class="form-group">
			<label >School: </label>
			<sf:input path="school" maxlength="16" cssClass="form-control" />
		</div>
		<div class="form-group">
			<label >Age: </label>
			<sf:input type="number" path="age" max="120" cssClass="form-control"/>
		</div>
		<button class='btn btn-success' type="submit" >Submit</button> <button id='reset' class='btn btn-danger' >Clear</button>
	</sf:form>
</div>
<div id='table'>
<p class="success">${param.feedback}</p>
	<table class="table">
		<thead>
			<tr>
			<th>Full Name</th>
			<th>School</th>
			<th>Age</th>
			<th>Gender</th>
			<th></th>
			<th></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${students}" var="student">
		<tr>
			<td>${student.getFirstName()} ${student.getLastName()}</td>
			<td>${student.getSchool()}</td>
			<td>${student.getAge()}</td>
			<td>${student.getGender()}</td>
			<td><a href="<c:url value='/edit/${student.getId()}' />"><button class='btn btn-primary'>Edit</button></a></td>
			<td><button class='btn btn-warning delete'   value='${student.getId()}' >Delete</button></td> 
		</tr>
		</c:forEach>
		</tbody>
	</table>
	
	</div>
  </div>
</body>
</html>