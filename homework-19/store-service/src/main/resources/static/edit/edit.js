angular.module('app').controller('EditBookController', function ($scope, $http, $routeParams, $localStorage, $location) {
    const contextPath = 'http://localhost:8080/api/v1';

    $scope.fetchBookData = function () {
        $http({
            url: contextPath + '/books/' + $routeParams.id,
            method: 'GET',
        }).then(function (response) {
            $scope.book = response.data;
        });
    };

    $scope.updateBook = function () {
        $http({
            url: contextPath + '/books',
            method: 'PUT',
            data: $scope.book
        }).then(function (response) {
            $location.path('/books');
        }).catch(function (error) {
            console.error('Error updating book:', error);
        });
    };


    $scope.fetchBookData();
});
