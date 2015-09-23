package com.icfi.sling.todo.content.todolist;

import com.icfi.sling.todo.model.json.ModelJsonObject;
import com.icfi.sling.todo.model.list.TodoList;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.commons.json.JSONException;

import javax.servlet.ServletException;
import java.io.IOException;

@SlingServlet(
        resourceTypes = "todo/content/todolist",
        methods = "GET",
        selectors = "model",
        extensions = "json"
)
public class TodoListModelServlet extends SlingSafeMethodsServlet {

    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        TodoList todoList = request.getResource().adaptTo(TodoList.class);

        try {
            response.getWriter().write(ModelJsonObject.forTodoList(todoList).toString());
        } catch (JSONException e) {
            throw new ServletException(e);
        }
    }

}
