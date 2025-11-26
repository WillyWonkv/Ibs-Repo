import React, { useState } from "react";
import { Grid, Layout } from "antd";
import { Content, Header } from "antd/es/layout/layout";
import { TopBar } from "../components/TopBar";

export const Dashboard = () => {

    const [profiletext, setProfiletext] = useState("Sign in");

    //const screens = Grid.useBreakpoint();

    const contentStyle: React.CSSProperties = {
        margin:"0",
        padding:"0",
        marginTop:"64px",
        height:"200vh"
    }

    const headerStyle: React.CSSProperties = {
        position:"fixed",
        width:"100%"
    }

    return(
        <Layout>
            <Header style={headerStyle}>
                <TopBar/>     
            </Header>
            <Content style={contentStyle}>
                
            </Content>
        </Layout>
    );

}