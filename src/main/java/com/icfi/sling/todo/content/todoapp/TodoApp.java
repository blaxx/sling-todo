package com.icfi.sling.todo.content.todoapp;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;

@Model(adaptables = Resource.class)
public class TodoApp {

    @Inject
    ResourceResolver resourceResolver;

    public boolean isAnonymous() {
        return resourceResolver.getUserID().equals("anonymous");
    }
}
