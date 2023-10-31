angular.module('app').controller('BooksDetailsController', function ($scope, $http, $routeParams, $localStorage, $location) {
    const contextPath = 'http://localhost:8080/api/v1';

    $scope.getBook = function () {
        $http({
            url: contextPath + '/books/' + $routeParams.id,
            method: 'GET',
        }).then(function (response) {
            $scope.book = response.data;
        });
    };

    $scope.getBook();
});
