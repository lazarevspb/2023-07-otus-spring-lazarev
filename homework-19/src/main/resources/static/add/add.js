angular.module('app').controller('AddBookController', function ($scope, $http, $routeParams, $localStorage, $location) {
    const contextPath = 'http://localhost:8080/api/v1';

    $scope.addBook = function () {
        $http({
            url: contextPath + '/books',
            method: 'POST',
            data: $scope.book
        }).then(function (response) {
            $location.path('/books');
        }).catch(function (error) {
            console.error('Error updating book:', error);
        });
    };

    $scope.addBook();
});
