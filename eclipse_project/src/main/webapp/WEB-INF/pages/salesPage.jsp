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
    	<li>Sales Reports</li>
    	<li>Sales Leads</li>
    	<li>Sales Demo</li>
    </ul>
</body>
</html>