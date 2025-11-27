import { ControlOutlined, DownOutlined, EllipsisOutlined, HomeFilled, LogoutOutlined, UserOutlined, YoutubeFilled } from "@ant-design/icons";
import { Button, Dropdown, Flex, Menu, MenuProps, Space, theme } from "antd";
import  "../components/TopBar.css"
import React, { useEffect, useState } from "react";
import { replace, useNavigate } from "react-router-dom";
import { jwtDecode } from "jwt-decode";
import Search from "antd/es/input/Search";


export const TopBar = () => {
    
    const navigate = useNavigate();
    const [username,setUsername] = useState<string|null>(null);
    const [loading,setLoading] = useState<boolean>(false) 

    const buttonStyle: React.CSSProperties = {
        color:"white",
        fontWeight:600  
    }
    
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
                try{
                    const payload : JwtPayload = jwtDecode(token)
                    const isExpired = payload.exp * 1000 < Date.now()
                    if(isExpired){
                        console.log("Token expired");
                        localStorage.removeItem("token");
                        setUsername(null);
                        navigate("/", {replace:true});
                        return
                    }
                    setUsername(payload.sub)
                }catch(err){
                    console.log("Token not valid", err);
                    localStorage.removeItem("token");
                    setUsername(null);
                }
            }
    },[])

    const items: MenuProps["items"] = [
        {
            key:"profile",
            label:(<Button 
                    className="dropbutton" 
                    type="text" 
                    icon={<UserOutlined/>}
                    onClick={() => {alert("profile")}}>Profile</Button>
            ) 
        },
        {
            key:"settings",
            label:(<Button 
                    className="dropbutton" 
                    type="text" 
                    icon={<ControlOutlined />}
                    onClick={() => {alert("settings")}}>Settings</Button>
            )
        },
        {
            type:"divider"
        },
        {
            key:"logout",
            label:(<Button 
                className="dropbutton" 
                type="text" 
                icon={<LogoutOutlined />}
                onClick={() => {
                    localStorage.removeItem("token");
                    setUsername(null);
                    navigate("/")
                }}
            >Logout</Button>)
        }

    ]


    return(

        <Flex className="topbar" gap={"middle"} align="center">

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
            
            <Flex  flex={1} justify="flex-start">
                <Search 
                    className="searchbar"
                    placeholder="Search..."
                    enterButton
                    loading={loading}
                    size="middle"
                    onSearch={() => (console.log("click search"))}
                    style={{width:"100%", maxWidth:"400px"}}
                />                      
            </Flex>

            <Flex >
                {username ? (
                    <Dropdown 
                        menu={{items}} 
                        placement="bottom" 
                        trigger={["click"]}
                        popupRender={(menu) => (
                            <div className="contentStyle">
                                <div className="menuStyle">
                                    {menu}
                                </div>
                            </div>
                        )}
                        >
                        <Button
                            shape="round"
                            type="text"
                            className="userbutton"
                            icon={<EllipsisOutlined />}
                            iconPlacement="end"
                            style={{color:"white"}}
                            >{username.toUpperCase()}</Button>
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