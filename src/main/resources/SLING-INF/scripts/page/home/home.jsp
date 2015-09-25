<%@include file="/apps/todo/global.jsp"%><%
%><!DOCTYPE html>
<html ng-app="todoApp">
    <head>
        <title>Apache Sling TODO List Application - Sling Angular Application Variant</title>
        <link rel="stylesheet" href="/apps/${resource.resourceType}/css/site.css">

        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.5/angular.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-ui-router/0.2.15/angular-ui-router.js"></script>
        <script src="/apps/${resource.resourceType}/js/controllers/loginCtrl.js"></script>
        <script src="/apps/${resource.resourceType}/js/controllers/todoListCtrl.js"></script>
        <script src="/apps/${resource.resourceType}/js/services/authenticationService.js"></script>
        <script src="/apps/${resource.resourceType}/js/services/todoListService.js"></script>
        <script src="/apps/${resource.resourceType}/js/directives/todoListDirective.js"></script>
        <script src="/apps/${resource.resourceType}/js/app.js"></script>
    </head>
    <body>
        <h1>Apache Sling TODO List Application - Sling Angular Application Variant</h1>
        <div ui-view></div>
        <script id="login.html" type="text/ng-template">
            <sling:include path="." resourceType="todo/state/login" />
        </script>
        <script id="todolist.html" type="text/ng-template">
            <sling:include path="." resourceType="todo/state/todolist" />
        </script>
        <script id="todolistDirective.html" type="text/ng-template">
            <div class="to-do-list">
                <h2>To Do List</h2>
                <form class="add-item-form" ng-submit="controls.addItemForm.addItem()">
                    <input type="text" name="title" placeholder="What needs to be done?" required ng-model="controls.addItemForm.title">
                    <button type="submit">Add</button>
                </form>
                <section class="list-items">
                    <div class="to-do-list-item" ng-repeat="todoitem in todolist.items">
                        <i class="completed-label" ng-if="todoitem.done">Done</i>
                        <form class="completed-form" ng-if="!todoitem.done" ng-submit="controls.completeItemForm.completeItem( todoitem )">
                            <button type="submit">Done</button>
                        </form>
                        <span class="title">{{todoitem.title}}</span>
                        <form class="delete-form" ng-submit="controls.deleteItemForm.deleteItem( todoitem )">
                            <button type="submit">X</button>
                        </form>
                    </div>
                    <p class="empty-list-warning" ng-if="!todolist.items.length">
                        Your TODO list is currently empty.  Enjoy your free time.
                    </p>
                </section>
                <section class="list-statistics">
                    <span class="items-remaining" ng-if="todolist.items.length">{{todolist.itemsRemaining}} remaining</span>
                </section>
            </div>
        </script>
    </body>
</html>