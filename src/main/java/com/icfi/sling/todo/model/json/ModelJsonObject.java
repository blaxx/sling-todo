package com.icfi.sling.todo.model.json;

import com.icfi.sling.todo.model.list.TodoList;
import com.icfi.sling.todo.model.list.TodoListItem;
import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class ModelJsonObject {

    public static JSONObject forTodoListItem(TodoListItem todoListItem) throws JSONException {
        return new JSONObject()
                .put("title", todoListItem.getTitle())
                .put("done", todoListItem.isDone())
                .put("path", todoListItem.getPath());
    }

    public static JSONObject forTodoList(TodoList todoList) throws JSONException {
        JSONObject retObject = new JSONObject();

        retObject.put("itemCount", todoList.getItemCount());
        retObject.put("itemsRemaining", todoList.getItemsRemaining());
        retObject.put("path", todoList.getPath());

        Collection<JSONObject> items = new ArrayList<JSONObject>();

        for (TodoListItem currentItem : todoList.getItems()) {
            items.add(forTodoListItem(currentItem));
        }

        retObject.put("items", items);

        return retObject;
    }

}
