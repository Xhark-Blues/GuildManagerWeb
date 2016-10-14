
app.controller('CharController', [ '$scope', '$http', function($scope, $http) {

  var url = 'http://api.guildwars2.com/v2/characters';

  $scope.APIKEY = 'F86BE23F-FCF1-FA42-A9F1-0D7BE7880214F63BD4B5-D2D4-44B1-86FE-5733FBF3B9A9';
  //$scope.chars = 'null';
  $scope.getChars = function(){
    $http.get(url+'?access_token='+$scope.APIKEY).then(function(response){
      $scope.chars = response.data;
    });
  };

  $scope.getChar = function(name){
    $http.get(url+'/'+name+'?access_token='+$scope.APIKEY).then(function(response){
      $scope.char = response.data;
    });
  };
}]);
