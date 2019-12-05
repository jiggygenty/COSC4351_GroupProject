<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- This is the menu bar at the top of all pages except the welcome page. -->

<div style="border: 1px solid #ccc;padding:5px;margin-bottom:20px;">
 
   <a href="<%=request.getContextPath() %>/welcome">Home</a>
 
  | &nbsp;
  
   <a href="<%=request.getContextPath() %>/userInfo">User Info</a>
  
  | &nbsp;
  
  <a href="<%=request.getContextPath() %>/admin">Administration</a>
  
  | &nbsp;
  
  <a href="<%=request.getContextPath() %>/finance">Finance</a>
  
  | &nbsp;
  
   <a href="<%=request.getContextPath() %>/sales">Sales</a>
   
  | &nbsp;
  
   <a href="<%=request.getContextPath() %>/hr">Human Resources</a>
   
  | &nbsp;
  
   <a href="<%=request.getContextPath() %>/engi">Engineering</a>
  
  <c:if test="<%=request.getUserPrincipal() != null %>">
  
     | &nbsp;
     
     User: <%=request.getUserPrincipal().getName() %>
     
     | &nbsp;
     
     <a href="<%=request.getContextPath() %>/logout">Logout</a>
     
  </c:if>
  
</div>