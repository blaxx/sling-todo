package com.icfi.sling.todo.content.todolist;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import javax.inject.Inject;
import java.util.List;

@Model(adaptables = Resource.class)
public class TodoList {

    @Inject @Optional
    private List<Resource> items;

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
