
$(document).ready(function(){

  $('.dropContent').hide().removeClass('.dropContent');

  $('.dropBtn').hover(
    function() {
      console.log("Down");
      $('ul', this).stop().slideDown(150);
    },
    function () {
      $('ul', this).stop().slideUp(250);
    }
  );

});
