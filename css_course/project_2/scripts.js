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

//like dislike button on side
videoSide.forEach(video =>{

    const videopreview = video.querySelector(".videopreview");

    videopreview.addEventListener("mouseleave", function(event){

        event.preventDefault();
        const likecontainer = videopreview.querySelector(".likecontainer");
        const dislikecontainer = videopreview.querySelector(".dislikecontainer");
        if(likecontainer && dislikecontainer) {
            likecontainer.remove();
            dislikecontainer.remove();
        }

    });

    videopreview.addEventListener("mouseenter", function(event){

        event.preventDefault();

        if(!videopreview.querySelector(".likecontainer") && !videopreview.querySelector(".dislikecontainer")){

            videopreview.style.position = "relative";

            const likecontainer = document.createElement("a");
            likecontainer.className = "likecontainer flex";
            likecontainer.href = "#";
            likecontainer.style.backgroundColor = "rgba(0, 0, 0, 0.34)";
            likecontainer.style.borderRadius = "50px"
            likecontainer.style.width = "30px";
            likecontainer.style.height = "30px";
            likecontainer.style.position = "absolute";
            likecontainer.style.top = "10%";
            likecontainer.style.left = "80%";
            likecontainer.style.zIndex = "2";
            likecontainer.style.alignItems = "center";
            likecontainer.style.justifyContent = "center";


            const dislikecontainer = document.createElement("a");
            dislikecontainer.className = "dislikecontainer flex";
            dislikecontainer.href = "#";
            dislikecontainer.style.backgroundColor = "rgba(0, 0, 0, 0.34)";
            dislikecontainer.style.borderRadius = "50px"
            dislikecontainer.style.width = "30px";
            dislikecontainer.style.height = "30px";
            dislikecontainer.style.position = "absolute";
            dislikecontainer.style.top = "calc(10% + 40px)";
            dislikecontainer.style.left = "80%";
            dislikecontainer.style.zIndex = "2";
            dislikecontainer.style.alignItems = "center";
            dislikecontainer.style.justifyContent = "center";

            const likebutton = document.createElement("i");
            likebutton.style.position = "absolute";
            likebutton.className = "fa-solid fa-thumbs-up";
            likebutton.style.color = "white";
            likebutton.style.transform = "tranlate(-50%, -50%)";
            likebutton.style.fontSize = "15px";

            const dislikebutton = document.createElement("i");
            dislikebutton.style.position = "absolute";
            dislikebutton.className = "fa-solid fa-thumbs-down";
            dislikebutton.style.color = "white";
            dislikebutton.style.transform = "tranlate(-50%, -50%)";
            dislikebutton.style.fontSize = "15px";

            videopreview.appendChild(likecontainer);
            videopreview.appendChild(dislikecontainer);

            likecontainer.appendChild(likebutton);
            dislikecontainer.appendChild(dislikebutton);

            likecontainer.addEventListener("click", function(event){

                event.preventDefault();
                event.stopPropagation();

                console.log("click like");

            })

            dislikebutton.addEventListener("click", function(event){

                event.preventDefault();
                event.stopPropagation();

                console.log("click dislike");

            });

        }

    });

});

//aggiunta like video
const buttonlike = document.getElementById("buttonlike");
const numerolike = document.getElementById("numerolike");
let numero = parseInt(numerolike.textContent);

buttonlike.addEventListener("click", function(event){

    event.stopPropagation();
    event.preventDefault();
    numero++;
    numerolike.textContent = numero;

});

//diminuzione like video
const buttondislike = document.getElementById("buttondislike");
buttondislike.addEventListener("click", function(event){

    event.stopPropagation();
    event.preventDefault();
    numero--;
    numerolike.textContent = numero;

})

//iscritto
const iscrivitibutton = document.getElementById("iscrivitibutton");
iscrivitibutton.addEventListener("click", function(event){

    event.preventDefault();

    const numeroiscritti = document.getElementById("numeroiscritti");
    const iscrivititext = document.getElementById("iscrivititext");
    let numeroiscrittiint = parseInt(numeroiscritti.textContent);
    
    if(iscrivititext.textContent.trim().toLowerCase() === "iscriviti"){
        
        numeroiscrittiint++;
        numeroiscritti.textContent = numeroiscrittiint;
        iscrivititext.innerText = "Iscritto";
        iscrivititext.style.color = "white";
        iscrivitibutton.style.backgroundColor = "rgba(73, 73, 73, 1)"
        iscrivititext.style.paddingRight = "0px";

        if(!document.getElementById("bell")){
            //<i class="fa-solid fa-bell"></i>
            const bell = document.createElement("i");
            bell.className = "fa-solid fa-bell";
            bell.style.color = "white";
            bell.id = "bell";
            bell.style.padding = "5px";
            iscrivititext.after(bell);

            const anim = bell.animate([
                {transform: 'scale(1.5)'}

            ],{
                duration: 500,
                iterations: 1,
            });

            anim.play();
            
        }


    }else{

        numeroiscrittiint--;
        numeroiscritti.textContent = numeroiscrittiint;
        iscrivititext.innerText = "Iscriviti";
        iscrivititext.style.color = "";
        iscrivitibutton.style.backgroundColor = "";

        document.getElementById("bell").remove();
        iscrivititext.style.paddingRight = "";

    }

});

//


//vede se trova il titolo del video ricercato e lo sostituisce
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




