
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
    $scope.stats = new Map();

    //Armor
    for( i = 2; i<=7; i++){
      var itemId = $scope.chars[$scope.selectedChar].equipment[i].id;

      dao.getItemById(itemId).then( function( item ) {
        var attributes = item.details.infix_upgrade.attributes;
        for(i in attributes){
          var value = attributes[i].modifier;
          var key = attributes[i].attribute;
          if( $scope.stats.has(key) ){
            value = $scope.stats.get(key) + value;
          }
          $scope.stats.set(key,  value);
        }
      });
    }


  };
};

CharController.$inject = ['$scope', '$http', 'dao'];
