import { Flex } from "antd";
import React from "react";
import { useNavigate } from "react-router-dom";

const pageStyle : React.CSSProperties = {

    backgroundColor:"rgba(46, 46, 46, 1)",
    height:"100vh",
    alignItems:"center",
    justifyContent:"center"
}

const containerStyle : React.CSSProperties = {

    flexDirection:"column",
    backgroundColor:"white",
    borderRadius:"15px",
    padding:"15px",
    width:"300px",
    height:"400px"
}

const titleStyle : React.CSSProperties = {

    fontFamily: "'Poppins', sans-serif",
    letterSpacing: "0.5px",
    fontWeight: "800",
    fontSize: "30px",
    color:"rgb(140, 0, 255)",
    textAlign:"center",
    width:"100%",

}

export const SignInForm = () => {

    return(
        <Flex style={pageStyle}>
            
            <Flex style={containerStyle}>
                <span style={titleStyle}>LOGIN</span>

            </Flex>

        </Flex>
    );


}