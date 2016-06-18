var x="Dear user, i'm sorry to tell you that there is something wrong in rendiring this page, may be you entered or submitted or ask for something wrong, So you can go back and contiunue what you were doing if you logged in before.";
var step=0;
window.onload = function() {
    writeSentance();
}

function writeSentance() {
       if(step<x.length){
            document.getElementById("demo").innerHTML=document.getElementById("demo").innerHTML + x[step];
       step++;
       setTimeout(writeSentance, 60);
       }
       console.log(step);
       console.log(x[step]);

}



