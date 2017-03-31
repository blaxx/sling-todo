# Sling TODO

A TODO List application in the spirit of TODO MVC illustrating the basics of Sling based development.

## Installation

```
mvn -P autoInstallBundle clean install
```

## Local Environment Setup

The following instructions are applicable to all variants unless superseded by the variant's README.

* Retrieve the latest Standalone Application jar from https://sling.apache.org/downloads.cgi (note, this project was last tested using `org.apache.sling.launchpad-8-standalone.jar`)
* Run the jar
    * Once run try accessing localhost:8080 - a Sling welcome page should come up
* Visit the Sling Explorer at http://localhost:8080/.explorer.html
* Login using username admin password admin
* Create a new node named `todo` under the apps directory with a jcr:primaryType of `nt:folder`
* Create a new node named `install` under the apps/todo directory created in the prior step with a jcr:primaryType of `nt:folder`
* Run the build command above
    * After a successful build the application should be accessible under localhost:8080/content/todo.html
    * Login using username admin password admin
    
## Branches

### variant/pureJsp

The Pure JSP branch demonstrates a ToDo application written using nothing other than jsps, the page context properties 
exposed by Sling, and the jsp tags contained in Sling.

### variant/backingBeansAndServlets

In this variant logic which lived largely in JSP scriptlets in the variant/pureJsp branch is moved into backing Classes 
 and Servlets.  
 
### variant/adaptation

Much of the logic contained in the backing Classes and Servlets in the variant/backingBeansAndServlets branch is moved 
into domain objects to which Resources may be adapted.  These objects provide a domain specific API abstraction over 
the raw Resource objects.

### variant/modelAngular

This branch reworks the ToDo application changing it from an application relying on classic POSTs and page turns into 
a Single Page Application leveraging the Angular framework.  

### variant/slingAngular

This branch presents another take on the Angular Application variant making use of the OOB Sling POST servlet in 
lieu of custom servlets and models.  
    
## Misc Debugging

### Running in Debug Mode

```
java -Xmx384M -agentlib:jdwp=transport=dt_socket,address=30303,server=y,suspend=n -jar org.apache.sling.launchpad-7-standalone.jar
```

### /var/discovery Errors

If you are seeing errors in the error log indicating that `/var/discovery` can not be created, log into the explorer at http://localhost:8080/.explorer.html and create the resource `discovery` under the `var` resource with a `jcr:primaryType` of `sling:folder`.
   

