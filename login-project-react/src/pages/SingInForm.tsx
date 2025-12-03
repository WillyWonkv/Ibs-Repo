import { Button, Checkbox, Flex, Form, Input } from "antd";
import React, { useContext, useState } from "react";
import "./Form.css";
import { useNavigate } from "react-router-dom";
import { handleLoginService } from "../service/UsersService";
import { AppContext, openNotification } from "../App";
import { loginResponse } from "../types";

type FiledType = {
    username: string;
    password: string;
    remember?: boolean;
}

export const SignInForm = () => {

    const navigate = useNavigate();
    const[loading,setLoading] = useState<boolean>(false);
    const {setStore,store} = useContext(AppContext);

    const handleLogin = ({username,password}:{
        username: string;
        password: string;
    }) => {
            setLoading(true);
            handleLoginService(username,password)
            .then(response => {
                localStorage.setItem("token",response.token || "");
                setStore((prev: loginResponse) => ({
                    ...prev,
                    token: response.token,
                    username: response.username,
                    roles: response.roles,
                    permissions: response.permissions,
                }));
                openNotification("success","Logged in")
                navigate("/", {replace:true});
            }).catch(err => {
                console.error("Login failed", err);
                openNotification("error","Login failed","Please check your credentials and try again.");
            }).finally(() => {
                setLoading(false);
            });
    }   

    return(
        <Flex className="pagestyle">
            
            <Flex className="formcontainer">

                <span className="title">LOGIN</span>

                <Form
                    className="form"
                    name="basic"
                    labelCol={{span:24}}
                    wrapperCol={{span:24}}
                    autoComplete="off"
                    initialValues={{remember: false}}
                    onFinish={handleLogin}
                    onFinishFailed={() => console.log("Failed")}
                    >
                
                    <Form.Item<FiledType>
                        className="inputname"
                        label="USERNAME"
                        name="username"
                        rules={[{required:true, message: "Please input your username!"}]}                        
                    >
                        <Input className="inputbox" placeholder="username"/>
                    </Form.Item>

                    <Form.Item<FiledType>
                        className="inputname"
                        label="PASSWORD"
                        name="password"
                        rules={[{required:true, message: "Please input your password!"}]}
                    >
                        <Input.Password className="inputbox" placeholder="••••••••"/>    
                    </Form.Item>

                    <Form.Item<FiledType>
                        name="remember"  
                        valuePropName="checked"
                        label={null}  
                    >
                        <Checkbox className="inputname">Remember me</Checkbox>
                    </Form.Item>

                    <Form.Item label={null}>
                        <Button className="submitbutton" htmlType="submit" loading={loading}>
                            SIGN IN
                        </Button>
                    </Form.Item>

                </Form>

                <span className="footer">
                    Don't have an account?
                    <Button 
                        type="link"
                        onClick={() => (navigate("/users/register"))}
                        >Sign up</Button>    
                </span>

            </Flex>

        </Flex>
    );


}