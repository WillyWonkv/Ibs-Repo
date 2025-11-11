import React from "react";
import "./Info-video-side.css"

interface Props{

    titolo: string;
    autore: string;
    visual: string;    

}

const InfoVideoSide = ({titolo, autore, visual} : Props) => {

    return(

        <div className="infovideo">
            <span className="titlestyle fontYT">{titolo}</span>
            
            <span className="authorstyle fontYT autoreSide">{autore}</span>
            <br></br>
            <span className="authorstyle fontYT">{visual} di visualizzazioni</span> 
        </div>

    );

}

export default InfoVideoSide;