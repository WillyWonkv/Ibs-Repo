import React, { useState } from "react";
import "./Box-video-side.css"
import VideoPreview from "../../micro/side-bar/Video-preview";
import InfoVideoSide from "../../micro/side-bar/Info-video-side";
import { InfoVideo } from "../../../Body";
import { AutoriList, VideoList } from "../../../Data";

interface Props{

    idVideo : number;
    src : string;
    titolo : string;
    autore : string;
    idAutore : number;
    numVisual : string;
    clickHandler: (InfoVideoSide : InfoVideo) => void

}



const BoxVideoSide = ({idVideo, idAutore, src, titolo, autore, numVisual, clickHandler} : Props) => {

    function onClickHandler(e :React.MouseEvent<HTMLAnchorElement>){

        const autoreObj = AutoriList.find(a => a.id === idAutore);
        const videoObj = VideoList.find(v => v.id === idVideo);
        clickHandler({
            id : videoObj ? videoObj.id : 0,
            srcVideo: videoObj ? videoObj.srcVideo : "",
            titoloVideo: titolo,
            autoreVideo: autore,
            srcFotoProfiloVideo: autoreObj ? autoreObj.fotoSrc : "",
            numIscrittiVideo: autoreObj ? autoreObj.numIscritti : 0,
            numLikeVideo: videoObj ? videoObj.numLikeVideo : 0,
            testoDesciVideo: videoObj ? videoObj.descVideo : ""
        });

    }

    return(

        <a href="#" className="boxsidebar flex"
            onClick={(e) => onClickHandler(e)}>
            <VideoPreview src={src}></VideoPreview>
            <InfoVideoSide
                titolo={titolo} autore={autore} visual={numVisual}></InfoVideoSide>
        </a>

    );

}

export default BoxVideoSide;