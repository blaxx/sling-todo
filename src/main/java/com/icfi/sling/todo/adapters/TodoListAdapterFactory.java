package com.icfi.sling.todo.adapters;

import com.icfi.sling.todo.model.list.TodoList;
import com.icfi.sling.todo.model.list.TodoListItem;
import com.icfi.sling.todo.model.list.impl.DefaultTodoList;
import com.icfi.sling.todo.model.list.impl.DefaultTodoListItem;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.adapter.AdapterFactory;
import org.apache.sling.api.resource.Resource;
import org.osgi.framework.Constants;

/**
 * Responsible for the adaptation of todo/content/todolist resources to TodoList Instances
 */
@Component
@Service(AdapterFactory.class)
@Properties({
        @Property(name = Constants.SERVICE_DESCRIPTION, value = "Sling TODO List Adapter Factory"),
        @Property(name = SlingConstants.PROPERTY_ADAPTABLE_CLASSES, value = {
                "org.apache.sling.api.resource.Resource"
        }),
        @Property(name = SlingConstants.PROPERTY_ADAPTER_CLASSES, value = {
                "com.icfi.sling.todo.model.list.TodoList",
                "com.icfi.sling.todo.model.list.TodoListItem"
        })
})
public class TodoListAdapterFactory implements AdapterFactory {

    @Override
    public <AdapterType> AdapterType getAdapter(Object adaptable, Class<AdapterType> type) {
        if (adaptable instanceof Resource) {
            return getResourceAdapter((Resource) adaptable, type);
        }

        return null;
    }

    public <AdapterType> AdapterType getResourceAdapter(Resource adaptableResource, Class<AdapterType> type) {
        if (type.equals(TodoList.class) && adaptableResource.isResourceType("todo/content/todolist")) {
            return (AdapterType) new DefaultTodoList(adaptableResource);
        }
        else if (type.equals(TodoListItem.class) && adaptableResource.isResourceType("todo/content/todolistitem")) {
            return (AdapterType) new DefaultTodoListItem(adaptableResource);
        }

        return null;
    }

}
