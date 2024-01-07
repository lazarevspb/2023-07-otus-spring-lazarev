angular.module('app').controller('ContentController', function ($scope, $http, $routeParams, $localStorage, $location) {
    const contextPath = 'http://localhost:8082/library/api/v1';

    $scope.getContent = function () {
        $http({
            url: contextPath + '/books/content/' + $routeParams.id,
            method: 'GET',
        }).then(function (response) {
            $scope.content = response.data.content;
        });
    };


    $scope.getContent();
});
