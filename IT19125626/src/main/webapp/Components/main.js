$(document).ready(function()
{
	$("#alertSuccess").hide(); 
	$("#alertError").hide();

});

//save button
$(document).on("click", "#btnSave", function(event) 
{

	$("#alertSuccess").text("");
    $("#alertSuccess").hide(); 
    $("#alertError").text(""); 
    $("#alertError").hide(); 
	
	// Form validation
	var status = validateItemForm(); 
	// If not valid------------------- 
	if (status != true) 
	{
		$("#alertError").text(status);
		$("#alertError").show();
		return;
		
		
	
	}
	// If valid
	
	var type = ($("#hidProjectIDSave").val() == "") ? "POST" : "PUT"; 
	
	$.ajax( 
	{
		url : "ProjectsAPI", 
		type : type, 
		data : $("#fromProject").serialize(), 
		dataType : "text", 
		complete : function(response, status) 
		{
			onItemSaveComplete(response.responseText, status); 
		
		}
	
	}); 
	 				
					 
	
});

function onItemSaveComplete(response, status) 
{
	if (status == "success") 
	{
		var resultSet = JSON.parse(response); 
		if (resultSet.status.trim() == "success") 
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			
			$("#divProjectsGrid").html(resultSet.data); 
		
		}
		else if (resultSet.status.trim() == "error") 
		{
			$("#alertError").text(resultSet.data); 
			$("#alertError").show(); 
		 
		}
		
	}
	else if (status == "error") 
	{
		$("#alertError").text("Error while saving."); 
		$("#alertError").show(); 
	
	}
	else
	{
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show(); 
	
	}
	$("#hidProjectIDSave").val(""); 
	$("#fromProject")[0].reset(); 
}


function validateItemForm()
{
	if ($("#itemcode").val().trim() == "") 
	{
	
		return "Insert project item Code."; 
	
	}
	if ($("#itemname").val().trim() == "") 
	{
	
		return "Insert project item Name."; 
	
	}
	if ($("#quantity").val().trim() == "") 
	{
	
		return "Insert project quantity."; 
	
	}
	if ($("#description").val().trim() == "") 
	{
	
		return "Insert project description."; 
	
	}
	if ($("#supemail").val().trim() == "") 
	{
	
		return "Insert project email."; 
	
	}
	
	return true; 



}

//Update
$(document).on("click", ".btnUpdate", function(event) 
{
	 	$("#hidProjectIDSave").val($(this).data("itemid")); 
	 	$("#itemcode").val($(this).closest("tr").find('td:eq(0)').text()); 
		$("#itemname").val($(this).closest("tr").find('td:eq(1)').text()); 
		$("#quantity").val($(this).closest("tr").find('td:eq(2)').text());
	    $("#description").val($(this).closest("tr").find('td:eq(3)').text());
		$("#supemail").val($(this).closest("tr").find('td:eq(4)').text());

	
});

//Delete
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax( 
	{
		url : "ProjectsAPI", 
		type : "DELETE",
		data : "itemid=" + $(this).data("itemid"), 
		dataType : "text", 
		complete : function(response, status) 
		{
			onItemDeleteComplete(response.responseText, status); 
		
		}
	
	
	
	});

});
function onItemDeleteComplete(response, status) 
{
	if (status == "success") 
	{
		var resultSet = JSON.parse(response); 
		if (resultSet.status.trim() == "success") 
		{
			$("#alertSuccess").text("Successfully deleted."); 
			$("#alertSuccess").show(); 
			$("#divProjectsGrid").html(resultSet.data); 
		
		}
		else if (resultSet.status.trim() == "error") 
		{
			$("#alertError").text(resultSet.data); 
			$("#alertError").show(); 
		
		}
		
	
	}
	else if (status == "error") 
	{
		$("#alertError").text("Error while deleting.");
		$("#alertError").show(); 
	}
	else
	{
		$("#alertError").text("Unknown error while deleting.."); 
		$("#alertError").show(); 
	
	}




}
