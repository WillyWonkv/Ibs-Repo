import React from "react";
import "./Home.css";
import { Link, useNavigate } from "react-router-dom";
import { Button } from "../components/Button";
import { handleUsersClick } from "../service/UsersService";

export const Dashboard = () => {

    const navigate = useNavigate();

    return(
        <div className="homebutton flex">
            <Button textButton={"Users"} onclick={() => handleUsersClick(navigate)}></Button>
        </div>
    );

}