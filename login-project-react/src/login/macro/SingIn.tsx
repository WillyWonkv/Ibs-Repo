import React, { use, useState } from "react";
import "./StyleForm.css";
import { Container } from "../micro/Container";
import api from "../axios";

export const SignIn = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = async () => {

        localStorage.removeItem("token");

        api
            .post("/user/login", {username, password})
            .then(response => {
                const token = response.data.token;
                localStorage.setItem("token", token);
                console.log("Logged in", token);
            })
            .catch(error => {
                console.error(error);
                alert("Login not successful");
            });

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