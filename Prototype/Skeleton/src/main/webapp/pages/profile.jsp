<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<c:import url="template/header.jsp" />

<div class="profile-page" >
<h2>Profile</h2>

<c:if test="${user.email == principalName}">
	<button type="button" class="btn-edit" onclick="">Edit</button>
</c:if>

<!-- <img src="image/1212" height="75px" width="75px" align="left" /> -->
<h3><c:out value="${user.name}"/></h3>
<h4><c:out value="${user.email}"/></h4>

<p>
<button type="button" class="btn" onclick="switchDisplay('profile-frontpage', 'profile-timeslots')">Front Page</button>
<button type="button" class="btn" onclick="switchDisplay('profile-timeslots', 'profile-frontpage')">Timeslots</button>
</p>

<div id="profile-frontpage">
<h3>Grades</h3>
<table class = "default">
  <tr>
    <td><c:out value="hallo" /></td>
  </tr>
</table>

<h3>Biography</h3>
<p><c:out value="${user.biography}"/></p>
</div>

<div id="profile-timeslots" style="display: none;">
<h3>Timeslots</h3>
<p><c:out value=""/></p>
</div>

<p><button type="button" class="btn" onclick="location.href='/Skeleton/search'">Search Tutor</button></p>

<c:if test="${user.email != principalName}">
	<form:form method="post" action="myRequests" modelAttribute="searchForm" id="profile" cssClass="form-horizontal"  autocomplete="off">
    	<p><button type=submit name=requestedUser value="${item.user.email}">Send Request</button></p>    
	</form:form>
</c:if>
<!--
	<c:if test="${page_error != null }">
        <div class="alert alert-error">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <h4>Error!</h4>
                ${page_error}
        </div>
    </c:if>
  -->
    
</div>

<c:import url="template/footer.jsp" />

<script type="text/javascript">
function switchDisplay(id1,id2)
{
	document.getElementById(id1).style.display = 'none';
	document.getElementById(id2).style.display = 'none';
	document.getElementById(id1).style.display = 'block';
}
</script>