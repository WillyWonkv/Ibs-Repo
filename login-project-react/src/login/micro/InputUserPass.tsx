import React from "react";
import "./InputUserPass.css"

export const InputUserPass = () => {

    return(

        <div className="userpasscontainer flex">

            <div className="inputcontainer">
                <i className="fa-solid fa-user logo color"></i>
                <input className="inputbox" type="text" name="username" placeholder="username"/>
            </div>
            
            <div className="inputcontainer">
                <i className="fa-solid fa-key logo color"></i>
                <input className="inputbox" type="text" name="password" placeholder="password"/>
            </div>

            
        </div>

    );


}