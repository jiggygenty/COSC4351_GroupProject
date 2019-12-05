<%@page session="false"%>
<html>
<head>
<title><%= request.getAttribute("title") %></title>
</head>
<body>
    <jsp:include page="_menu.jsp" />
 
 
    <h1>Message : <%=request.getAttribute("message") %></h1>
</body>
</html>