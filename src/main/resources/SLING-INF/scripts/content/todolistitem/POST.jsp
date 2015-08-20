<%@ page import="org.apache.sling.api.SlingHttpServletRequest" %>
<%@ page import="org.apache.sling.api.SlingHttpServletResponse" %>
<%@ page import="org.apache.sling.api.resource.Resource" %>
<%@ page import="org.apache.sling.api.resource.ModifiableValueMap" %>
<%@ page import="org.apache.sling.api.resource.ResourceResolver" %>
<%@include file="/apps/todo/global.jsp"%><%

    /* These are here so that I can use code completion - they needent be here however as they are all exposed by sling:defineObjects */
    SlingHttpServletRequest mRequest = (SlingHttpServletRequest) request;
    SlingHttpServletResponse mResponse = (SlingHttpServletResponse) response;
    Resource mResource = resource;
    ResourceResolver mResourceResolver = mResource.getResourceResolver();
    Resource parent = mResource.getParent();

    if ("true".equals(mRequest.getParameter("isDone"))) {
        ModifiableValueMap valueMap = mResource.adaptTo(ModifiableValueMap.class);
        valueMap.put("isDone", true);
        mResourceResolver.commit();
    }
    else if ("true".equals(mRequest.getParameter("delete"))) {
        mResourceResolver.delete(mResource);
        mResourceResolver.commit();
    }

    while (parent != null && !parent.isResourceType("todo/page/home")) {
        parent = parent.getParent();
    }

    if (parent != null) {
        mResponse.sendRedirect(parent.getPath() + ".html");
        return;
    }
%>
<%-- This line should not be reached if the content is in the right structure --%>
Done