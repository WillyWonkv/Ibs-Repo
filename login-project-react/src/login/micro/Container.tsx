import React from "react";
import "./Container.css";
import { InputUserPass } from "./InputUserPass";
import { Button } from "./Button";
import { Props } from "../../App";

export const Container = ({textTitle, textButton} : Props) => {

    return(

        <div className="container flex">
            <h2 className="title color">{textTitle}</h2>
            <InputUserPass></InputUserPass>
            <Button textButton={textButton} ></Button>
        </div>

    );

}