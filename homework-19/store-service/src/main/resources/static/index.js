(function ($localStorage) {

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
            .otherwise({
                redirectTo: '/'
            });
    }
})();

angular.module('app').controller('indexController', function () {
});
