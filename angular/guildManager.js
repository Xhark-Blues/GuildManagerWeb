
var app = angular.module("characters", ["ngRoute"]);

app.config(function($routeProvider) {
    console.log("router");
    $routeProvider.when("/char1", {
        templateUrl : "../characters/charSummary.html",
        controller : "CharController"
    });
});
