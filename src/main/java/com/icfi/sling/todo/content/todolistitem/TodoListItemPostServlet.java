package com.icfi.sling.todo.content.todolistitem;

import com.icfi.sling.todo.model.list.TodoListItem;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
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

        TodoListItem todoListItem = request.getResource().adaptTo(TodoListItem.class);

        if (todoListItem != null) {
            if ("true".equals(request.getParameter("isDone"))) {
                todoListItem.complete();
            }
            else if ("true".equals(request.getParameter("delete"))) {
                todoListItem.delete();
            }
        }

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
