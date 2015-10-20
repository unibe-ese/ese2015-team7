<!DOCTYPE HTML>
<!--
	Verti by HTML5 UP
	html5up.net | @n33co
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<h1>Login</h1>

<form name='loginForm' action="j_spring_security_check" method='POST'>
    <fieldset>


			<c:if test="${not empty param.err}">
                <div><c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/></div>
            </c:if>
            <c:if test="${not empty param.out}">
                <div>You've logged out successfully.</div>
            </c:if>
            <c:if test="${not empty param.time}">
                <div>You've been logged out due to inactivity.</div>
            </c:if>
            
            
            
        <c:set var="nameErrors"><form:errors path="name"/></c:set>
        
        <div class="control-group<c:if test="${not empty nameErrors}"> error</c:if>">
            <label for="field-username">E-mail:</label>
            <div class="controls">
            	<input name="j_username" id="field-username" type="email"/>
                
                <form:errors path="name" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <c:set var="passwordErrors"><form:errors path="password"/></c:set>
        <div class="control-group<c:if test="${not empty passwordErrors}"> error</c:if>">
            <label for="field-password">Passwort:</label> 
            <div class="controls">
           		<input name="j_password" id="field-password" type="password"/>
             
                <form:errors path="password" cssClass="help-inline" element="span"/>
            </div>
        </div>
        
        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Login</button>
            <button type="button" class="btn" onclick="location.href='/Skeleton/signUp'">SignUp</button>
        </div>
    </fieldset>
</form:form>


