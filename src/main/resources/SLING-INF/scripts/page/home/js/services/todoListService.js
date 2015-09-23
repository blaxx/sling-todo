angular.module( 'todo.services.todolist', [] )
    .service( 'TodoListService', function( $http ) {

        this.getTodoList = function( path ) {
            return $http.get( path + '.model.json' )
                .then( function( response ) {
                    return response.data;
                } );
        };

        this.addTodoListItem = function( todoList, title ) {

            return $http.post( todoList.path, { title: title } )
                .then( function( response ) {
                    if ( response.status >= 400 ) {
                        throw new Error( 'Error creating new Item' );
                    }
                } );

        };

        this.deleteTodoListItem = function( todoListItem ) {

            return $http.delete( todoListItem.path )
                .then( function( response ) {
                    if ( response.status >= 400 ) {
                        throw new Error( 'Error deleting Item ' + todoListItem.path );
                    }
                } );

        };

        this.completeTodoListItem = function( todoListItem ) {

            return $http.put( todoListItem.path, { done: true } )
                .then( function( response ) {
                    if ( response.status >= 400 ) {
                        throw new Error( 'Error completing Item ' + todoListItem.path );
                    }
                } );

        };

    } );