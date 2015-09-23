angular.module( 'todoApp', [ 'ui.router', 'todo.controllers.todolist', 'todo.controllers.login' ] )
    .config( function( $stateProvider, $urlRouterProvider ) {

        $urlRouterProvider.otherwise( '/login' );

        $stateProvider
            .state( 'login', {
                url: "/login",
                templateUrl: "login.html",
                controller: "LoginCtrl"
            } )
            .state( 'todolist', {
                url: "/todolist",
                templateUrl: "todolist.html",
                controller: "TodoListCtrl"
            } );

    } );