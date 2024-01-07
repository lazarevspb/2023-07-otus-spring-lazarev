(function ($localStorage, $scope) {

    if (window.localStorage && window.localStorage.currentUser) {
        // const user = window.localStorage.getItem('currentUser');
        const currentUser = window.localStorage.currentUser.username;
        console.log(currentUser);
    } else {
        console.log('No user data found');
    }

    angular
        .module('app', ['ngRoute', 'ngStorage'])
        .config(config);
    function config($routeProvider, $httpProvider) {


        $routeProvider
            .when('/', {
                templateUrl: 'home/home.html',
                controller: 'homeController'
            })
            .when('/books', {
                templateUrl: 'books/books.html',
                controller: 'bookController'
            })

            .when('/edit/:id', {
                templateUrl: 'edit/edit.html',
                controller: 'EditBookController'
            })
            .when('/books/:id', {
                templateUrl: 'details/details.html',
                controller: 'BooksDetailsController',
            })
            .when('/add/', {
                templateUrl: 'add/add.html',
                controller: 'AddBookController',
            })
            .when('/content/:id', {
                templateUrl: 'content/content.html',
                controller: 'ContentController',
            })
            .when('/login', {
                templateUrl: 'login/login.html',
                controller: 'LoginController',
            })
            .otherwise({
                redirectTo: '/store/'
            });
    }
})();

angular
    .module('app')
    .controller('indexController', function ($localStorage, $scope, $http, $location) {
        $scope.isUserLoggedIn = function () {
            if ($localStorage.currentUser) {
                $scope.currentUserName = $localStorage.currentUser.username;
                return true;
            } else {
                return false;
            }
        };

        // if ($localStorage.currentUser.username) {
        //     $scope.currentUserName = $localStorage.currentUser.username;
        // }

        $scope.tryToLogout = function () {
            $scope.clearUser();
            // $localStorage.currentUser.username = null;
            // $localStorage.currentUser.token = null;

            $location.path('/');
            if ($scope.currentUserName) {
                $scope.currentUserName = null;
            }
        };

        $scope.clearUser = function () {
            delete $localStorage.currentUser;
            $http.defaults.headers.common.Authorization = '';
        };

});
