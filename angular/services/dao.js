
function dao($http, $q){

  var url = 'http://api.guildwars2.com/v2/characters';
  this.getCredentials = function() {
    return $http.get('json/credentials.json').then(function(response) {
      return $q.all(response.data);
  });

  }

  function getCharsByName(name,KEY) {
    return $http.get(url+'/'+name+'?access_token='+KEY).then(function(response){
      return $q.all(response.data);
    });
  };

  this.getChars = function(KEY){
    console.log(KEY);
    return $http.get(url+'?access_token='+KEY).then(function(response){
      return $q.all(response.data.map(function(x){
          return getCharsByName(x, KEY);
        }
      ));
    });
  };

};

dao.$inject = ['$http', '$q', '$rootScope'];
