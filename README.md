# Bookmark service
Spring 5 WebFlux REST service demo

mongodb used
```shell
 docker run -p 27017:27017 -d mongodo
 ##optionally
 #docker logs -f <CONTAINER ID>
```
OpenAPI:
(verbose "README.md" - will be fixed)
```json
{
    "openapi": "3.0.1",
    "info": {
        "title": "OpenAPI definition",
        "version": "v0"
    },
    "servers": [
        {
            "url": "http://localhost:8080",
            "description": "Generated server url"
        }
    ],
    "paths": {
        "/api/v1/bookmarks/{id}": {
            "get": {
                "tags": [
                    "bookmark-controller"
                ],
                "operationId": "getById",
                "parameters": [
                    {
                        "name": "id",
                        "in": "path",
                        "required": true,
                        "schema": {
                            "type": "string"
                        }
                    }
                ],
                "responses": {
                    "200": {
                        "description": "OK",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "$ref": "#/components/schemas/Bookmark"
                                }
                            }
                        }
                    }
                }
            },
            "put": {
                "tags": [
                    "bookmark-controller"
                ],
                "operationId": "update",
                "parameters": [
                    {
                        "name": "id",
                        "in": "path",
                        "required": true,
                        "schema": {
                            "type": "string"
                        }
                    }
                ],
                "requestBody": {
                    "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/Bookmark"
                            }
                        }
                    },
                    "required": true
                },
                "responses": {
                    "201": {
                        "description": "Created",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "$ref": "#/components/schemas/Bookmark"
                                }
                            }
                        }
                    }
                }
            }
        },
        "/api/v1/login": {
            "post": {
                "tags": [
                    "authentication-rest"
                ],
                "operationId": "login",
                "requestBody": {
                    "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/AuthRequest"
                            }
                        }
                    },
                    "required": true
                },
                "responses": {
                    "200": {
                        "description": "OK",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "object"
                                }
                            }
                        }
                    }
                }
            }
        },
        "/api/v1/bookmarks": {
            "get": {
                "tags": [
                    "bookmark-controller"
                ],
                "operationId": "listSharedBookmarks",
                "responses": {
                    "302": {
                        "description": "Found",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "array",
                                    "items": {
                                        "$ref": "#/components/schemas/Bookmark"
                                    }
                                }
                            }
                        }
                    }
                }
            },
            "post": {
                "tags": [
                    "bookmark-controller"
                ],
                "operationId": "create",
                "requestBody": {
                    "content": {
                        "application/json": {
                            "schema": {
                                "$ref": "#/components/schemas/Bookmark"
                            }
                        }
                    },
                    "required": true
                },
                "responses": {
                    "201": {
                        "description": "Created",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "$ref": "#/components/schemas/Bookmark"
                                }
                            }
                        }
                    }
                }
            }
        },
        "/api/v1/bookmarks/user": {
            "get": {
                "tags": [
                    "bookmark-controller"
                ],
                "operationId": "listMyBookmarks",
                "responses": {
                    "302": {
                        "description": "Found",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "array",
                                    "items": {
                                        "$ref": "#/components/schemas/Bookmark"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        "/api/v1/bookmarks/user/shared": {
            "get": {
                "tags": [
                    "bookmark-controller"
                ],
                "operationId": "listMySharedBookmarks",
                "responses": {
                    "302": {
                        "description": "Found",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "array",
                                    "items": {
                                        "$ref": "#/components/schemas/Bookmark"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        "/api/v1/bookmarks/other": {
            "get": {
                "tags": [
                    "bookmark-controller"
                ],
                "operationId": "listMyOtherBookmarks",
                "responses": {
                    "302": {
                        "description": "Found",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "type": "array",
                                    "items": {
                                        "$ref": "#/components/schemas/Bookmark"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    },
    "components": {
        "schemas": {
            "Bookmark": {
                "type": "object",
                "properties": {
                    "id": {
                        "type": "string"
                    },
                    "url": {
                        "type": "string"
                    },
                    "shared": {
                        "type": "boolean"
                    },
                    "owner": {
                        "type": "string"
                    }
                }
            },
            "AuthRequest": {
                "type": "object",
                "properties": {
                    "username": {
                        "type": "string"
                    },
                    "password": {
                        "type": "string"
                    }
                }
            }
        }
    }
}
```