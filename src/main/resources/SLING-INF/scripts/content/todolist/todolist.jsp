<%@include file="/apps/todo/global.jsp"%>
<jsp:useBean id="todolist" class="com.icfi.sling.todo.content.todolist.TodoList">
    <jsp:setProperty name="todolist" property="resource" value="${resource}"/>
</jsp:useBean>
<div class="to-do-list">
    <h2>To Do List</h2>
    <form method="post" action="${resource.path}" class="add-item-form">
        <input type="text" name="title" placeholder="What needs to be done?" required>
        <button type="submit">Add</button>
    </form>
    <section class="list-items">
        <c:forEach var="currentListItem" items="${todolist.items}">
            <sling:include path="${currentListItem.path}" resourceType="todo/content/todolistitem"/>
        </c:forEach>
        <c:if test="${empty todolist.items}">
            <p class="empty-list-warning">
                Your TODO list is currently empty.  Enjoy your free time.
            </p>
        </c:if>
    </section>
    <section class="list-statistics">
        <c:if test="${!empty todolist.items}">
            <span class="items-remaining">${todolist.itemsRemaining} remaining</span>
        </c:if>
    </section>
</div>