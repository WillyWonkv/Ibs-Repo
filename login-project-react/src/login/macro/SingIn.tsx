import React, { useState } from "react";
import "./StyleForm.css";
import { Container } from "../micro/Container";
import axios from "axios";
import { error } from "console";

export const SignIn = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = async () => {

        axios
            .post("http://localhost:8080/user/login", {username, password})
            .then(response => {

                const token = response.data.token;
                localStorage.setItem("token", token);
                alert("Logged in");
            })
            .catch(error => {
                console.error(error);
                alert("Login not successful")
            })

    }

    return(

        <div className="flex body">
            <Container 
                textTitle='SIGN IN' 
                textButton='Login' 
                onclick={handleLogin}
                onUsernameChange={setUsername}
                onPasswordChange={setPassword}></Container>
        </div>

    );


}