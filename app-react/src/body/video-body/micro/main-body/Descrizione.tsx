import React from "react";
import "./Descrizione.css"

interface Props{

    testoDescri : string;

}

const Descrizione = ({testoDescri} : Props) => {

    return(

        <div className="descrizionecontainer">
            <span className="descrizionetext fontYT" id="descrizione">{testoDescri}</span>
        </div>

    );

}

export default Descrizione;