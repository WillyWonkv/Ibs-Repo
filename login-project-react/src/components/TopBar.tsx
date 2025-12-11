import { ControlOutlined, EllipsisOutlined, HomeFilled, LogoutOutlined, UserOutlined, YoutubeFilled } from "@ant-design/icons";
import { AutoComplete, Button, Dropdown, Flex, Input, MenuProps, Spin } from "antd";
import  "../components/TopBar.css"
import React, { useContext, useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { AppContext, openNotification } from "../App";
import { Film, handleGetFilmByTitleService } from "../service/FilmsService";


export const TopBar = () => {
    
    const navigate = useNavigate();
    const [loading,setLoading] = useState<boolean>(false);
    const {setStore,store} = useContext(AppContext);
    const [options, setOptions] = useState<{value:string}[]>([]);
    const [filmSearch, setFilmSearch] = useState<Film[]>([]);

    const buttonStyle: React.CSSProperties = {
        color:"white",
        fontWeight:700 
    }
    
    const logout =()=>{
        localStorage.removeItem("token");
        localStorage.removeItem("store");
        setStore({
            token:"",
            username:"",
            roles:[],
            permissions:[]
        });
        navigate("/", {replace:true});
    }

    const fetchFilms = async (title:string) => {
        if(!title){
            setOptions([]);
            return;
        }
        setLoading(true);
        handleGetFilmByTitleService(title)
        .then(response => {
            setOptions(response.map(film => ({value: film.title})));
            setFilmSearch(response);
        })
        .catch(err => {
            console.error("Error fetching films by title", err);
        })
        .finally(() => {
            setLoading(false);
        });    
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
                        onClick={() => {window.location.href = "/";}}
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
                <AutoComplete
                    style={{width:300,height:32}}
                    options={options}
                    onChange={fetchFilms}
                    >
                    <Input.Search 
                        size="middle" 
                        placeholder="Search films..."
                        loading={loading}
                        style={{borderRadius:16,height:32}}
                        onSearch={() => {
                            setStore(prev => ({...prev, films: filmSearch}));
                            setOptions([]);
                        }}
                        />
                </AutoComplete>
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

function debounce(fetchFilms: (title: string) => Promise<void>, arg1: number) {
    throw new Error("Function not implemented.");
}
