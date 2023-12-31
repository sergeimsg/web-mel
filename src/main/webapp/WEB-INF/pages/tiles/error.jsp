<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>

		 <fmt:setLocale value="${sessionScope.local}" />
   		 <fmt:setBundle basename="localization.local" var="loc" />
   
		  <fmt:message bundle="${loc}" key="local.error.page.error.message" var="error_page" />
		  <fmt:message bundle="${loc}" key="local.error.page.go.to.basepage" var="go_to_basepage" />
		  <fmt:message bundle="${loc}" key="local.menu.news.list" var="news_list" />
		  
		  
		  


<body>
	
	
 	
 	<div class="error-box" style="float: centre;">
 	
 	<c:out value="${error_page}"></c:out>
 	 
 	 <br>
 	 <c:out value="${requestScope.AuthentificationError}"></c:out>
 	 <br>
 	 <c:out value="${requestScope.errorE}"></c:out>
 	 <br>
 	 
 	 <c:if test="${sessionScope.role eq 'admin'}">
 	 
 	 
 	 <a href="controller?command=go_to_news_list"> <c:out value="${news_list}"></c:out></a>
 	 
 	 </c:if>
 	 <br>
 	 
 	 <c:if test="${not (sessionScope.role eq 'admin')}">
 	 
 	 
 	 <a href="controller?command=go_to_base_page"> <c:out value="${go_to_basepage}"></c:out></a>
 	 
 	 </c:if>
 	 
 	 
 	 
 	 
	</div>
	
	
	
	 	
 	
</body>
</html>

