import React, { useState } from "react";
import api from "./AxiosSettings";
import { loginResponse } from "../types";

export const handleLoginService = async (
    username: string,
    password: string
): Promise<loginResponse> => {
    const resp = await api.post<loginResponse>("/user/login", { username, password })
    return resp.data;
}

export const handleRegisterService = async (
    username: string,
    password: string
) => {
    await api.post("/user/register", { username, password })
}

