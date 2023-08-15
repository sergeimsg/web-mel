<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



	<fmt:setLocale value="${sessionScope.local}" />
   	<fmt:setBundle basename="localization.local" var="loc" />
   
   <fmt:message bundle="${loc}" key="local.newsList.title" var="newsList_title" />
   <fmt:message bundle="${loc}" key="local.newsList.newsId" var="news_id" />
   <fmt:message bundle="${loc}" key="local.newsList.read.news" var="read_news" />
   <fmt:message bundle="${loc}" key="local.newsList.edit.news" var="edit_news" />
   <fmt:message bundle="${loc}" key="local.newsList.delete.news" var="delete_news" />
   <fmt:message bundle="${loc}" key="local.newsList.no.news" var="no_news" />

<div class="body-title">
	
	<c:out value="${newsList_title}"></c:out>
	
</div>


<form action="controller" method="post">
<input type="hidden" name="command" value="do_delete_news">
		
	<c:forEach var="news" items="${requestScope.news}">
	
	<c:if test="${news.status eq 1}">
	
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<c:out value="${news_id}"></c:out>
					<c:out value="${news.idNews}" />
					<h3>
						<c:out value="${news.title}" />
					</h3>
				</div>
				<div class="news-date">
				
			<c:set var = "dateScope" value = "${news.newsDate}" />	
			
			<fmt:parseDate value = "${dateScope}" var = "parsedDate" pattern = "yyyy-MM-dd" />
			
			<fmt:formatDate type="both" pattern = "EEE, d MMM yyyy HH:mm " dateStyle="medium" timeStyle="short" value="${parsedDate}" />
					
					
					
				</div>

				<div class="news-content">
					<c:out value="${news.briefNews}" />
				</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">
				
						<c:if test="${sessionScope.role eq 'admin'}">
							<a href="controller?command=go_to_edit_news&id=${news.idNews}"><c:out value="${edit_news}"></c:out> </a> ----------
						</c:if>

						<a href="controller?command=go_to_view_news&id=${news.idNews}"><c:out value="${read_news}"></c:out> </a>
					
				 						
				
						<c:if test="${sessionScope.role eq 'admin'}">
							<input type="checkbox" name="idNewsCheckBox" value="${news.idNews}" />
						</c:if>
						
										
					</div>
				</div>

			</div>
		</div>
		
		</c:if>

	</c:forEach>


	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
		
		        
        <h1> <c:out value="${no_news}"></c:out>  </h1>
         
	</c:if>
	
	</div>
	
	<c:if test="${sessionScope.role eq 'admin' && requestScope.news ne null}">
	
	<div class="save-button">
	
	<input type="submit" value="<c:out value="${delete_news}"></c:out>">
	
	</div>
	
	</c:if>
	
	
	
	
</form>
