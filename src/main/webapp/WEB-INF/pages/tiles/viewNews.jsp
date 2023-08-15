<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


   
   <fmt:setLocale value="${sessionScope.local}" />
   <fmt:setBundle basename="localization.local" var="loc" />
   
   
   <fmt:message bundle="${loc}" key="local.news.view.back" var="back_to_news_list" />
   <fmt:message bundle="${loc}" key="local.news.view.newsId" var="news_id" />
   <fmt:message bundle="${loc}" key="local.news.view.news.title" var="news_title" />
   <fmt:message bundle="${loc}" key="local.news.view.news.date" var="news_date" />
   <fmt:message bundle="${loc}" key="local.news.view.news.brief" var="news_brief" />
   <fmt:message bundle="${loc}" key="local.news.view.news.content" var="news_content" />
   <fmt:message bundle="${loc}" key="local.news.view.news.image" var="news_image" />   
   
      

<div class="body-title">
	            
	<a href="controller?command=go_to_news_list"> <c:out value="${back_to_news_list}"></c:out></a>
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		
		<tr>
			
			
			<td class="space_around_title_text"> <c:out value="${news_id}"></c:out></td>

			<td class="space_around_view_text"> <div class="word-breaker">
				<h3><c:out value="${requestScope.news.idNews }" /> </h3>	
				</div></td>
		</tr>
		
		
		<tr>
			<td class="space_around_title_text"> <c:out value="${news_title}"></c:out></td>

			<td class="space_around_view_text"><div class="word-breaker">
				<h3><c:out value="${requestScope.news.title }" /> </h3>	
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><c:out value="${news_date}"></c:out></td>

			<td class="space_around_view_text"><div class="word-breaker">
			
			<c:set var = "dateScope" value = "${requestScope.news.newsDate}" />	
			
			<fmt:parseDate value = "${dateScope}" var = "parsedDate" pattern = "yyyy-MM-dd" />
			
			<fmt:formatDate type="both" pattern = "EEE, d MMM yyyy HH:mm" dateStyle="medium" timeStyle="short" value="${parsedDate}" />
			
			  <br>
			     
				
					
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><c:out value="${news_brief}"></c:out></td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.briefNews }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><c:out value="${news_content}"></c:out></td>
			<td class="space_around_view_text">
			<div class="word-breaker">
					<c:out value="${requestScope.news.content }" />
				</div>
				</td>
		</tr>
		
		<tr>
			<td class="space_around_title_text"><c:out value="${news_image}"></c:out></td>
			<td >
			<div >
				<!-- <c:out value="${requestScope.news.imagePath}" /> -->	
					<p> 
					 <img alt="${requestScope.news.title}" src="${requestScope.news.imagePath}"> </p>
				
				
				
				</div></td>
		</tr>
			
		<tr>
			<td class="space_around_title_text">
			
			<a href="controller?command=go_to_news_list"> <c:out value="${back_to_news_list}"></c:out></a></td>
			
		</tr>
		
		

		
	</table>
		
		
			
</div>




<c:if test="${sessionScope.role eq 'admin'}">
<div class="first-view-button">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="go_to_edit_news" /> <input
			type="hidden" name="id" value="${news.idNews}" /> <input
			type="submit" value="Edit" />
	</form>
</div>

<div class="second-view-button">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="delete" /> <input
			type="hidden" name="id" value="${news.idNews}" /> <input
			type="submit" value="Delete" />
	</form>
</div>
</c:if>