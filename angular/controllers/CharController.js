
app.controller('CharController', [ '$scope', '$http', 'dao',function($scope, $http, dao) {

  var url = 'http://api.guildwars2.com/v2/characters';
  $scope.APIKEY = 'F86BE23F-FCF1-FA42-A9F1-0D7BE7880214F63BD4B5-D2D4-44B1-86FE-5733FBF3B9A9';
  //$scope.chars = 'null';
  dao.getChars().then( function(chrs){
      $scope.chars = chrs;
  });

  $scope.getChar = function(name) {
    dao.getCharsByName(name).then(function(chr) {
      console.log(chr);
      $scope.char = chr;
    });
  };


}]);
