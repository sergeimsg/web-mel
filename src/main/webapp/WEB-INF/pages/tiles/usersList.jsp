<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
 
 	<fmt:setLocale value="${sessionScope.local}" />
   	<fmt:setBundle basename="localization.local" var="loc" />
   
   	<fmt:message bundle="${loc}" key="local.registration.page.id" var="user_id" />
   	<fmt:message bundle="${loc}" key="local.registration.page.role.id" var="user_role" />
   	<fmt:message bundle="${loc}" key="local.registration.page.role.admin" var="user_admin" />
   	<fmt:message bundle="${loc}" key="local.registration.page.role.user" var="user_user" />
   	<fmt:message bundle="${loc}" key="local.registration.page.name" var="user_name" />
	<fmt:message bundle="${loc}" key="local.registration.page.surname" var="user_surname" />
	<fmt:message bundle="${loc}" key="local.registration.page.login" var="user_login" />
	<fmt:message bundle="${loc}" key="local.registration.page.email" var="user_email" />
	<fmt:message bundle="${loc}" key="local.registration.page.role.user.edit" var="user_edit" />
	
	
   

<body>
		
		
	
	  
 <div class="add-table-margine">
 	
 	 	
 <table class="news_text_format">
 
 	   <tr class="text-table-header"> 
		    
			<td> <c:out value="${user_id}"></c:out></td> 
		    <td> <c:out value="${user_surname}"></c:out></td>
		    <td> <c:out value="${user_name}"></c:out></td>
		    <td> <c:out value="${user_email}"></c:out></td>
		    <td> <c:out value="${user_login}"></c:out></td>
		    <td> <c:out value="${user_role}"></c:out></td>
		
		  
				
		</tr>	
 	
	<c:forEach var="users" items="${requestScope.userList}">
	
			 
	
	    <tr> 
		    <td> <c:out value="${users.userID}"></c:out></td> 
		    <td> <c:out value="${users.name}"></c:out></td>
		    <td> <c:out value="${users.surname}"></c:out></td>
		    <td>  <c:out value="${users.email}"></c:out> </td>
		    <td>  <c:out value="${users.login}"></c:out> </td>
		    <c:if test="${users.role eq '1'}">
		    <td>   <c:out value="${user_user}"></c:out> </td>    
		    </c:if>
		    
		    <c:if test="${users.role eq '2'}">
		    <td>   <c:out value="${user_admin}"></c:out> </td>    
		    </c:if>
		        <td> <a href="controller?command=go_to_user_detail&userId=${users.userID}"><c:out value="${user_edit}"></c:out> </a></td>
		  
				
		</tr>		 	
		    
		
	</c:forEach>	
	    
	    </table>
		</div>
				
		
</body>