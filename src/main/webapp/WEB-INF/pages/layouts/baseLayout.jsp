<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="script/validation.js"></script>
<title>Base page layout</title>

<!-- <bean:message key="locale.linkname.headertitle" />
 -->

<link rel="stylesheet" type="text/css" href="styles/newsStyle.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <fmt:setLocale value="${sessionScope.local}" />
   	<fmt:setBundle basename="localization.local" var="loc" />
   
   <fmt:message bundle="${loc}" key="local.menu.nonews.authorize" var="no_news_authorize" />

</head>
<body>
		
	  <div class="page">
		<div class="header">
			<c:import url="/WEB-INF/pages/tiles/header.jsp" />
			
		</div>
		
				
		<div class="base-layout-wrapper">
			<div class="menu">
				 
				
				   <c:if test="${not (sessionScope.user eq 'active')}">
				   <p class="text-red"> <c:out value="${no_news_authorize}"></c:out>  </p> 
				   </c:if>
				   <%-- 
				    --%>
				   
					<%-- <c:import url=""></c:import> --%>
				
				<c:if test="${sessionScope.user eq 'active'}">
					<c:import url="/WEB-INF/pages/tiles/menu.jsp" />
				</c:if>
		</div>

		<div class="content">
				
					
				<c:if test="${requestScope.error eq 'error' }">
				
					<c:import url="/WEB-INF/pages/tiles/error.jsp"></c:import>
					               
				</c:if>

				<c:if test="${not (sessionScope.user eq 'active') && not (requestScope.user2 eq 'register') &&
				not (requestScope.error eq 'error') }">
					<c:import url="/WEB-INF/pages/tiles/guestInfo.jsp" />
				</c:if>
				<c:if test="${sessionScope.user eq 'active'}">
					<c:import url="/WEB-INF/pages/tiles/body.jsp" />
				</c:if>
				
				<c:if test="${requestScope.user2 eq 'register'}">
					
					<c:import url="/WEB-INF/pages/tiles/registration.jsp" />
				</c:if>


			</div>
		</div>

		<div class="footer">

			<c:import url="/WEB-INF/pages/tiles/footer.jsp" />
		</div>
	</div>
</body>
</html>