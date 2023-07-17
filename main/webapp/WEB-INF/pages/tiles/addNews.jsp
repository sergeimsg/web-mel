<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="body-title">
	<!-- <a href="">News >> </a> View News   -->               
	<a href="controller?command=go_to_news_list"> Back to news list</a>
</div>

<c:if test="${sessionScope.role eq 'admin'}">

	<form action="controller" method="post">
		<input type="hidden" name="command" value="do_add_news" /> 

<div class="add-table-margin">
	<table class="news_text_format">
		
		<tr>
			
			
			<td class="space_around_title_text"> News id</td>

			<td class="space_around_view_text"><div class="word-breaker">
			
			<input type="hidden" name="id" value="" /> 	
				Added automatically
				
				</div></td>
		</tr>
		
		
		<tr>
			<td class="space_around_title_text"> News title</td>

			<td class="space_around_view_text"><div class="word-breaker">
			
			<h3><input type="text" name="title" value="<c:out value="" /> "></h3>
			
				
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">News Date</td>

			<td class="space_around_view_text"><div class="word-breaker">
			<input type="text" name="date" value="<c:out value="" />" 
			placeholder="example 2023-06-20 00:00:00">
					
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">Brief</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			
					<textarea wrap="soft" rows="4" cols="60" name="briefNews" placeholder="max 100 marks" 
					style="width:419px; heght:77px;" >
						<c:out value="" />
					</textarea>
				
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">Content</td>
			<td class="space_around_view_text">
			<div class="word-breaker">
			<textarea rows="20" cols="60" name="content">
			
			<c:out value="" />
			
			</textarea>
			
					
				</div>
				</td>
		</tr>
		
		<tr>
			<td class="space_around_title_text">Image path</td>
			<td >
			<div >
				<!-- <c:out value="${requestScope.news.imagePath}" /> -->	
					<p> 
					
					
					<input type="text" name="imgPath" value="">
					
					 
						
				
				</div></td>
		</tr>
			
		
		<tr>
			<td class="space_around_title_text">News status(1-active, 0 - not)</td>
			<td >
			<div >
				<!-- <c:out value="${requestScope.news.imagePath}" /> -->	
					<p> 
					
					
					<input type="text" name="newsStatus" value="1-active, 0 - not">
					
							
				</div></td>
		</tr>	
			
			
			
		<tr>
			<td class="space_around_title_text">
			
			<a href="controller?command=go_to_news_list"> Back  to news list</a></td>
			
		</tr>
		
		

		
	</table>
		
		
			
</div>

	
		<input 	type="hidden" name="id" value="" /> <input
			type="submit" value="Save" />

</form>


<!-- <div class="second-view-button">
	<form action="controller" method="post">
		<input type="hidden" name="command" value="delete" /> <input
			type="hidden" name="id" value="${news.idNews}" /> <input
			type="submit" value="Delete" />
	</form>
</div>    -->

</c:if>
