<%@page session="false"%>
<html>
<head>
<title><%=request.getAttribute("title") %></title>
</head>
<body>
  <h1><%=request.getAttribute("message") %></h1>
  <h3><a href="<%= request.getContextPath() %>/login">Continue to login</a></h3>
</body>
</html>