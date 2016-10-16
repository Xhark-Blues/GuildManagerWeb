
var app = angular.module("characters", ["ngRoute"]);

app.service('dao', dao);
app.controller('CharController', CharController);
app.config(function($routeProvider) {
    $routeProvider.when("/char1", {
        templateUrl : "characters/charSummary.html",
        controller : "CharController"
    });
});
/*.otherwise({
    templateUrl : "../characters/charSummary.html",
    controller : "CharController"
})*/
