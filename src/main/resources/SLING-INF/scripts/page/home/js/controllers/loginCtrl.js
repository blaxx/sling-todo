angular.module( 'todo.controllers.login', [ 'todo.services.authentication' ] )
    .controller( 'LoginCtrl', function( $scope, $location, AuthenticationService ) {

        $scope.loginform = {
            username: "",
            password: "",
            submit: function( e ) {
                AuthenticationService.login( $scope.loginform.username, $scope.loginform.password )
                    .then( $location.path( '/todolist' ) );
            }
        };

    } );