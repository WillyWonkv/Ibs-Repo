import React, { useState } from "react";
import "./StyleForm.css";
import { Container } from "../components/CustomSignForm";
import { handleRegister } from "../service/UsersService";

export const SignUpForm = () => {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    return(    
        <div className="flex body">
            <Container 
                textTitle='SIGN UP' 
                textButton='Register'
                onclick={() => handleRegister(username, password)}
                onUsernameChange={setUsername}
                onPasswordChange={setPassword}></Container>
        </div>
    );


}