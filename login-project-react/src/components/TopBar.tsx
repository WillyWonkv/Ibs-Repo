import { DownOutlined, HomeFilled, UserOutlined, YoutubeFilled } from "@ant-design/icons";
import { Button, Dropdown, Flex, Menu, MenuProps, Space } from "antd";
import  "../components/TopBar.css"
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";



const buttonStyle: React.CSSProperties = {

    color:"white",
    fontFamily:"sans-serif",
    fontWeight:600  

}

const topBarStyle: React.CSSProperties = {
    height: "100%", 
    width:"100%"
}

const menuTextStyle: React.CSSProperties = {
    fontFamily:"sans-serif",
    fontWeight:1000
}

export const TopBar = () => {

    const navigate = useNavigate();
    const [username,setUsername] = useState<string|null>(null);

    type JwtPayload = {
        role: string[]
        permissions: string[]
        sub: string
        iat: number
        exp: number
    }

    useEffect(() => {
            const token = localStorage.getItem("token")
            if(token){
                const payload : JwtPayload = jwtDecode(token)
                setUsername(payload.sub) 
            }
    },[])

    const items: MenuProps["items"] = [
        {
            key:"profile",
            label:(<Button className="userbutton" type="text" onClick={() => {alert("profile")}}>Profile</Button>)
        },
        {
            key:"profile",
            label:(<Button className="userbutton" type="text" onClick={() => {alert("settings")}}>Setting</Button>)
        },
        {
            type:"divider"
        },
        {
            key:"logout",
            label:(<Button 
                className="userbutton" 
                type="text" 
                onClick={() => {
                    localStorage.removeItem("token");
                    setUsername(null);
                    navigate("/")
                }}
            >Logout</Button>)
        }

    ]


    return(

        <Flex style={topBarStyle} gap={"middle"} align="center">

            <Flex>
                <Button
                    type="link"
                    icon={
                        <YoutubeFilled 
                            style={{
                                color:"red",
                                fontSize:"28px"
                        }}/>
                    }
                />
            </Flex>

            <Flex justify="flex-start">
                
                    <Button
                        type="link"
                        style={buttonStyle}
                        className="menubutton"
                        icon={<HomeFilled
                            style={{
                                fontSize:"16px", 
                            }}
                        />}
                    >HOME</Button>

                    <Button
                        type="link"
                        style={buttonStyle}
                        className="menubutton"
                    >FILM</Button>

                    <Button
                        type="link"
                        style={buttonStyle}
                        className="menubutton"
                    >TV SERIES</Button>

            </Flex>

            <Flex style={{marginLeft: "auto"}}>

                {username ? (
                    <Dropdown 
                        menu={{items}} 
                        placement="bottom" 
                        trigger={["click"]}
                        >
                            <Button
                                shape="round"
                                type="text"
                                className="userbutton"
                                icon={<UserOutlined/>}
                                style={{color:"white"}}
                            >{username}</Button>
                    </Dropdown>
                    ):(
                        <Button 
                            shape="round"
                            type="text"
                            iconPlacement="start"
                            className="userbutton"
                            style={{color:"white"}}
                            icon={<UserOutlined/>}
                            onClick={() => {navigate("/users/login")}}
                            ><span>Sign in</span> 
                        </Button>
                    )
                }

            </Flex>

        </Flex>

    );

}