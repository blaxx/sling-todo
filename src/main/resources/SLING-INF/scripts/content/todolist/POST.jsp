<%@include file="/apps/todo/global.jsp"%><%
%><%@ page import="org.apache.sling.api.resource.Resource" %><%
%><%@ page import="java.util.HashMap" %><%
%><%@ page import="java.util.Map" %><%
%><%@ page import="org.apache.sling.api.SlingHttpServletRequest" %><%
%><%@ page import="org.apache.sling.api.resource.ResourceResolver" %><%
%><%@ page import="org.apache.sling.api.SlingHttpServletResponse" %><%

    /* These are here so that I can use code completion - they needent be here however as they are all exposed by sling:defineObjects */
    SlingHttpServletRequest mRequest = (SlingHttpServletRequest) request;
    SlingHttpServletResponse mResponse = (SlingHttpServletResponse) response;
    Resource mResource = resource;
    ResourceResolver mResourceResolver = mResource.getResourceResolver();

    String newTitle = request.getParameter("title");

    if (newTitle == null || newTitle.equals("")) {
        mResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        mResponse.getWriter().write("TODO item title is a required field");
        return;
    }

    Resource itemsResource = mResource.getChild("items");

    if (itemsResource == null) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("jcr:primaryType", "nt:unstructured");

        itemsResource = mResourceResolver.create(mResource, "items", properties);
        mResourceResolver.commit();
    }

    Map<String, Object> newItemProperties = new HashMap<String, Object>();
    newItemProperties.put("jcr:primaryType", "nt:unstructured");
    newItemProperties.put("title", newTitle);
    newItemProperties.put("sling:resourceType", "todo/content/todolistitem");

    mResourceResolver.create(itemsResource, "item" + System.currentTimeMillis(), newItemProperties);
    mResourceResolver.commit();

    Resource parent = mResource.getParent();

    while (parent != null && !parent.isResourceType("todo/page/home")) {
        parent = parent.getParent();
    }

    if (parent != null) {
        mResponse.sendRedirect(parent.getPath() + ".html");
        return;
    }
%>
<%-- This line should not be reached if the content is in the right structure --%>
${newTitle} Created