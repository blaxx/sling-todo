<%@include file="/apps/todo/global.jsp"%><%
%><!DOCTYPE html>
<html>
    <head>
        <title>Apache Sling TODO List Application - Pure JSP Variant</title>
        <link rel="stylesheet" href="/apps/${resource.resourceType}/css/site.css">
    </head>
    <body>
        <h1>Apache Sling TODO List Application - Pure JSP Variant</h1>
        <sling:include path="todolist" resourceType="todo/content/todolist" />
    </body>
</html>