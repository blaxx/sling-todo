angular.module( 'todo.services.todolist', [] )
    .service( 'TodoListService', function( $http ) {

        this.getTodoList = function( path ) {
            return $http.get( path + '.2.json' )
                .then( function( response ) {
                    var todoList = {
                        path: path,
                        items: [],
                        itemsRemaining: 0
                    };

                    if ( response.data && response.data.items ) {
                        for( var currentItemsKey in response.data.items ) {
                            if ( response.data.items.hasOwnProperty( currentItemsKey ) && currentItemsKey.indexOf( 'item' ) === 0 ) {
                                var currentItem = response.data.items[ currentItemsKey ];
                                todoList.items.push( {
                                    path: path + '/items/' + currentItemsKey,
                                    title: currentItem.title,
                                    done: currentItem.isDone
                                } );

                                if ( !currentItem.isDone ) {
                                    todoList.itemsRemaining += 1;
                                }
                            }
                        }
                    }

                    todoList.itemCount = todoList.items.length;

                    return todoList;
                } );
        };

        this.addTodoListItem = function( todoList, title ) {

            return $http.post( todoList.path + '/items/*', {
                title: title,
                "sling:resourceType": "todo/content/todolistitem",
                "isDone": false,
                "isDone@TypeHint": "Boolean",
                ":nameHint": "item" + Date.now()
                //TODO: Handle missing items path
            } )
                .then( function( response ) {
                    if ( response.status >= 400 ) {
                        throw new Error( 'Error creating new Item' );
                    }
                } );

        };

        this.deleteTodoListItem = function( todoListItem ) {

            return $http.post( todoListItem.path, { ":operation" : "delete" } )
                .then( function( response ) {
                    if ( response.status >= 400 ) {
                        throw new Error( 'Error deleting Item ' + todoListItem.path );
                    }
                } );

        };

        this.completeTodoListItem = function( todoListItem ) {

            return $http.post( todoListItem.path, {
                isDone: true,
                "isDone@TypeHint": "Boolean"
            } )
                .then( function( response ) {
                    if ( response.status >= 400 ) {
                        throw new Error( 'Error completing Item ' + todoListItem.path );
                    }
                } );

        };

    } );