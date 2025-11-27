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

    return(
        <Layout>
            <Header style={headerStyle}>
                <TopBar/>     
            </Header>
            <Content>
                <Body/>
            </Content>
        </Layout>
    );

}