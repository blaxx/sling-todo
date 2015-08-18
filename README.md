# Sling TODO

A TODO List application in the spirit of TODO MVC illustrating the basics of Sling based application development.

## Installation

```
mvn -P autoInstallBundle clean install
```

## Local Environment Setup

* Retrieve the latest Standalone Application jar from https://sling.apache.org/downloads.cgi
* Run the jar
    * Once run try accessing localhost:8080 - a Sling welcome page should come up
* Visit the Sling Explorer at http://localhost:8080/.explorer.html
* Login using username admin password admin
* Create a new node named `todo` under the apps directory with a jcr:primaryType of `nt:folder`
* Create a new node named `install` under the apps/todo directory created in the prior step with a jcr:primaryType of `nt:folder`
* Run the build command above
    * After a successful build the application should be accessible under localhost:8080/content/todo.html
    
Currently you must be logged in to use the TODO application.  To log in visit http://localhost:8080/.explorer.html and 
use username admin password admin.

