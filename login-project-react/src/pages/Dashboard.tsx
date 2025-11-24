import React from "react";
import "./Home.css";
import { Grid, Layout } from "antd";
import { Content, Header } from "antd/es/layout/layout";
import { TopBar } from "../components/TopBar";

export const Dashboard = () => {

    const screens = Grid.useBreakpoint();

    const contentStyle: React.CSSProperties = {
        backgroundColor:'#444444',
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