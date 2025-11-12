import React from "react";

interface VideoSide{

    id : number;
    srcVideo : string;
    srcFotoVideo: string;
    titoloVideo : string;
    autoreVideo : string;
    descVideo : string;
    numLikeVideo : number;
    numVisualVideo : string;

}

interface AutoreSide{

    id : number;
    nome : string;
    fotoSrc : string;
    numIscritti : number;

}

export const AutoriList : AutoreSide[] = [
    {
        id: 1,
        nome: "Bad Bunny",
        fotoSrc: "resources/badbunny.jpg",
        numIscritti: 30010
    },
    {
        id: 2,
        nome: "Tainy",
        fotoSrc: "resources/tainy.jpg",
        numIscritti: 10020
    },
    {
        id: 3,
        nome: "Luis Fonsi",
        fotoSrc: "resources/luisfonsi.jpg",
        numIscritti: 999299
    }
]

export const VideoList : VideoSide[] = [
    {
        srcFotoVideo: "resources/hq720.webp",
        titoloVideo: "DtMF",
        autoreVideo: "Bad Bunny",
        descVideo: "",
        id: 1,
        numLikeVideo: 9992,
        numVisualVideo: "43 mln",
        srcVideo: "https://www.youtube.com/embed/v9T_MGfzq7I?si=zJevos4pULPLZU4T"
    },
    {
        srcFotoVideo: "resources/hqdefault.webp",
        titoloVideo: "MOJABI GHOST",
        autoreVideo: "Tainy",
        descVideo: "",
        id: 2,
        numLikeVideo: 10002,
        numVisualVideo: "5 mln",
        srcVideo: "https://www.youtube.com/embed/O1wUdB7MQbI?si=AzOBRK2slEGFvLJw"
    },
    {
        srcFotoVideo: "resources/hq720.webp",
        titoloVideo: "KLOuFRENS",
        autoreVideo: "Bad Bunny",
        descVideo: "",
        id: 3,
        numLikeVideo: 87737,
        numVisualVideo: "32 mln",
        srcVideo: "https://www.youtube.com/embed/LgbYE3cIdhs?si=2Nnz39JPeA_BQqLn"
    },
    {
        srcFotoVideo: "resources/hq720.webp",
        titoloVideo: "VeLDÁ",
        autoreVideo: "Bad Bunny",
        descVideo: "",
        id: 4,
        numLikeVideo: 99183,
        numVisualVideo: "20 mln",
        srcVideo: "https://www.youtube.com/embed/yNEpyU3PnDI?si=zqpmH_Fc1uWllplB"
    },
    {
        srcFotoVideo: "resources/titi.webp",
        titoloVideo: "Tití Me Preguntó",
        autoreVideo: "Bad Bunny",
        descVideo: "",
        id: 5,
        numLikeVideo: 5535,
        numVisualVideo: "52 mln",
        srcVideo: "https://www.youtube.com/embed/Cr8K88UcO0s?si=6vDlg_DEo8xlTWES"
    },
    {
        srcFotoVideo: "resources/despa.jpg",
        titoloVideo: "Despacito",
        autoreVideo: "Luis Fonsi",
        descVideo: "",
        id: 6,
        numLikeVideo: 10030,
        numVisualVideo: "1 mld",
        srcVideo: "https://www.youtube.com/embed/kJQP7kiw5Fk?si=M6io2iF7pvTp-HGH"
    },
    {
        srcFotoVideo: "resources/hqdefault.webp",
        titoloVideo: "Hawái",
        autoreVideo: "Maluma",
        descVideo: "",
        id: 7,
        numLikeVideo: 20030,
        numVisualVideo: "500 mln",
        srcVideo: "https://www.youtube.com/embed/pK060iUFWXg?si=osvXRbEu8E9Zip9a"
    },
    {
        srcFotoVideo: "resources/hq720.webp",
        titoloVideo: "Blinding Lights",
        autoreVideo: "The Weeknd",
        descVideo: "",
        id: 8,
        numLikeVideo: 5555,
        numVisualVideo: "20 mln",
        srcVideo: ""
    },
    {
        srcFotoVideo: "resources/hqdefault.webp",
        titoloVideo: "Peaches",
        autoreVideo: "Justin Bieber",
        descVideo: "",
        id: 9,
        numLikeVideo: 2,
        numVisualVideo: "100 mln",
        srcVideo: ""
    },
    {
        srcFotoVideo: "resources/hq720.webp",
        titoloVideo: "Levitating",
        autoreVideo: "Dua Lipa",
        descVideo: "",
        id: 10,
        numLikeVideo: 102,
        numVisualVideo: "30 mln",
        srcVideo: ""
    },
    {
        srcFotoVideo: "resources/hqdefault.webp",
        titoloVideo: "Montero (Call Me By Your Name)",
        autoreVideo: "Lil Nas X",
        descVideo: "",
        id: 11,
        numLikeVideo: 99329,
        numVisualVideo: "201 mln",
        srcVideo: ""
    },
    {
        srcFotoVideo: "resources/hq720.webp",
        titoloVideo: "Stay",
        autoreVideo: "The Kid LAROI",
        descVideo: "",
        id: 12,
        numLikeVideo: 99999,
        numVisualVideo: "200 mln",
        srcVideo: ""
    },
    {
        srcFotoVideo: "resources/hqdefault.webp",
        titoloVideo: "Industry Baby",
        autoreVideo: "Lil Nas X",
        descVideo: "",
        id: 13,
        numLikeVideo: 103020,
        numVisualVideo: "13 mln",
        srcVideo: ""
    },
] 