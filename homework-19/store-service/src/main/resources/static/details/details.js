angular.module('app').controller('BooksDetailsController', function ($scope, $http, $routeParams, $localStorage, $location) {
    const contextPath = 'http://localhost:8082/store/api/v1';

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
