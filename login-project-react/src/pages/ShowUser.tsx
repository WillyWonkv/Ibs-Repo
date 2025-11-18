import React, { useState } from "react";
import "./StyleForm.css"
import api from "../service/AxiosSettings";

interface User{
        id : number;
        username: string;
        password : string;
}

export const ShowUserForm = () => {

    const [user, setUser] = useState<User[]>([]);

    const getUsers = () => {

        api
            .get<User[]>("/user/all")
            .then(resp =>{
                setUser(resp.data);
            })
            .catch(err => {
                console.log(err)
            })

    }

    return(
        user.map(u => {



        })
    );


}
