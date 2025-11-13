import React from "react";
import { Props } from "../../App";
import "./Button.css"

export const Button = ({textButton} : Props) => {

    return(

        <a href="#" className="button color">{textButton}</a>

    );

}