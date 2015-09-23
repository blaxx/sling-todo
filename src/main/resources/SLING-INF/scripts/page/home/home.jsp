<%@include file="/apps/todo/global.jsp"%><%
%><!DOCTYPE html>
<html ng-app="todoApp">
    <head>
        <title>Apache Sling TODO List Application - Angular Application Variant</title>
        <link rel="stylesheet" href="/apps/${resource.resourceType}/css/site.css">

        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.2.15/angular-ui-router.js"></script>
        <script src="/apps/${resource.resourceType}/js/controllers/loginCtrl.js"></script>
        <script src="/apps/${resource.resourceType}/js/controllers/todoListCtrl.js"></script>
        <script src="/apps/${resource.resourceType}/js/services/authenticationService.js"></script>
        <script src="/apps/${resource.resourceType}/js/app.js"></script>
    </head>
    <body>
        <h1>Apache Sling TODO List Application - Backing Beans &amp; Servlets Variant</h1>
        <div ui-view></div>
        <script id="login.html" type="text/ng-template">
            <sling:include path="." resourceType="todo/state/login" />
        </script>
        <script id="todolist.html" type="text/ng-template">
            <sling:include path="." resourceType="todo/state/todolist" />
        </script>
    </body>
</html>