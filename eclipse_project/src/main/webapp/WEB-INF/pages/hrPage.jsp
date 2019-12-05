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
    	<li>New Hire</li>
    	<li>Onboarding</li>
    	<li>Benefits</li>
    	<li>Payroll</li>
    	<li>Terminations</li>
    	<li>HR Reports</li>
    </ul>
</body>
</html>