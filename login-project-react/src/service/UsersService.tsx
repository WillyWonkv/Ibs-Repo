import React from "react";
import api from "./AxiosSettings";


export const handleLogin = async (username : string, password : string) => {

    localStorage.removeItem("token");

    if(!username.trim() || !password.trim()){
        alert("Fill all the fields");
        return;
    }

    api
        .post("/user/login", {username, password})
        .then(response => {
            const token = response.data.token;
            localStorage.setItem("token", token);
            console.log("Logged in", token);
            alert("Logged in");
        })
        .catch(error => {
            console.error(error.response.data.message);
            alert("Login not successful");
        });

}

export const handleRegister = async (username : string, password : string) => {

    localStorage.removeItem("token")

    if(!username.trim() || !password.trim()){
        alert("Fill all the fields");
        return;
    }

    api
        .post("/user/register", {username, password})
        .then(resp => {
            alert("Registered");
        })
        .catch(err => {
            console.error(err.response.data.message);
            alert("User already registered")
        })

}
