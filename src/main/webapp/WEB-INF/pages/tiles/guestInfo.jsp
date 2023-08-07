<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



	<fmt:setLocale value="${sessionScope.local}" />
   	<fmt:setBundle basename="localization.local" var="loc" />
	<fmt:message bundle="${loc}" key="local.newsList.no.news" var="no_news" /> 
  
	

	<c:if test="${requestScope.news2 eq 'latestNews'}">
	
	<c:forEach var="news" items="${requestScope.news}">
	
	<c:if test="${news.status eq 1}">
	
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
				<h3> <c:out value="${news.title}" /> </h3>	
				</div>
				<div class="news-date">
				
			<c:set var = "dateScope" value = "${news.newsDate}" />	
			
			<fmt:parseDate value = "${dateScope}" var = "parsedDate" pattern = "yyyy-MM-dd" />
			
			<fmt:formatDate type="both" pattern = "d MMM yyyy HH:mm " dateStyle="medium" timeStyle="short" value="${parsedDate}" />
				
					
					
				</div>

				<div class="news-content">
					<c:out value="${news.briefNews}" />
				</div>
			</div>
		</div>
		
		</c:if>

	</c:forEach>
	
	</c:if>

	<div class="no-news">
	 	
	 	
	<c:if test="${empty requestScope.news}">
		
		<h2> <c:out value="${no_news}"></c:out>  </h2>  
		
      	<br>
      
	</c:if>
	
	</div>


