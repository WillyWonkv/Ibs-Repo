const videoMap = {

    "dtmf" : "https://www.youtube.com/embed/oB_jnvbtrg0",

};

const fotoMap = {

    "bad bunny" : "/css_course/project_2/resources/badbunny.jpg",
    "tainy" : "/css_course/project_2/resources/tainy.jpg",
    "lil nas x" : "/css_course/project_2/resources/lilnas.jpg",

};

const descMap = {

    "dtmf": `BAD BUNNY - DtMF
            http://debitirarmasfotos.com

            Más de Bad Bunny:
            nadie sabe lo que va a pasar mañana:    • BAD BUNNY -  NADIE SABE (Visualizer) | nad...  
            UN VERANO SIN TI:    • Bad Bunny - Moscow Mule (Video Oficial) | ...  
            EL ÚLTIMO TOUR DEL MUNDO:    • BAD BUNNY x ROSALÍA - LA NOCHE DE ANOCHE (...  

            Sigue a Bad Bunny:
            Spotify: https://open.spotify.com/artist/4q3ew...
            Apple Music:   / bad-bunny  
            Instagram:   / badbunnypr​  
            TikTok:   / badbunny  

            #DeBÍTiRARMáSFOToS​ #DtMF #BadBunny
            © 2025 Rimas Entertainment`,

    "mojabi ghost" : `  
            "DATA" YA DISPONIBLE EN TODAS LAS PLATAFORMAS DIGITALES: https://ffm.to/tainy-data

            Follow Me On Social Media:
            Facebook:   / tainy-199594.  .
            Instagram:   / tainy  
            TikTok: https://www.tiktok.com/@tainy?lang=en
            Twitter:   / tainy  
            Spotify: https://sptfy.com/Oa9J`,
        
};

const titoloVideo = document.getElementById("titoloVideo");
const nomeProfilo = document.getElementById("nomeProfilo");
const fotoprofilo = document.getElementById("fotoprofilo");
const player = document.getElementById("player");
const playerContainer = document.getElementById("playercontainer");
const descrizione = document.getElementById("descrizione");
const searchbutton = document.getElementById("searchbutton");
const searchbar = document.getElementById("searchbar");
const videoSide = document.querySelectorAll(".boxsidebar");

//click su video side
videoSide.forEach(video => {

    video.addEventListener("click", function(event){

        event.preventDefault();

        changeVideo(video);

    });

});

//press enter
searchbar.addEventListener("keydown", function(event){

    if(event.key === "Enter"){

        event.preventDefault();

        const search = searchbar.value.trim().toLowerCase();
        searchbar.value = "";
        searchHandler(search);
    }
});

//click sul bottone 
searchbutton.addEventListener("click", function(event){

    event.preventDefault();
    const search = searchbar.value.trim().toLowerCase();
    searchbar.value = "";
    searchHandler(search);

});

//focus sulla searchbar(allunga e mette la lente)
searchbar.addEventListener("focus", function(event){

    event.preventDefault();

    console.log("focus");

    const searchbardiv = document.getElementById("searchbardiv");
    const lente = document.getElementById("lente");

    searchbardiv.style.position = "relative";
    searchbar.style.minWidth = "calc(25vw + 30px)";
    searchbar.style.paddingLeft = "50px";

    const lenteBar = document.createElement("i");
    lenteBar.className = "fa-solid fa-magnifying-glass";
    lenteBar.id = "lentebar";
    lenteBar.style.position = "absolute";
    lenteBar.style.color = "white";
    lenteBar.style.top = "50%";
    lenteBar.style.left = "15px";
    lenteBar.style.transform = "translateY(-50%)"

    document.getElementById("searchbardiv").appendChild(lenteBar);

});

//blur sulla searchbar(accorcia e toglie la lente)
searchbar.addEventListener("blur", function(event){

    event.preventDefault();

    const lentebar = document.getElementById("lentebar")
    lentebar.remove();

    searchbar.style.minWidth = "";
    searchbar.style.paddingLeft = "";

});

function searchHandler(search) {
    
    videoSide.forEach(video => {

        const vid = video.querySelector(".titlestyle");
        const titoloSide = vid.textContent.trim().toLowerCase();

        if(titoloSide === search) changeVideo(video);

    });

}

//cambio di tutte le informazione rispetto al video selezionato o trovato
function changeVideo(video){

    const autoreSide = video.querySelector(".autoreSide").textContent.trim();
    const titoloSide = video.querySelector(".titlestyle").textContent.trim();
    const fotoSide = video.querySelector(".videopreview img");

    titoloVideo.innerHTML = titoloSide;
    nomeProfilo.innerHTML = autoreSide;

    //cambio immagine profilo
    if(fotoMap[autoreSide.trim().toLowerCase()]){
        fotoprofilo.src = fotoMap[autoreSide.trim().toLowerCase()];
    }else{
        fotoprofilo.src = "/css_course/project_2/resources/blank-profile-picture.png";
    }

    //vede se esiste già un'immagine nel player
    const exImg = playerContainer.querySelector(".videoimg");
    if(exImg) exImg.remove();

    //cambio video o immagine se non c'è il video
    if(videoMap[titoloSide.trim().toLowerCase()]){
        player.src = videoMap[titoloSide.trim().toLowerCase()];
    }else{
        player.src = "";

        const img = document.createElement("img");
        img.src = fotoSide.src;
        img.className = "videoimg";
        img.style.width = "100%";

        playerContainer.appendChild(img);
    }

    //cambio descrizione
    if(descMap[titoloSide.trim().toLowerCase()]){
        descrizione.innerText = descMap[titoloSide.trim().toLowerCase()];
        descrizione.style.color = "";
    }else{
        descrizione.innerText = "Nessuna descrizione";
        descrizione.style.color = "grey";
    }

}

//TODO : hover sui video della side


