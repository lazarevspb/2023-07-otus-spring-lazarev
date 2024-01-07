angular.module('app').controller('LoginController', function ($scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8082/auth';

    if (window.localStorage && window.localStorage.currentUser) {
        // const user = window.localStorage.getItem('currentUser');
        const currentUser = window.localStorage.currentUser.token;
        console.log(currentUser);
    } else {
        console.log('No user data found');
    }

    $scope.tryToAuth = function () {

        $http.post(contextPath + '/auth/', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.currentUser = {username: $scope.user.username, token: response.data.token};

                    $scope.currentUserName = $scope.user.username;
                    window.localStorage.currentUser = $scope.user.username;

                    $scope.user.username = null;
                    $scope.user.password = null;
                }
            }, function errorCallback(response) {
            });
    };


    // $scope.tryToLogout = function () {
    //     $scope.clearUser();
    //
    //     $http.post(contextPath + '/api/v1/cart')
    //         .then(function successCallback(response) {
    //             $localStorage.happyCartUuid = response.data;
    //         });
    //
    //     $location.path('/');
    //     if ($scope.user.username) {
    //         $scope.user.username = null;
    //     }
    //     if ($scope.user.password) {
    //         $scope.user.password = null;
    //     }
    // };

    $scope.clearUser = function () {
        delete $localStorage.currentUser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.isUserLoggedIn = function () {
        if ($localStorage.currentUser) {
            return true;
        } else {
            return false;
        }
    };

});