import React, { useState } from "react";
import { Grid, Layout } from "antd";
import { Content, Header } from "antd/es/layout/layout";
import { TopBar } from "../components/TopBar";
import { Body } from "../components/Body";

export const Dashboard = () => {

    //const screens = Grid.useBreakpoint();

    const headerStyle: React.CSSProperties = {
        position:"fixed",
        zIndex:1,
        width:"100%"
    }

    const bodyStyle:React.CSSProperties = {
        margin:"64px 0 0 0",
        padding:"20px 50px 0 50px",
        backgroundColor:"#464646" 
    }

    return(
        <Layout style={{minHeight: "100vh"}}>
            <Header style={headerStyle}>
                <TopBar/>     
            </Header>
            <Content style={bodyStyle}>
                <Body/>
            </Content>
        </Layout>
    );

}