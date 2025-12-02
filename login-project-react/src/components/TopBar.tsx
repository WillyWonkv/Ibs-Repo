import { ControlOutlined, EllipsisOutlined, HomeFilled, LogoutOutlined, UserOutlined, YoutubeFilled } from "@ant-design/icons";
import { Button, Dropdown, Flex, MenuProps } from "antd";
import  "../components/TopBar.css"
import React, { useContext, useEffect, useState } from "react";
import { replace, useNavigate } from "react-router-dom";
import Search from "antd/es/input/Search";
import { AppContext } from "../App";


export const TopBar = () => {
    
    const navigate = useNavigate();
    const [loading,setLoading] = useState<boolean>(false);
    const {setStore,store} = useContext(AppContext);

    const buttonStyle: React.CSSProperties = {
        color:"white",
        fontWeight:700 
    }
    
    const logout =()=>{
        localStorage.removeItem("token");
        setStore({
            token:"",
            username:"",
            roles:[],
            permissions:[]
        });
        navigate("/", {replace:true});
    }


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
                onClick={logout}
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

            <Flex>
                {store.username ? (
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
                            >{store.username.toUpperCase()}</Button>
                    </Dropdown>
                    ) : (
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