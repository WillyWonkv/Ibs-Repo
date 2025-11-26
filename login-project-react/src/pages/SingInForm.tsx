import { Button, Checkbox, Flex, Form, Input } from "antd";
import React, { useState } from "react";
import "./Form.css";
import { useNavigate } from "react-router-dom";
import { handleLogin } from "../service/UsersService";

type FiledType = {
    username: string;
    password: string;
    remember?: boolean;
}

export const SignInForm = () => {

    const navigate = useNavigate();

    const[loading,setLoading] = useState<boolean>(false);

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
                    onFinish={async (values: FiledType) =>  {
                        setLoading(true)
                        await handleLogin(values.username,values.password,navigate)
                        setLoading(false)
                    }}
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