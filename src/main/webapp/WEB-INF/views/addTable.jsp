<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
  	$("#action").click(function(){
				var num = document.getElementById('rows').value;
				var theader = "<table border='1'>"
					+"<tr><th width=100>Name</th>"
					+ "<th width=100>Production Date</th>"+"<th width=100>Self Life</th>"
					+ "<th width=100>Picture</th>" + "<th width=100>Quantity</th>"
					+ "<th width=100>Price</th>" + "<th width=100>Sale Price</th>"
					+ "<th width=100>Description</th></tr>";
				var tbody="";
				
				for (i = 0; i < num; i++) {
					tbody += "<tr><td><input name='name' id='name' size='10' required='required' /></td>"
							+ "<td><input type='text' name='productionDate'size='10' id='productionDate' maxDate='selflifeDate' required='required'/></td>"
							+ "<td><input type='text' name='selflifeDate' id='selflife' minDate='productionDate' size='10' required='required'/></td>"
							+ "<td><input type='file' name='img' id='img'size='10' required='required'/></td>"
							+ "<td><input name='quantity' id='quantity' size='10' required='required' /></td>"
							+ "<td><input name='price' id='price' size='10' required='required' /></td>"
							+ "<td><input name='saleprice' id='saleprice' size='10' required='required' /></td>"
							+ "<td><input name='description' id='description' size='10' required='required' /></td></tr>";
				}
				var tfooter = "</table>";
				var sub = "<br /><input type='submit' name='btn_submit' id='btn_submit' value='Submit' />";

				var elem = document.getElementById("action");
				elem.parentNode.removeChild(elem);
		        $("#txtHint1").append(theader + tbody + tfooter+sub);
  
    $('input[name$="Date"]').datepicker({
        dateFormat: 'yyyy-MM-dd',
        beforeShow: function() {
            if ($(this).attr('maxDate')) {
                var dateItem = $('#' + $(this).attr('maxDate'));
                if (dateItem.val() !== "") {
                    $(this).datepicker('option', 'maxDate', dateItem.val());
                }
            }

            if ($(this).attr('minDate')) {
                var dateItem = $('#' + $(this).attr('minDate'));
                if (dateItem.val() !== "") {
                    $(this).datepicker('option', 'minDate', dateItem.val());
                }
            }
        }
    });});});
  </script>
</head>
<body>
	<form method='post' action='${contextPath}/food/addsuccess'
		enctype="multipart/form-data">
		<label>Enter: <input type="text" name="rows" id="rows" /></label><br />
		<input id="action" type="submit" value="submit" />		
        
		<div id="txtHint1">
			
		</div>

	</form>

</body>
</html>
