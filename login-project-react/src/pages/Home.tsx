import React from "react";
import "./Home.css"
import { Button } from "../components/Button";
import { Link } from "react-router-dom";



export const Home = () => {

    return(

        <div className='homebutton flex'>
            <Link to={"/register"}>
                <Button textButton={'Register'}></Button>
            </Link>
            <Link to={"/login"}> 
                <Button textButton={'Login'}></Button>
            </Link> 
        </div>

    );


}