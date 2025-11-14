import React, { useState } from "react";
import "./StyleForm.css";
import { Container } from "../micro/Container";
import axios from "axios";

export const SignUp = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleRegister = async () => {

            axios
        .post("http://localhost:8080/user/register", {username, password})
        .then(response => {
            alert("Registered");
        })
        .catch(error => {
            console.error(error);
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