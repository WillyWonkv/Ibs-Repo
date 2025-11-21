import React, { useEffect, useState } from "react";
import "./StyleForm.css"
import { getUsers, User } from "../service/UsersService";

export const ShowUserForm = () => {

    const [user, setUser] = useState<User[]>([]);
    const [isPending, setIsPending] = useState(true);

    useEffect(() => {
        getUsers()
            .then(resp => {
                setUser(resp)
                setIsPending(false)
            })
            .catch(error => console.error(error.message))
    }, [])

    return(
        <>
            {isPending && <div>Loading...</div>}

            <div>
                {user.map(u => (
                    <div key={u.username}>
                        <p>Username: {u.username}</p>
                        <p>Password: {u.password}</p>
                    </div>
                ))}
            </div>
        </>
    );


}
