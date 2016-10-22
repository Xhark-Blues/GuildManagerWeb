
function CharController($scope, $http, dao) {

  var url = 'http://api.guildwars2.com/v2/characters';
  $scope.chars = [];
  $scope.creds = [];
  dao.getCredentials().then( function(res){
      $scope.creds = res;
      dao.getChars($scope.creds.APIKEY).then( function(chrs){
          $scope.chars = chrs;
      });
  });


  $scope.selectChar = function selectChar(index){
    $scope.selectedChar = index;
    console.log($scope.chars[$scope.selectedChar].name);
  };
};

CharController.$inject = ['$scope', '$http', 'dao'];
