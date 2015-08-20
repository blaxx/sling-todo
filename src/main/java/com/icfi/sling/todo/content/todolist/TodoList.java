package com.icfi.sling.todo.content.todolist;

import com.icfi.sling.todo.content.todolistitem.TodoListItem;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;

import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private List<Resource> items;
    private Resource resource;
    private ValueMap valueMap;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
        this.valueMap = resource.adaptTo(ValueMap.class);
        this.items = new ArrayList<Resource>();

        if (resource.getChild("items") != null) {
            for(Resource currentItemResource : resource.getChild("items").getChildren()) {
                items.add(currentItemResource);
            }
        }
    }

    public List<Resource> getItems() {
        return items;
    }

    public int getItemCount() {
        return items.size();
    }

    public int getItemsRemaining() {
        int itemsRemaining = 0;

        for (Resource currentItemResource : getItems()) {
            if (!currentItemResource.adaptTo(ValueMap.class).get("isDone", false)) {
                itemsRemaining++;
            }
        }

        return itemsRemaining;
    }
}
