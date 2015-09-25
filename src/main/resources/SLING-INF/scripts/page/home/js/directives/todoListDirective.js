angular.module( 'todo.directives.todolist', [ 'todo.services.todolist' ] )
    .directive( 'todoList', function( $http, $log, TodoListService ) {

        return {
            restrict: "E",
            transclude: false,
            replace: false,
            templateUrl: "todolistDirective.html",
            scope: {
                listResource: "@"
            },
            link: function( scope, element, attrs ) {

                var refreshList = function() {
                    TodoListService.getTodoList( scope.listResource )
                        .then( function( list ) {
                            scope.todolist = list;
                        } );
                };

                scope.controls = {
                    addItemForm: {
                        title: "",
                        addItem: function( e ) {
                            TodoListService.addTodoListItem( scope.todolist, scope.controls.addItemForm.title )
                                .then( refreshList )
                                .catch( $log.error );
                        }
                    },
                    deleteItemForm: {
                        deleteItem: function( item ) {
                            TodoListService.deleteTodoListItem( item )
                                .then( refreshList )
                                .catch( $log.error );
                        }
                    },
                    completeItemForm: {
                        completeItem: function( item ) {
                            TodoListService.completeTodoListItem( item )
                                .then( refreshList )
                                .catch( $log.error );
                        }
                    }
                };

                refreshList();

            }
        };

    } );