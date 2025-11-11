import React, { useState } from "react";
import "./Box-video-side.css"
import VideoPreview from "../../micro/side-bar/Video-preview";
import InfoVideoSide from "../../micro/side-bar/Info-video-side";

interface Props{

    src : string;
    titolo : string;
    autore : string;
    numVisual : string;
    clickHandler: (titolo: string) => void

}

const BoxVideoSide = ({src, titolo, autore, numVisual, clickHandler} : Props) => {

    const [Click, setClick] = useState(false);

    return(

        <a href="#" className="boxsidebar flex"
            onClick={() => clickHandler(titolo)}>
            <VideoPreview src={src}></VideoPreview>
            <InfoVideoSide
                titolo={titolo} autore={autore} visual={numVisual}
            ></InfoVideoSide>
        </a>

    );

}

export default BoxVideoSide;