import { Button, Checkbox, Flex, Form, Input } from "antd";
import React, { useState } from "react";
import "./Form.css";
import { useNavigate } from "react-router-dom";
import { handleRegisterService } from "../service/UsersService";

type FiledType = {
    username: string;
    password: string;
    passwordConfirm: string;
}

export const SignUpForm = () => {

    const navigate = useNavigate();

    const[loading,setLoading] = useState<boolean>(false);

    const handleRegister = ({username,password}:{
        username: string;
        password: string;
    }) => {
        setLoading(true);
        handleRegisterService(username,password)
        .then(() => {
            console.log("Registration successful");
            alert("Registration successful");
            navigate("/users/login");
        })
        .catch((err) => {
            console.error("Registration failed", err);
            alert("Registration failed");
        })
        .finally(() => {
            setLoading(false);
        });
    }

    return(
        <Flex className="pagestyle">
            
            <Flex className="formcontainer">

                <span className="title">SIGN UP</span>

                <Form
                    className="form"
                    name="basic"
                    labelCol={{span:24}}
                    wrapperCol={{span:24}}
                    autoComplete="off"
                    onFinish={handleRegister}
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
                        <Input type={"password"} className="inputbox" placeholder="••••••••"/>    
                    </Form.Item>

                    <Form.Item<FiledType>
                        className="inputname"
                        label="CONFIRM PASSWORD"
                        name="passwordConfirm"
                        dependencies={["password"]}
                        rules={[
                            {required:true, message: "Please input your password!"},
                            ({getFieldValue}) => ({
                                validator(_, value){
                                    if(!value || getFieldValue("password") === value){
                                        return Promise.resolve();
                                    }
                                    return Promise.reject("Passwords do not match!")
                                }
                            })
                        ]}
                    >
                        <Input.Password className="inputbox" placeholder="••••••••"/>    
                    </Form.Item>

                    <Form.Item label={null}>
                        <Button style={{marginTop:"30px"}} className="submitbutton" htmlType="submit" loading={loading}>
                            SIGN UP
                        </Button>
                    </Form.Item>

                </Form>

            </Flex>

        </Flex>
    );


}