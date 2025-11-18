import React, { useState } from "react";
import "./StyleForm.css";
import { Container } from "../components/CustomSignForm";
import { handleLogin } from "../service/UsersService";

export const SignInForm = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");


    return(
        <div className="flex body">
            <Container 
                textTitle='SIGN IN' 
                textButton='Login' 
                onclick={() => handleLogin(username,password)}
                onUsernameChange={setUsername}
                onPasswordChange={setPassword}></Container>
        </div>
    );


}