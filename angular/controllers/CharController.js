
function CharController($scope, $http, $rootScope, $q) {

  var url = 'http://api.guildwars2.com/v2/characters';
  $scope.APIKEY = 'F86BE23F-FCF1-FA42-A9F1-0D7BE7880214F63BD4B5-D2D4-44B1-86FE-5733FBF3B9A9';

	function pjsFromName(name) {
    return $http.get(url+'/'+name+'?access_token='+$scope.APIKEY).then(function(resp) { return resp.data; });
  };

	function getPjs() {
		return $http.get(url+'?access_token='+$scope.APIKEY).then(function(response){
      return $q.all(response.data.map(pjsFromName));
    });
	}

  getPjs().then(function(pjs) {
		$scope.chars = pjs;
	});

};

CharController.$inject = ['$scope', '$http', '$rootScope', '$q'];
