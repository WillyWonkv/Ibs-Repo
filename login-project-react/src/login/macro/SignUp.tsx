import React, { useState } from "react";
import "./StyleForm.css";
import { Container } from "../micro/Container";
import axios from "axios";
import api from "../axios";

export const SignUp = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleRegister = async () => {

        localStorage.removeItem("token")

        api
            .post("/user/register", {username, password})
            .then(resp => {
                alert("Registered");
            })
            .catch(err => {
                console.error(err);
                alert("User already registered")
            })

    }

    return(    
        <div className="flex body">
            <Container 
                textTitle='SIGN UP' 
                textButton='Register'
                onclick={handleRegister}
                onUsernameChange={setUsername}
                onPasswordChange={setPassword}></Container>
        </div>

    );


}