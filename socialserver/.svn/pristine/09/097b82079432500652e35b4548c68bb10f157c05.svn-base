<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./login.do" method="POST">

		<input type="text" name="phone" id="phone" />
		<p />
		<input type="password" name="password" /> <input type="submit"
			value="submit">

	</form>
<button onclick="ahead()">CheckPhone</button>

	<br />
	<br />
	<br />
	<input type="text" id="back" />
	<button onclick="findback()">Go</button>
	<br/>
	<input type="text" id="vcode" />
	<input type="text" id="npassword" />
	<br/>
	<button onclick="updater()">update</button>
	
	<script type="text/javascript">
	
		function updater(){
			var request;
			request = new XMLHttpRequest();

			request.open("POST", "./updatepassword.do", true);
			request.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			request.onreadystatechange = function() {
				if (request.readyState == 4 && request.status == 200) {
					alert(request.responseText);
				}
			}
			request.send("npassword=" + document.getElementById("npassword").value+"&vcode="+document.getElementById("vcode").value);
		}
	
		function ahead() {
			/* alert(document.getElementById("phone").value);
			 */var request;
			request = new XMLHttpRequest();

			request.open("POST", "./find.do", true);
			request.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			request.onreadystatechange = function() {
				if (request.readyState == 4 && request.status == 200) {
					alert(request.responseText);
				}
			}
			request.send("phone=" + document.getElementById("phone").value);
		}

		function findback() {
			var request;
			request = new XMLHttpRequest();

			request.open("POST", "./reset.do", true);
			request.setRequestHeader("Content-type",
					"application/x-www-form-urlencoded");
			request.onreadystatechange = function() {
				if (request.readyState == 4 && request.status == 200) {
					alert(request.responseText);
				}
			}
			request.send("phone=" + document.getElementById("back").value);
		}
		
		
	</script>

</body>
</html>