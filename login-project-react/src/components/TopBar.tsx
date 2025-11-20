import React from "react";
import "./TopBar.css"
import { Button } from "./Button";

export const TopBar = () => {

    return(

        <div className="topbar flex">
            <div className="logout"></div>
            <Button textButton={"Log Out"} onclick={() => {}}></Button>
        </div>

    );

}