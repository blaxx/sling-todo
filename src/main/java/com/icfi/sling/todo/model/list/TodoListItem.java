package com.icfi.sling.todo.model.list;

import org.apache.sling.api.resource.PersistenceException;

public interface TodoListItem {

    boolean isDone();

    void complete() throws PersistenceException;

    String getTitle();

    void delete() throws PersistenceException;

    String getPath();

}
