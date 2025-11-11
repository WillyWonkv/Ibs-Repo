import React from "react";
import "./Box-video-main.css"
import Player from "../../micro/main-body/Player";
import Foto from "../../micro/main-body/Foto-profilo";
import InfoProfilo from "../../micro/main-body/Info-profilo";
import IscrivitiButton from "../../micro/main-body/Iscriviti-button";
import LikeDislikeButton from "../../micro/main-body/LikeDislike-button";
import Descrizione from "../../micro/main-body/Descrizione";

interface Props{

    srcPlayer : string;
    titoloVideo : string;
    srcFotoProfilo : string;
    nomeProfilo : string;
    numIscritti : number;
    numLike : number;
    testoDescri : string;

}

const BoxVideoMain = ({

    srcPlayer,
    titoloVideo,
    srcFotoProfilo,
    nomeProfilo,
    numIscritti,
    numLike,
    testoDescri

} : Props) => {

    return(

        <div className="main">

            <Player src={srcPlayer}></Player>
            <h1 className="titolovideo fontYT">{titoloVideo}</h1>
            <div className="divprofilo flex">
                <Foto src={srcFotoProfilo}></Foto>
                <InfoProfilo nomeProfilo={nomeProfilo} numeroIscritti={numIscritti}></InfoProfilo>
                <IscrivitiButton></IscrivitiButton>
                <LikeDislikeButton numeroLike={numLike}></LikeDislikeButton>
            </div>
            <Descrizione testoDescri={testoDescri}></Descrizione>

        </div>

    );

}

export default BoxVideoMain;