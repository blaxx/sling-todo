package com.icfi.sling.todo.content.todolist;

import com.icfi.sling.todo.model.list.TodoList;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SlingServlet(
        resourceTypes = "todo/content/todolist",
        methods = "POST"
)
public class NewListItemServlet extends SlingAllMethodsServlet {

    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        String newTitle = request.getParameter("title");

        if (newTitle == null || newTitle.equals("")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("TODO item title is a required field");
        }

        TodoList todoList = request.getResource().adaptTo(TodoList.class);

        todoList.add(newTitle);
    }

}
