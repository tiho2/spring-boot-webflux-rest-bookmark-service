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
        "/api/v1/signin": {
            "post": {
                "tags": [
                    "authentication-controller"
                ],
                "operationId": "create",
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
                    "201": {
                        "description": "Created",
                        "content": {
                            "*/*": {
                                "schema": {
                                    "$ref": "#/components/schemas/User"
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
                    "authentication-controller"
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
        "/api/v1/bookmarks/": {
            "post": {
                "tags": [
                    "bookmark-controller"
                ],
                "operationId": "update",
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
        "/api/v1/bookmarks/{id}/{shared}": {
            "patch": {
                "tags": [
                    "bookmark-controller"
                ],
                "operationId": "patchBookmark",
                "parameters": [
                    {
                        "name": "id",
                        "in": "path",
                        "required": true,
                        "schema": {
                            "type": "string"
                        }
                    },
                    {
                        "name": "shared",
                        "in": "path",
                        "required": true,
                        "schema": {
                            "type": "boolean"
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
            }
        },
        "/api/v1/bookmarks": {
            "get": {
                "tags": [
                    "bookmark-controller"
                ],
                "operationId": "listBookmarks",
                "parameters": [
                    {
                        "name": "scope",
                        "in": "query",
                        "required": true,
                        "schema": {
                            "type": "string"
                        }
                    },
                    {
                        "name": "scopeDetail",
                        "in": "query",
                        "required": true,
                        "schema": {
                            "type": "string"
                        }
                    }
                ],
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
            }
        }
    },
    "components": {
        "schemas": {
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
            },
            "GrantedAuthority": {
                "type": "object",
                "properties": {
                    "authority": {
                        "type": "string"
                    }
                }
            },
            "User": {
                "type": "object",
                "properties": {
                    "username": {
                        "type": "string"
                    },
                    "enabled": {
                        "type": "boolean"
                    },
                    "roles": {
                        "type": "array",
                        "items": {
                            "type": "string",
                            "enum": [
                                "ROLE_USER",
                                "ROLE_ADMIN"
                            ]
                        }
                    },
                    "accountNonExpired": {
                        "type": "boolean"
                    },
                    "authorities": {
                        "type": "array",
                        "items": {
                            "$ref": "#/components/schemas/GrantedAuthority"
                        }
                    },
                    "accountNonLocked": {
                        "type": "boolean"
                    },
                    "credentialsNonExpired": {
                        "type": "boolean"
                    }
                }
            },
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
            }
        }
    }
}
```