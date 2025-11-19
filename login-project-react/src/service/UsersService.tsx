import React from "react";
import api from "./AxiosSettings";
import { jwtDecode } from "jwt-decode";
import { error } from "console";
import { transcode } from "buffer";

interface TokenPayload {
  role: string[];
  permissions: string[];
  sub: string;
  iat: number;
  exp: number;
}

export interface User{
    username: string;
    password : string;
}   

export const handleLogin = async (
    username : string,
    password : string,
    navigate : (path : string) => void
) => {

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
            navigate("/dashboard");
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
            console.log("User registered")
            alert("Registered");
        })
        .catch(err => {
            console.error(err.response.data.message);
            alert("User already registered")
        })

}

export const handleUsersClick = async (navigate : (path : string) => void) => {

    const token = localStorage.getItem("token")
    if(!token){
        alert("You need to login!!");
        navigate("/login");
        return;
    }

    const payload : TokenPayload = jwtDecode(token);
    const allowedRoles = ["ADMIN", "MANAGER"];
    if(payload.role.some(role => allowedRoles.includes(role))){
        navigate("/users/getall");
    }else{
        alert("Access denied");
    }

    // api
    //     .get("/user/getall")
    //     .then(resp => {
    //         navigate("/users/getall");
    //     })
    //     .catch(err => {
    //         alert("Access denied")
    //         console.error(err);
    //     })

}

export const getUsers = async () => {

    return api
        .get<User[]>("/user/getall")
        .then(resp => resp.data)
        .catch(err => {
            alert(err.response.data.message);
            console.log(err);
            throw err;
        })

}