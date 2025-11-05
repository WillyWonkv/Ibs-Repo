const videoSide = document.querySelectorAll(".boxsidebar" )
 
const videoMap = {

    "dtmf" : "https://www.youtube.com/embed/oB_jnvbtrg0",

};

const fotoMap = {

    "bad bunny" : "/css_course/project_2/resources/badbunny.jpg",
    "tainy" : "/css_course/project_2/resources/tainy.jpg",
    "lil nas x" : "/css_course/project_2/resources/lilnas.jpg",

};

const descMap = {



};


videoSide.forEach(video => {

    video.addEventListener("click", function(event){

        event.preventDefault();

        const titoloVideo = document.getElementById("titoloVideo");
        const nomeProfilo = document.getElementById("nomeProfilo")
        const fotoprofilo = document.getElementById("fotoprofilo");
        const autoreSide = this.querySelector(".autoreSide").textContent.trim();
        const titoloSide = this.querySelector(".titlestyle").textContent.trim();
        const player = document.getElementById("player");

        titoloVideo.innerHTML = titoloSide;
        nomeProfilo.innerHTML = autoreSide;

        if(fotoMap[autoreSide.toLowerCase()]){
            fotoprofilo.src = fotoMap[autoreSide.toLowerCase()];
        }else{
            fotoprofilo.src = "/css_course/project_2/resources/blank-profile-picture.png";
        }

        if(videoMap[titoloSide.toLowerCase()]){
            player.src = videoMap[titoloSide.toLowerCase()];
        }else{
            player.src = "";
        }

    });

});