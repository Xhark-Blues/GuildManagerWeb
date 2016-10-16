
function dao($http, $q){

  var url = 'http://api.guildwars2.com/v2/characters';
  APIKEY = 'F86BE23F-FCF1-FA42-A9F1-0D7BE7880214F63BD4B5-D2D4-44B1-86FE-5733FBF3B9A9';



  function getCharsByName(name) {
    return $http.get(url+'/'+name+'?access_token='+APIKEY).then(function(response){
      return $q.all(response.data);
    });
  };

  this.getChars = function(){
    return $http.get(url+'?access_token='+APIKEY).then(function(response){
      return $q.all(response.data.map(getCharsByName));
    });
  };
};

dao.$inject = ['$http', '$q', '$rootScope'];
