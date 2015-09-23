angular.module( 'todo.controllers.todolist', [ 'todo.services.authentication' ] )
    .controller( 'TodoListCtrl', function( $scope, $location, AuthenticationService ) {

        $scope.controls = {
            logout: function( e ) {
                AuthenticationService.logout()
                    .then( function() {
                        $location.path( '/login' );
                    } );
            }
        };

    } );