import React from "react";
import "./Container.css";
import { InputUserPass } from "./InputUserPass";
import { Button } from "./Button";

interface Props{

    textTitle : string,
    textButton : string,
    onclick : () => void
    onUsernameChange: (username: string) => void;
    onPasswordChange: (password: string) => void;

}

export const Container = ({textTitle, textButton, onclick, onUsernameChange, onPasswordChange} : Props) => {

    return(

        <div className="container flex">
            <h2 className="title color">{textTitle}</h2>
            <InputUserPass onUsernameChange={onUsernameChange} onPasswordChange={onPasswordChange}></InputUserPass>
            <Button textButton={textButton} onclick={onclick}></Button>
        </div>

    );

}