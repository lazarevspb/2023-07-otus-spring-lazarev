angular.module('app').controller('bookController', function ($scope, $http,$location, $localStorage) {
    const contextPath = 'http://localhost:8082/library/api/v1';
    let token = null;
    if ($localStorage.currentUser && $localStorage.currentUser.token) {
        let currentUser = $localStorage.currentUser;
        token = currentUser.token;
    } else {
        console.log('No user data found');
    }

    $scope.showProductsPage = function () {
        if (token){
            $http({
                url: contextPath + '/books',
                method: 'GET',
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            }).then(function (response) {
                $scope.books = response.data;
            });
        } else {
            $http({
                url: contextPath + '/books',
                method: 'GET',
            }).then(function (response) {
                $scope.books = response.data;
            });
        }
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

    $scope.isUserLoggedIn = function () {
        if ($localStorage.currentUser) {
            $scope.currentUserName = $localStorage.currentUser.username;
            return true;
        } else {
            return false;
        }
    };

    $scope.showProductsPage();
});
