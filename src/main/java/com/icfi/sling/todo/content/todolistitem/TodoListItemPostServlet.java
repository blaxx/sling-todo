package com.icfi.sling.todo.content.todolistitem;

import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import javax.servlet.ServletException;
import java.io.IOException;

@SlingServlet(
        resourceTypes = "todo/content/todolistitem",
        methods = "POST"
)
public class TodoListItemPostServlet extends SlingAllMethodsServlet {

    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
    	
    	Resource parent = getTodoPageResource(request.getResource());

        if ("true".equals(request.getParameter("isDone"))) {
            ModifiableValueMap valueMap = request.getResource().adaptTo(ModifiableValueMap.class);
            valueMap.put("isDone", true);
            request.getResourceResolver().commit();
        }
        else if ("true".equals(request.getParameter("delete"))) {
            request.getResourceResolver().delete(request.getResource());
            request.getResourceResolver().commit();
        }


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
