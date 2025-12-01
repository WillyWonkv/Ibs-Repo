import React, { useState } from "react";
import api from "./AxiosSettings";
import { jwtDecode } from "jwt-decode";
import { loginResponse } from "../types";

export interface TokenPayload {
    roles: string[];
    permissions: string[];
    sub: string;
    iat: number;
    exp: number;
}

export interface User {
    username: string;
    password: string;
}

export const handleLoginService = async (
    username: string,
    password: string
): Promise<loginResponse> => {
    const resp = await api.post<loginResponse>("/user/login", { username, password })
    return resp.data;
}

export const handleRegister = async (
    username: string,
    password: string,
    navigate: (path: string) => void
): Promise<boolean> => {

    localStorage.removeItem("token")

    try {
        await api.post("/user/register", { username, password })
        console.log("User registered");
        alert("Registered");
        navigate("/users/login");
        return true;
    } catch (err) {
        console.error(err);
        alert("Error");
        return false;
    }

}

export const handleUsersClick = async (navigate: (path: string) => void) => {

    const token = localStorage.getItem("token")
    if (!token) {
        alert("You need to login!!");
        navigate("/login");
        return;
    }

    const payload: TokenPayload = jwtDecode(token);
    const allowedRoles = ["ADMIN", "MANAGER"];
    if (payload.roles.some(role => allowedRoles.includes(role))) {
        navigate("/users/getall");
    } else {
        alert("Access denied");
    }

}
