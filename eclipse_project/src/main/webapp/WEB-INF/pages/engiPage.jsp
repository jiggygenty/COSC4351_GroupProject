<%@page session="false"%>
<html>
<head>
<title><%= request.getAttribute("title") %></title>
</head>
<body>
    <jsp:include page="_menu.jsp" />
 
 
    <h1>Message : <%=request.getAttribute("message") %></h1>
    
        <h3>Links:</h3>
    <ul>
    	<li>Application Monitoring</li>
    	<li>Tech Support</li>
    	<li>Application Development</li>
    	<li>Application Admin</li>
    	<li>Release Management</li>
    </ul>
</body>
</html>