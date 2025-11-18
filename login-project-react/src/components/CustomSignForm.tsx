import React, { useRef } from "react";
import "./CustomSignForm.css";
import { Button } from "./Button";

interface Props{

    textTitle : string,
    textButton : string,
    onclick : () => void
    onUsernameChange: (username: string) => void;
    onPasswordChange: (password: string) => void;

}

export const Container = ({textTitle, textButton, onclick, onUsernameChange, onPasswordChange} : Props) => {

    const usernameRef = useRef<HTMLInputElement>(null);
    const passwordRef = useRef<HTMLInputElement>(null);

    const handleEnter = () => {
        onclick?.();
        if(usernameRef.current) usernameRef.current.value = "";
        if(passwordRef.current) passwordRef.current.value = "";
    }

    return(

        <div className="container flex">
            <h2 className="title color">{textTitle}</h2>
            
            <div className="userpasscontainer flex">

                <div className="inputcontainer">
                    <i className="fa-solid fa-user logo color"></i>
                    <input ref={usernameRef} className="inputbox" type="text" name="username" placeholder="username" 
                        onChange={(e) => onUsernameChange(e.target.value)}
                        onKeyDown={(e) => {if(e.key === "Enter") handleEnter()}}/>
                </div>
                
                <div className="inputcontainer">
                    <i className="fa-solid fa-key logo color"></i>
                    <input ref={passwordRef} className="inputbox" type="password" name="password" placeholder="password"
                        onChange={(e) => onPasswordChange(e.target.value)}
                        onKeyDown={(e) => {if(e.key === "Enter") handleEnter()}}/>
                </div>
            </div>

            <Button textButton={textButton} onclick={onclick}></Button>
        </div>

    );

}