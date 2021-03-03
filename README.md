# Spring 5 WebFlux REST Bookmark service demo
 

features:
- user can sign in or login to the service
- user can 
    - create public or private bookmark
    - patch shared field in stored bookmark
    - list his bookmarks
    - list shared (public) bookmarks, where user's shared bookmarks can be excluded
- reactive mongodb with configured back pressure is used

```shell
 docker run -p 27017:27017 -d mongo
 ##optionally
 #docker logs -f <CONTAINER ID>
```

Please see [OpenAPI definition](./OpenApiDefinition.md) for api details, or better call `http://localhost:8080/v3/api-docs`.
