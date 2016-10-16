
var app = angular.module("characters", ["ngRoute"]);

app.service('dao', dao);
app.controller('Charcontroller', CharController);
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
