package com.icfi.sling.todo.content.todolist;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SlingServlet(
        resourceTypes = "todo/content/todolist",
        methods = "POST"
)
public class NewListItemServlet extends SlingAllMethodsServlet {
	
	private Logger LOG = LoggerFactory.getLogger(this.getClass());

    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
       
    	String newTitle = request.getParameter("title");
        
        LOG.info("[doPost] - new title -> {}", newTitle);

        if (newTitle == null || newTitle.equals("")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("TODO item title is a required field");
        }

        Resource itemsResource = request.getResource().getChild("items");

        if (itemsResource == null) {
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("jcr:primaryType", "nt:unstructured");

            itemsResource = request.getResourceResolver().create(request.getResource(), "items", properties);
            request.getResourceResolver().commit();
        }

        Map<String, Object> newItemProperties = new HashMap<String, Object>();
        newItemProperties.put("jcr:primaryType", "nt:unstructured");
        newItemProperties.put("title", newTitle);
        newItemProperties.put("sling:resourceType", "todo/content/todolistitem");

        request.getResourceResolver().create(itemsResource, "item" + System.currentTimeMillis(), newItemProperties);
        request.getResourceResolver().commit();

        Resource parent = getTodoPageResource(request.getResource());

        if (parent != null) {
            response.sendRedirect(parent.getPath() + ".html");
        }
    }

    private static Resource getTodoPageResource(Resource resource) {
        Resource parent = resource.getParent();

        while (parent != null && !parent.isResourceType("todo/page/home")) {
            parent = parent.getParent();
        }

        return parent;
    }

}
