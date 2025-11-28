import React, { useState } from "react";
import { Grid, Layout } from "antd";
import { Content, Header } from "antd/es/layout/layout";
import { TopBar } from "../components/TopBar";
import { Body } from "../components/Body";

export const Dashboard = () => {

    const [profiletext, setProfiletext] = useState("Sign in");

    //const screens = Grid.useBreakpoint();

    const headerStyle: React.CSSProperties = {
        position:"fixed",
        width:"100%"
    }

    const bodyStyle:React.CSSProperties = {
    margin:"64px 0 0 0",
    padding:"20px 50px 0 50px",
    height: "calc(100vh - 64px)",
    backgroundColor:"#464646" 
    }

    return(
        <Layout>
            <Header style={headerStyle}>
                <TopBar/>     
            </Header>
            <Content style={bodyStyle}>
                <Body/>
            </Content>
        </Layout>
    );

}