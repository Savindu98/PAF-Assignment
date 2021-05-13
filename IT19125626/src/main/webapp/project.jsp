<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import = "com.Project" import = "java.sql.DriverManager" import = "java.sql.Connection"
    pageEncoding="ISO-8859-1"%>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Projects Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script> 
<script src="Components/main.js"></script> 

	
</head>
<body>
	<div class = "container">
	<div class="row">
	
		<div class="col-6"> 
		
			<h1 >Project Management</h1> 
			
			<form id="fromProject" name="fromProject" method="post" action="project.jsp" >
			
			
				Item Code: <input id="itemcode" name="itemcode" type="text" class="form-control form-control-sm" ><br>
				Item Name: <input id="itemname" name="itemname" type="text" class="form-control form-control-sm" ><br>
				Quantity: <input id="quantity" name="quantity" type="text" class="form-control form-control-sm" ><br>
				Description: <input id="description" name="description" type="text" class="form-control form-control-sm" ><br>
				Supplier Email: <input id="supemail"  name="supemail" type="text" class="form-control form-control-sm" ><br><br>
				
			 <br>
			 
			 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
			 
			 <input type="hidden" id="hidProjectIDSave" name="hidProjectIDSave" value="">
			</form>
			
			<div id="alertSuccess" class="alert alert-success">
			
				
			</div> 
			 <div id="alertError" class="alert alert-danger"></div> 
			 
			 <br>
			 <div id="divProjectsGrid">
			 <%
			 	Project proobj = new Project();
			 	out.print(proobj.readItems());
			 
			 %>
			 </div>
			 
		</div>
	</div>
	</div>
	
	

</body>
</html>