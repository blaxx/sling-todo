angular.module( 'todoApp', [ 'ui.router', 'todo.controllers.todolist', 'todo.controllers.login', 'todo.directives.todolist' ] )
    .config( function( $stateProvider, $urlRouterProvider, $httpProvider ) {

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

        $httpProvider.defaults.transformRequest = function( data ) {

            if (typeof data === 'object') {
                var encodedParts = [];
                for (var currentKey in data) {
                    if (data.hasOwnProperty(currentKey)) {
                        encodedParts.push(encodeURIComponent(currentKey) + '=' + encodeURIComponent(data[currentKey]));
                    }
                }
                return encodedParts.join("&");
            }

            return data;

        };

        $httpProvider.defaults.headers.post[ 'Content-Type' ] = 'application/x-www-form-urlencoded';
        $httpProvider.defaults.headers.put[ 'Content-Type' ] = 'application/x-www-form-urlencoded';

    } );