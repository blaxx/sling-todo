<%@include file="/apps/todo/global.jsp"%>
<jsp:useBean id="todoitem" class="com.icfi.sling.todo.content.todolistitem.TodoListItem">
    <jsp:setProperty name="todoitem" property="resource" value="${resource}"/>
</jsp:useBean>
<div class="to-do-list-item">
    <c:choose>
        <c:when test="${todoitem.done}">
            <i class="completed-label">Done</i>
        </c:when>
        <c:otherwise>
            <form class="completed-form" method="post" action="${resource.path}">
                <input type="hidden" name="isDone" value="true" />
                <button type="submit">Done</button>
            </form>
        </c:otherwise>
    </c:choose>
    <span class="title"><c:out value="${todoitem.title}"/></span>
    <form class="delete-form" method="post" action="${resource.path}">
        <input type="hidden" name="delete" value="true" />
        <button type="submit">X</button>
    </form>
</div>