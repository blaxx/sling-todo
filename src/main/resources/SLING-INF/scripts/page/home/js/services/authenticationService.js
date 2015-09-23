angular.module( 'todo.services.authentication', [] )
    .service( 'AuthenticationService', function( $http ) {

        var loginPath = '/j_security_check';
        var logoutPath = '/system/sling/logout.html';

        var authenticated = false;

        this.isAuthenticated = function() {
            return authenticated;
        };

        this.login = function( username, password ) {

            /*
            return $http( {
                method: 'POST',
                url: loginPath,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'},
                transformRequest: function(obj) {
                    var str = [];
                    for(var p in obj)
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    return str.join("&");
                },
                data: { "j_username": username, "j_password": password }
            } )
                .then( function( response ) {

                    if ( response.status < 400 ) {
                        authenticated = true;
                    }
                    else {
                        throw new Error( 'Authentication Failure' );
                    }

                } );
*/


            return $http.post( loginPath, {
                "j_username": username,
                "j_password": password
            } )
                .then( function( response ) {

                    if ( Number( response.status ) < 400 ) {
                        authenticated = true;
                    }
                    else {
                        throw new Error( 'Authentication Failure' );
                    }

                } );

        };

        this.logout = function() {

            $http.post( logoutPath )
                .then( function( response ) {
                    if ( response.status < 400 ) {
                        authenticated = false;
                    }
                    else {
                        throw new Error( 'Logout Failure' );
                    }
                } );

        };

    } );