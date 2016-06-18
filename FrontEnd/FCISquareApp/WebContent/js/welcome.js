var step=0;
window.onload = function() {
    slide();
};

function slide() {
//       document.body.style.backgroundImage="";
       document.body.setAttribute("style","background-image:url('/FCISquareApp/images/img"+step+".jpg');" +
       		"background-repeat: no-repeat;"+
       		"background-size: 100%;");
       
       step++;
       step%=4;
       console.log(step);
       setTimeout(slide, 4000);
}