<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

    <fmt:setLocale value="${sessionScope.local}" />
   	<fmt:setBundle basename="localization.local" var="loc" />
   	
   	
   	<jsp:useBean id="now" class="java.util.Date"></jsp:useBean>

<center> 
 <fmt:formatDate value="${now}"   type="both" timeStyle="short" pattern= "EEEE dd MMMM yyyy GGG HH:mm:ss zzz "/>
</center>
