<%@include file="/apps/todo/global.jsp"%>
<div class="to-do-list-item">
    <c:set var="properties" value="${sling:adaptTo(resource,'org.apache.sling.api.resource.ValueMap')}" />
    <c:choose>
        <c:when test="${sling:getValue(properties, 'isDone', false)}">
            <i class="completed-label">Done</i>
        </c:when>
        <c:otherwise>
            <form class="completed-form" method="post" action="${resource.path}">
                <input type="hidden" name="isDone" value="true" />
                <button type="submit">Done</button>
            </form>
        </c:otherwise>
    </c:choose>
    <span class="title"><c:out value="${sling:getValue(properties, 'title', resource.name)}"/></span>
    <form class="delete-form" method="post" action="${resource.path}">
        <input type="hidden" name="delete" value="true" />
        <button type="submit">X</button>
    </form>
</div>