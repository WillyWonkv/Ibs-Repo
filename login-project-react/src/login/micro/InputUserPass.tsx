import React from "react";
import "./InputUserPass.css"

interface UserPassProps{

    onUsernameChange: (username: string) => void;
    onPasswordChange: (password: string) => void;

}

export const InputUserPass = ({onUsernameChange, onPasswordChange} : UserPassProps) => {

    return(

        <div className="userpasscontainer flex">

            <div className="inputcontainer">
                <i className="fa-solid fa-user logo color"></i>
                <input className="inputbox" type="text" name="username" placeholder="username" 
                    onChange={(e) => onUsernameChange(e.target.value)}/>
            </div>
            
            <div className="inputcontainer">
                <i className="fa-solid fa-key logo color"></i>
                <input className="inputbox" type="text" name="password" placeholder="password"
                    onChange={(e) => onPasswordChange(e.target.value)}/>
            </div>

            
        </div>

    );


}