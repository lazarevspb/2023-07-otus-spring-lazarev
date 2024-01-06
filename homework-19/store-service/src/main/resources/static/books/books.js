angular.module('app').controller('bookController', function ($scope, $http,$location, $localStorage) {
    const contextPath = 'http://localhost:8080/api/v1';

    $scope.showProductsPage = function () {
        $http({
            url: contextPath + '/books',
            method: 'GET',
        }).then(function (response) {
            $scope.books = response.data;
        });
    };

    $scope.editBook = function (bookId) {
        $location.path('/edit/' + bookId);
    }

    $scope.getBookPage = function (bookId) {
        $location.path('/books/' + bookId);
    }

    $scope.getAddBookPage = function () {
        $location.path('/add/');
    }

    $scope.deleteBook = function (bookId) {
        $http({
            url: contextPath + '/books/' + bookId,
            method: 'DELETE',
        }).then(function (response) {
            $scope.showProductsPage();
            $location.path('/books');
        });
    }

    $scope.showProductsPage();
});
