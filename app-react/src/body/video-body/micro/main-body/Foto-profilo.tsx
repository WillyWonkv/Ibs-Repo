import React from "react";
import "./Foto-profilo.css"

interface Props{

    src : string;

}

const Foto = ({src} : Props) => {

    return(

        <a href="#">
            <img 
                className="fotoprofilo" 
                src={src} 
                id="fotoprofilo"
                alt="Foto profilo"
            />
        </a>

    );

}

export default Foto;