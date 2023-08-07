<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



    <fmt:setLocale value="${sessionScope.local}" />
   	<fmt:setBundle basename="localization.local" var="loc" />
   
   <fmt:message bundle="${loc}" key="local.news.view.back" var="back_to_news_list" />
   <fmt:message bundle="${loc}" key="local.news.view.newsId" var="news_id" />
   <fmt:message bundle="${loc}" key="local.news.view.newsId.automatically" var="news_id_auto" />
   <fmt:message bundle="${loc}" key="local.news.view.news.title" var="news_title" />
   <fmt:message bundle="${loc}" key="local.news.view.news.date" var="news_date" />
   <fmt:message bundle="${loc}" key="local.news.view.news.brief" var="news_brief" />
   <fmt:message bundle="${loc}" key="local.news.view.news.content" var="news_content" />
   <fmt:message bundle="${loc}" key="local.news.view.news.image.path" var="news_image_path" />
   <fmt:message bundle="${loc}" key="local.news.view.news.image.current" var="news_image_current" />
   <fmt:message bundle="${loc}" key="local.news.view.news.status" var="news_status" />
   <fmt:message bundle="${loc}" key="local.news.view.news.choose.file" var="news_file_choose" />
   <fmt:message bundle="${loc}" key="local.news.view.news.save" var="news_save" />      


<div class="body-title">
	<!-- <a href="">News >> </a> View News   -->               
	<a href="controller?command=go_to_news_list"> <c:out value="${back_to_news_list}"></c:out></a>
</div>

<c:if test="${sessionScope.role eq 'admin'}">

	<form action="controller" method="post">
	<input type="hidden" name="command" value="do_edit_news" /> 

<div class="add-table-margin">
	<table class="news_text_format">
		
		<tr>
			
			
			<td class="space_around_title_text"> <c:out value="${news_id}"></c:out></td>

			<td class="space_around_view_text"><div class="word-breaker">
						
			<input type="hidden" name="id" value="${requestScope.newsEdit.idNews}" />
			 
			<h3><c:out value="${requestScope.newsEdit.idNews }" /> </h3>	
				</div></td>
		</tr>
		
		
		<tr>
			<td class="space_around_title_text"> <c:out value="${news_title}"></c:out></td>

			<td class="space_around_view_text"><div class="word-breaker">
			
			<h3><input type="text" name="title" value="<c:out value="${requestScope.newsEdit.title }" /> "></h3>
			
				
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><c:out value="${news_date}"></c:out></td>

			<td class="space_around_view_text"><div class="word-breaker">
			<input type="text" name="date" value="<c:out value="${requestScope.newsEdit.newsDate}" />" 
			placeholder="example 2023-06-20 00:00:00">
					
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><c:out value="${news_brief}"></c:out></td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			
					<textarea wrap="soft" rows="4" cols="60" name="briefNews" placeholder="max 100 marks" 
					style="width:419px; heght:77px;">
						<c:out value="${requestScope.newsEdit.briefNews }" />
					</textarea>
				
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text"><c:out value="${news_content}"></c:out></td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<textarea rows="20" cols="60" name="content">
			
			<c:out value="${requestScope.newsEdit.content }" />
			
			</textarea>
			
					
				</div>
				</td>
		</tr>
		
		<tr>
			<td class="space_around_title_text"><c:out value="${news_image_path}"></c:out></td>
			<td >
			<div >
				<!-- <c:out value="${requestScope.news.imagePath}" /> -->	
					<p> 
					
					<!-- <input type="text" name="imgPath" value="${requestScope.newsEdit.imagePath}"> -->
					<c:out value="${news_file_choose}"></c:out>
					<input type="file" name="imgPath" accept="image/png, image/jpeg" value="${requestScope.newsEdit.imagePath}">
					
			
					 
						
				
				</div></td>
				
				
		</tr>
		
		<tr>
			<td class="space_around_title_text"><c:out value="${news_image_current}"></c:out></td>
			<td >
			<div >
				<!-- <c:out value="${requestScope.news.imagePath}" /> -->	
					<p> 
					
					<!-- <input type="text" name="imgPath" value="${requestScope.newsEdit.imagePath}"> -->
					
					<input type="text" name="currentImgPath" value="${requestScope.newsEdit.imagePath}">
				
						
				
				</div></td>
				
				
		</tr>
		
		
		
		<tr>
			<td class="space_around_title_text"><c:out value="${news_status}"></c:out></td>
			<td >
			<div >
				<!-- <c:out value="${requestScope.news.imagePath}" /> -->	
					<p> 
					
					
					<input type="text" name="newsStatus" value="${requestScope.newsEdit.status}">
					
							
				</div></td>
		</tr>	
			
			
			
		<tr>
			<td class="space_around_title_text">
			
			<a href="controller?command=go_to_news_list"> <c:out value="${back_to_news_list}"></c:out></a></td>
			
		</tr>
		
		

		
	</table>
		
		
			
</div>

	<div style="save-button">
	
	 <input type="submit" value='<c:out value="${news_save}"></c:out>' />
	
	</div>
		
			
			
	</form>


<!-- <div class="second-view-button">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="delete" /> <input
			type="hidden" name="id" value="${news.idNews}" /> <input
			type="submit" value="Delete" />
	</form>
</div>    -->

</c:if>