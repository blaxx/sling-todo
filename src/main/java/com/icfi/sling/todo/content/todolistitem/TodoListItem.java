package com.icfi.sling.todo.content.todolistitem;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class TodoListItem {

    @Inject @Default(booleanValues = false)
    private Boolean isDone;

    @Inject @Default(values = "Title")
    private String title;

    public boolean isDone() {
        return isDone;
    }

    public String getTitle() {
        return title;
    }

}
