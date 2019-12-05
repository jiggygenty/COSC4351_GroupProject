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
    	<li>Finance Reports</li>
    	<li>Accounts Payable</li>
    	<li>Accounts Receivable</li>
    	<li>Tax</li>
    </ul>
</body>
</html>