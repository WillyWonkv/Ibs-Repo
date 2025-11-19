import React, { useEffect, useState } from "react";
import "./StyleForm.css"
import { getUsers, User } from "../service/UsersService";

export const ShowUserForm = () => {

    const [user, setUser] = useState<User[]>([]);

    getUsers()
        .then(resp => setUser(resp))
        .catch(error => console.error(error))

    return(
        <div>
            {user.map(u => (
                <div key={u.username}>
                    <p>Username: {u.username}</p>
                    <p>Password: {u.password}</p>
                </div>
            ))}
        </div>
    );


}
