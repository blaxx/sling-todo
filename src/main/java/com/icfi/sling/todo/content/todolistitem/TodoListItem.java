package com.icfi.sling.todo.content.todolistitem;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

public class TodoListItem {

    private Resource resource;
    private ValueMap valueMap;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
        this.valueMap = resource.adaptTo(ValueMap.class);
    }

    public boolean isDone() {
        return valueMap.get("isDone", false);
    }

    public String getTitle() {
        return valueMap.get("title", "Title");
    }
}
