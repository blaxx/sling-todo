package com.icfi.sling.todo.model.list.impl;

import com.icfi.sling.todo.model.list.TodoList;
import com.icfi.sling.todo.model.list.TodoListItem;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;

import java.util.*;

public class DefaultTodoList implements TodoList {

    private final Resource resource;

    public DefaultTodoList(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void delete(TodoListItem item) throws PersistenceException {
        item.delete();
    }

    @Override
    public TodoListItem add(String title) throws PersistenceException {

        Resource itemsResource = resource.getChild("items");

        if (itemsResource == null) {
            Map<String, Object> properties = new HashMap<String, Object>();
            properties.put("jcr:primaryType", "nt:unstructured");

            itemsResource = resource.getResourceResolver().create(resource, "items", properties);
            resource.getResourceResolver().commit();
        }

        Map<String, Object> newItemProperties = new HashMap<String, Object>();
        newItemProperties.put("jcr:primaryType", "nt:unstructured");
        newItemProperties.put("title", title);
        newItemProperties.put("sling:resourceType", "todo/content/todolistitem");

        Resource newItemResource = resource.getResourceResolver().create(itemsResource, "item" + System.currentTimeMillis(), newItemProperties);
        resource.getResourceResolver().commit();

        return new DefaultTodoListItem(newItemResource);

    }

    @Override
    public Collection<TodoListItem> getItems() {
        List<TodoListItem> todoListItems = new ArrayList<TodoListItem>();

        Resource itemsResource = this.resource.getChild("items");

        if (itemsResource != null) {
            for (Resource currentItem : itemsResource.getChildren()) {
                todoListItems.add(currentItem.adaptTo(TodoListItem.class));
            }
        }

        return todoListItems;
    }

    @Override
    public int getItemCount() {
        return getItems().size();
    }

    @Override
    public int getItemsRemaining() {
        int itemsRemaining = 0;

        for (TodoListItem currentListItem : getItems()) {
            if (!currentListItem.isDone()) {
                itemsRemaining += 1;
            }
        }

        return itemsRemaining;
    }

    @Override
    public String getPath() {
        return resource.getPath();
    }

}
