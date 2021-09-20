const liste = document.querySelectorAll(".inputfelt input");

Array.prototype.forEach.call(liste, function(item){

    item.addEventListener("focus", function (){
        this.previousElementSibling.style.display = "block";
    })
    item.addEventListener("blur", function (){
        if(this.value.length === 0){
            this.previousElementSibling.style.display = "none";
        }
    })
})
