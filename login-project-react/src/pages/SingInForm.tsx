import React, { useEffect, useState } from "react";
import "./StyleForm.css";
import { Container } from "../components/CustomSignForm";
import { handleLogin } from "../service/UsersService";
import { useNavigate } from "react-router-dom";

export const SignInForm = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();

    if(localStorage.getItem("token")){
        localStorage.removeItem("token");
    }

    return(
        <div className="flex body">
            <Container
                textTitle='SIGN IN' 
                textButton='Login' 
                onclick={() => handleLogin(username,password,navigate)}
                onUsernameChange={setUsername}
                onPasswordChange={setPassword}></Container>
        </div>
    );


}