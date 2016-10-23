
$(document).ready(function(){
  console.log("hoverholi");
  $('.dropBtn').hover(
    function() {
      $('ul', this).stop().slideDown(150);
    },
    function () {
      $('ul', this).stop().slideUp(250);
    }
  );
});
