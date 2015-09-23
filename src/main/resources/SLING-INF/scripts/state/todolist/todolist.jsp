<%@include file="/apps/todo/global.jsp"%>
<sling:include path="todolist" resourceType="todo/content/todolist" />
<div class="logout-controls">
    <form ng-submit="controls.logout()">
        <button type="submit">Logout</button>
    </form>
</div>