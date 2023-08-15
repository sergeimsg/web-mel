<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
	
	<fmt:setLocale value="${sessionScope.local}"/>
	<fmt:setBundle basename="localization.local" var="loc"/>
	
	<fmt:message bundle="${loc}" key="local.registration.page.id" var="user_id" />
	<fmt:message bundle="${loc}" key="local.user.detail.page.edit.form" var="edit_page" /> 
	<fmt:message bundle="${loc}" key="local.error.page.go.to.basepage" var="go_to_basepage" />
	<fmt:message bundle="${loc}" key="local.registration.page.name" var="registr_page_name" />
	<fmt:message bundle="${loc}" key="local.registration.page.surname" var="registr_page_surname" />
	<fmt:message bundle="${loc}" key="local.registration.page.login" var="registr_page_login" />
	<fmt:message bundle="${loc}" key="local.registration.page.email" var="registr_page_email" />
	<fmt:message bundle="${loc}" key="local.registration.page.user.role" var="registr_user_role" />
	<fmt:message bundle="${loc}" key="local.registration.page.send.form.update" var="update_user" />
		

<html>

<body>
	
	 <h3> <c:out value="${edit_page }"></c:out> </h3>	
 	<form action="controller" method="post">
 	 <input type="hidden" name="command" value="do_update_user">
 	 <input type="hidden" name="userId" value="${requestScope.userById.userID}">
 	
 	<div class="list-menu-wrapper" style="float: centre;">
 	<p>  <c:out value=" ${user_id}  ${requestScope.userById.userID}"></c:out>  </p>
 	<p>  <c:out value="${registr_page_name}"></c:out> <input type="text" name="name" 
 	value="<c:out value="${requestScope.userById.name}"></c:out>"> </p>
 	<p> <c:out value="${registr_page_surname}"> </c:out> <input type="text" name="surname" 
 	value="<c:out value="${requestScope.userById.surname}"></c:out>"> </p>
 	<p> <c:out value="${registr_page_login}"></c:out> <input type="text" name="login" 
 	value="<c:out value="${requestScope.userById.login}"></c:out>"> </p>
 	<p> <c:out value="${registr_page_email}"></c:out> <input type="text" name="email" 
 	value="<c:out value="${requestScope.userById.email}"></c:out>"> </p>
 	<p> <c:out value="${registr_user_role}"></c:out> <input type="text" name="role" 
 	value="<c:out value="${requestScope.userById.role}" ></c:out>" > 2 - admin, 1 - not </p>		
	
	<br>	 		
 	<input type="submit" value="<c:out value="${update_user}"></c:out> ">
  
 	
 
 	
 	
    	
    	
 	
 	</div>
 	</form>
 		 
 		 <a href="controller?command=go_to_news_list"> <c:out value="${go_to_basepage}"></c:out></a>
	 		
 		
			 	
		
 	
 	
</body>
</html>