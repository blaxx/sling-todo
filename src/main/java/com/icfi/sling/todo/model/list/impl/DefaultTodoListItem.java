package com.icfi.sling.todo.model.list.impl;

import com.icfi.sling.todo.model.list.TodoListItem;
import org.apache.sling.api.resource.*;

public class DefaultTodoListItem implements TodoListItem {

    private final Resource resource;
    private final ValueMap valueMap;

    public DefaultTodoListItem(Resource resource) {
        this.resource = resource;
        this.valueMap = resource.adaptTo(ValueMap.class);
    }

    public boolean isDone() {
        return valueMap.get("isDone", false);
    }

    public String getTitle() {
        return valueMap.get("title", "Title");
    }

    public void delete() throws PersistenceException {
        ResourceResolver resourceResolver = resource.getResourceResolver();
        resourceResolver.delete(resource);
        resourceResolver.commit();
        //TODO: Handle further calls to this object after the deletion of the underlying resource
        //TODO: Handle refreshing of the parent list object
    }

    @Override
    public String getPath() {
        return resource.getPath();
    }

    public void complete() throws PersistenceException {
        ModifiableValueMap modifiableValueMap = resource.adaptTo(ModifiableValueMap.class);
        modifiableValueMap.put("isDone", true);
        resource.getResourceResolver().commit();
    }
}
