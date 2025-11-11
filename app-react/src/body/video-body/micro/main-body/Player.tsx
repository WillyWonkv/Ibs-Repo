import React from "react";
import "./Player.css";

interface Props{

    src : string;

}

const Player = ({src} : Props) => {

    return(

        <div className="playercontainer" id="playercontainer">
            <iframe className="player" 
            src={src}
            title="YouTube video player" 
            allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" 
            allowFullScreen
            id="player"
            ></iframe>
        </div> 

    );

}

export default Player;