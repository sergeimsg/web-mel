<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


	<fmt:setLocale value="${sessionScope.local}" />
   	<fmt:setBundle basename="localization.local" var="loc" />
   
   <fmt:message bundle="${loc}" key="local.menu.menu.list" var="menu_title" />
   <fmt:message bundle="${loc}" key="local.menu.news.list" var="news_list" />
   <fmt:message bundle="${loc}" key="local.menu.news.add" var="add_news_menu" />
   <fmt:message bundle="${loc}" key="local.menu.admin.users" var="admin_users"/>
   

<div class="menu-wrapper">
	<div class="menu-title-wrapper">
		<div class="menu-title">
		      <h3> <c:out value="${menu_title}"></c:out>  </h3>
		</div>
	</div>

	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper" style="float: left;">
			<ul style="list-style-image: url(images/img.jpg); text-align: left; ">
				
				<li style="padding-left: 5px;">
				
				<a href="controller?command=go_to_news_list"><c:out value="${news_list}"></c:out></a><br />
				</li>

				
				 <c:if test="${sessionScope.role eq 'admin'}">
				   <li style="padding-left: 5px;">
				
				    <a href="controller?command=go_to_add_news"><c:out value="${add_news_menu}"></c:out> </a> </li>
                
                 	<li style="padding-left: 5px"> <a href="controller?command=go_to_users_list">  <c:out value="${admin_users}"></c:out>    </a> </li>
                 
                 <br />
                 
                 
					
				
				</c:if>
			</ul>
		</div>
		<div class="clear"></div>
	</div>
	<!--  grey free space at the bottom of menu -->
	<div style="height: 25px;"></div>
</div>

