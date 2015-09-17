package com.icfi.sling.todo.model.list;

import org.apache.sling.api.resource.PersistenceException;

import java.util.Collection;

public interface TodoList {

    void delete(TodoListItem item) throws PersistenceException;

    TodoListItem add(String title) throws PersistenceException;

    Collection<TodoListItem> getItems();

    int getItemCount();

    int getItemsRemaining();

    String getPath();

}
