$(document).ready(function(){
  $('.dropBtn').hover(
    function() {
      $('ul', this).stop().slideDown(150);
    },
    function () {
      $('ul', this).stop().slideUp(250);
    }
  );
});
