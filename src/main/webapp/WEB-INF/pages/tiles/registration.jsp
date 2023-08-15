<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
	
	<fmt:setLocale value="${sessionScope.local}"/>
	<fmt:setBundle basename="localization.local" var="loc"/>
	<fmt:message bundle="${loc}" key="local.registration.page.registration" var="registr_page" /> 
	<fmt:message bundle="${loc}" key="local.error.page.go.to.basepage" var="go_to_basepage" />
	<fmt:message bundle="${loc}" key="local.registration.page.name" var="registr_page_name" />
	<fmt:message bundle="${loc}" key="local.registration.page.surname" var="registr_page_surname" />
	<fmt:message bundle="${loc}" key="local.registration.page.login" var="registr_page_login" />
	<fmt:message bundle="${loc}" key="local.registration.page.password" var="registr_page_password" />
	<fmt:message bundle="${loc}" key="local.registration.page.email" var="registr_page_email" />
	<fmt:message bundle="${loc}" key="local.registration.page.secret.word" var="registr_page_secret_word" />
	<fmt:message bundle="${loc}" key="local.registration.page.english.level" var="registr_page_english_level" />
	<fmt:message bundle="${loc}" key="local.registration.page.english.level.beginner" var="registr_page_english_level_beginner" />
	<fmt:message bundle="${loc}" key="local.registration.page.english.level.intermediate" var="registr_page_english_level_intermediate" />
	<fmt:message bundle="${loc}" key="local.registration.page.english.level.upperintermediate" var="registr_page_english_level_upperintermediate" />
	<fmt:message bundle="${loc}" key="local.registration.page.english.level.advanced" var="registr_page_english_level_advanced" />
	<fmt:message bundle="${loc}" key="local.registration.page.way.to.work" var="registr_page_way_to_work" />
	<fmt:message bundle="${loc}" key="local.registration.page.way.to.work.public" var="registr_page_way_to_work_public" />
	<fmt:message bundle="${loc}" key="local.registration.page.way.to.work.car" var="registr_page_way_to_work_car" />
	<fmt:message bundle="${loc}" key="local.registration.page.way.to.work.foot" var="registr_page_way_to_work_foot" />
	<fmt:message bundle="${loc}" key="local.registration.page.way.to.work.bike" var="registr_page_way_to_work_bike" />
	<fmt:message bundle="${loc}" key="local.registration.page.way.to.work.moto" var="registr_page_way_to_work_moto" />
	<fmt:message bundle="${loc}" key="local.registration.page.send.form" var="registr_page_send_form" />
	<fmt:message bundle="${loc}" key="local.registration.page.form.completed" var="registr_form_completed" />
	
	
	


<html>

<body>
	
	 <h3> <c:out value="${registr_page }"></c:out> </h3>	
	 
	 <c:if test="${requestScope.userRegister eq 'register'}">
	 
 	<form action="controller" method="post">
 	 <input type="hidden" name="command" value="do_registration">
 	
 	<div class="list-menu-wrapper" style="float: centre;">
 	
 	<p>  <c:out value="${registr_page_name}"></c:out> <input type="text" name="name" value=""> </p>
 	<p> <c:out value="${registr_page_surname}"> </c:out> <input type="text" name="surname" value=""> </p>
 	<p> <c:out value="${registr_page_login}"></c:out> <input type="text" name="login" value=""> </p>
 	<p> <c:out value="${registr_page_password}"></c:out> <input type="text" name="password" value="" placeholder="min 8 marks"> </p>
 	<p> <c:out value="${registr_page_email}"></c:out> <input type="text" name="email" value="" placeholder="mail@dot.com "> </p>
 	<p> <c:out value="${registr_page_secret_word}"></c:out> <input type="text" name="secret_word" value="" placeholder="mother surname"> </p>		
	
	<div> <h3> <c:out value="${registr_page_english_level}"></c:out> </h3> 
	<input type="radio" id="language" name="level" value="beginner">
	<label for="language">  <c:out value="${registr_page_english_level_beginner}"></c:out>  </label> 
	<input type="radio" id="language" name="level" value="intermediate">
	<label for="language"> <c:out value="${registr_page_english_level_intermediate}"></c:out>  </label>
	<input type="radio" id="language" name="level" value="upper-intermediate">
	<label for="language"> <c:out value="${registr_page_english_level_upperintermediate}"></c:out> </label> 
	<input type="radio" id="language" name="level" value="advanced">
	<label for="language"> <c:out value="${registr_page_english_level_advanced}"></c:out> </label>
	
	</div>
	
	<div>
	 <h3> <c:out value="${registr_page_way_to_work}"></c:out> </h3>
    <input type="checkbox" name="vehicle" value="public"> <c:out value="${registr_page_way_to_work_public}"></c:out><br>
    <input type="checkbox" name="vehicle" value="car"> <c:out value="${registr_page_way_to_work_car}"></c:out><br>
    <input type="checkbox" name="vehicle" value="foot"> <c:out value="${registr_page_way_to_work_foot}"></c:out><br>
    <input type="checkbox" name="vehicle" value="bike"><c:out value="${registr_page_way_to_work_bike}"></c:out><br>
    <input type="checkbox" name="vehicle" value="moto"><c:out value="${registr_page_way_to_work_moto}"></c:out><br>
  </div>
 		
 	<input type="submit" value="<c:out value="${registr_page_send_form}"></c:out> ">
 	
 	</div>
 	</form>
 	
 			 
 		 <a href="controller?command=go_to_base_page"> <c:out value="${go_to_basepage}"></c:out></a>
 		 
	 </c:if>		
 		<c:if test="${sessionScope.welcome eq 'registered'}">
 													

			<div align="left">
			
			 			 	
			 	 <h1>	<a href="controller?command=go_to_base_page">  <c:out value="${registr_form_completed}"></c:out> </a></h1>
			 		
			 		<br>
			 		
			 		<a href="controller?command=go_to_base_page"> <c:out value="${go_to_basepage}"></c:out></a>
			 	
			</div> 	

		</c:if>
 	
 	
</body>
</html>