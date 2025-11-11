import React from "react";
import "./Info-profilo.css"

interface Props{

    nomeProfilo : string;
    numeroIscritti : number;

}

const InfoProfilo = ({nomeProfilo, numeroIscritti} : Props) => {

    return(

        <span className="fontYT infoprofilo">
            <a href="#" className="fontYT nomeprofilo" id="nomeProfilo">{nomeProfilo}</a>
            <br></br>
            <span className="authorstyle fontYT" id="numeroiscritti">{numeroIscritti} iscritti</span>
        </span>

    );

}

export default InfoProfilo;