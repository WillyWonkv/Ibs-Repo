import React from "react";
import "./Button.css"

interface Props{

    textButton : string;
    onclick : () => void

}

export const Button = ({textButton, onclick} : Props) => {

    return(

        <button type="submit" className="button" onClick={onclick}>{textButton}</button>

    );

}