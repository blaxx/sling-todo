<%@include file="/apps/todo/global.jsp"%><%
%><%@ page import="org.apache.sling.api.resource.Resource" %><%
%><%@ page import="org.apache.sling.api.resource.ValueMap" %><%
%><div class="to-do-list">
    <sling:getResource base="${resource}" path="items" var="listItems" />
    <c:set var="itemCount" value="0" />
    <h2>To Do List</h2>
    <form method="post" action="${resource.path}" class="add-item-form">
        <input type="text" name="title" placeholder="What needs to be done?" required>
        <button type="submit">Add</button>
    </form>
    <section class="list-items">
        <c:if test="${listItems != null}">
            <c:forEach var="currentListItem" items="${sling:listChildren(listItems)}">
                <c:set var="itemCount" value="${itemCount + 1}" />
                <sling:include path="${currentListItem.path}" resourceType="todo/content/todolistitem"/>
            </c:forEach>
        </c:if>
        <c:if test="${itemCount == 0}">
            <p class="empty-list-warning">
                Your TODO list is currently empty.  Enjoy your free time.
            </p>
        </c:if>
    </section>
    <section class="list-statistics">
        <c:if test="${listItems != null && itemCount > 0}">
            <%
                Integer itemsRemaining = 0;

                for(Resource currentListItemResource : listItems.getChildren()) {
                    ValueMap currentListItemProperties = currentListItemResource.adaptTo(ValueMap.class);
                    if (!currentListItemProperties.get("isDone", false)) {
                        itemsRemaining += 1;
                    }
                }

                pageContext.setAttribute("itemsRemaining", itemsRemaining);
            %>
            <span class="items-remaining">${itemsRemaining} remaining</span>
        </c:if>
    </section>
</div>