# Sling TODO

A TODO List application in the spirit of TODO MVC illustrating the basics of Sling based application development.

## Installation

```
mvn -P autoInstallBundle clean install
```

## Local Environment Setup

* Retrieve the latest Standalone Application jar from https://sling.apache.org/downloads.cgi (note, this project was last tested using `org.apache.sling.launchpad-7-standalone.jar`)
* Run the jar
    * Once run try accessing localhost:8080 - a Sling welcome page should come up
* Visit the Sling Explorer at http://localhost:8080/.explorer.html
* Login using username admin password admin
* Create a new node named `todo` under the apps directory with a jcr:primaryType of `nt:folder`
* Create a new node named `install` under the apps/todo directory created in the prior step with a jcr:primaryType of `nt:folder`
* Run the build command above
    * After a successful build the application should be accessible under localhost:8080/content/todo.html
    * Login using username admin password admin
    
## Misc Debugging

### Running in Debug Mode

```
java -Xmx384M -agentlib:jdwp=transport=dt_socket,address=30303,server=y,suspend=n -jar org.apache.sling.launchpad-7-standalone.jar
```

### /var/discovery Errors

If you are seeing errors in the error log indicating that `/var/discovery` can not be created, log into the explorer at http://localhost:8080/.explorer.html and create the resource `discovery` under the `var` resource with a `jcr:primaryType` of `sling:folder`.
   

