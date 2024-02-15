angular.module('app').controller('RegistrationController', function ($scope, $http, $localStorage, $location) {
    const contextPath = 'http://localhost:8082/auth';

    $scope.tryToRegistration = function () {

        $http.post(contextPath + '/auth/registration', $scope.user)
            .then(function successCallback(response) {
                if (response.data) {
                    $location.path('/login');

                    console.log(response.data);
                }
            }, function errorCallback(response) {
            });
    };




    $scope.isUserLoggedIn = function () {
        if ($localStorage.currentUser) {
            return true;
        } else {
            return false;
        }
    };

});