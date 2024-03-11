# REST Conventions

HTTP Methods:
* GET
  * Should only get data and should not modify anything.
  * Should use query parameters for sending in variables
  * Query parameters should not have any sensitive data in them like a password or security token.
* POST
  * Generally used for Creating resources. This is also usually the default go to for actions that don't fall under CRUD operations.
  * All parameters should be in the boy. While it is possible to use query parameters, for consistency you shouldn't
* PUT
  * Generally used for completely updating a resource
  * All parameters should be in the boy. While it is possible to use query parameters, for consistency you shouldn't
* DELETE
  * Used for deleting a resource
  * Generally doesn't have a body, and while the spec doesn't specifically forbid it Grails Command Objects don't support it, so don't send a body with a delete.
* PATCH
  * Generally used for partial updates of a Resource.
  * All parameters should be in the boy. While it is possible to use query parameters, for consistency you shouldn't

In Rest Controllers should represent the resources of your application and generally have CRUD actions:
* List Object under a resource `"/$controller/"(action: “index”, method: "GET")`
* Show a resource `"/$controller/$id"(action: "show", method: "GET")`
* Create/Save a resource `"/$controller"(action: "save", method: "POST")`
* Update a resource `"/$controller/$id"(action: "update", method: "PUT")`
* Delete a resource `"/$controller/$id"(action: "delete", method: "DELETE")`

For actions that fall outside traditional CRUD there isn't a set standard but what you should  follow is:
* For actions that affect one resource the action name should proceed with the id of the  resource like:
```
"/$controller/$id/$action"(method: "GET")
"/$controller/$id/$action"(method: "POST")
"/$controller/$id/$action"(method: "PUT")
"/$controller/$id/$action"(method: "DELETE")
```
* For actions that are general or affect many resources the action should be in place of the id:
```
"/$controller/$action"(method: "POST")
"/$controller/$action"(method: "PUT")
"/$controller/$action"(method: "GET")
"/$controller/$action"(method: "PATCH")
"/$controller/$action"(method: "DELETE")
```
