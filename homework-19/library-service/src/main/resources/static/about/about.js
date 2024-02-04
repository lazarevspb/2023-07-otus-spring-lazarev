angular.module('app').controller('aboutController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8082/library';
    if (window.localStorage && window.localStorage.currentUser) {
        let currentUser = window.localStorage.currentUser;
        console.log(currentUser);
        console.log(currentUser.token);
    } else {
        console.log('No user data found');
    }

    var user = $localStorage.currentUser;
    if (user) {
        console.log(user.username);
        console.log(user.token);
    } else {
        console.log('No user data found');
    }
});
