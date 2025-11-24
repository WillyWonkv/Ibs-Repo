import { HomeFilled, UserOutlined, YoutubeFilled } from "@ant-design/icons";
import { Button, Flex } from "antd";
import  "../components/TopBar.css"
import React from "react";

const buttonStyle: React.CSSProperties = {

    color:"white",
    fontFamily:"sans-serif",
    fontWeight:600  

}

const topBarStyle: React.CSSProperties = {
    height: "100%", 
    width:"100%"
}

export const TopBar = () => {

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
                <Button 
                    shape="round"
                    type="text"
                    iconPlacement="start"
                    className="userbutton"
                    style={{
                        color:"white"
                    }}
                    icon={<UserOutlined/>}>Sign In</Button>
            </Flex>

        </Flex>

    );

}