import React, { useState } from "react";
import api from "./AxiosSettings";
import { jwtDecode } from "jwt-decode";

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
):Promise<boolean> => {

    try{
        const resp = await api.post("/user/login", {username, password})
        const token = resp.data.token;
        localStorage.setItem("token", token);
        console.log("Logged in", token);
        navigate("/");
        return true;
    }catch(err){
        console.log(err);
        alert("Error")
        return false
    }

}

export const handleRegister = async (
    username : string,
    password : string,
    navigate : (path : string) => void
):Promise<boolean> => {

    localStorage.removeItem("token")

    try{
        await api.post("/user/register", {username, password})
        console.log("User registered");
        alert("Registered");
        navigate("/users/login");
        return true;
    }catch(err){
        console.error(err);
        alert("Error");
        return false;
    }

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