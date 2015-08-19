<%@include file="/apps/todo/global.jsp"%><%
%><!DOCTYPE html>
<html>
    <head>
        <title>Apache Sling TODO List Application - Pure JSP Variant</title>
        <link rel="stylesheet" href="/apps/${resource.resourceType}/css/site.css">
    </head>
    <body>
        <h1>Apache Sling TODO List Application - Pure JSP Variant</h1>
        <c:choose>
            <c:when test="${isAnonymous}">
                <p>You must be logged in to use this application.</p>
                <div class="login-controls">
                    <form method="post" action="/j_security_check">
                        <input type="text" name="j_username" placeholder="User Name" required/>
                        <input type="password" name="j_password" placeholder="Password" required />
                        <input type="hidden" name="sling.auth.redirect" value="${resource.path}.html" />
                        <button type="submit">Sign In</button>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <sling:include path="todolist" resourceType="todo/content/todolist" />
                <div class="logout-controls">
                    <form method="post" action="/system/sling/logout.html">
                        <input type="hidden" name="resource" value="${resource.path}.html" />
                        <button type="submit">Logout</button>
                    </form>
                </div>
            </c:otherwise>
        </c:choose>
    </body>
</html>