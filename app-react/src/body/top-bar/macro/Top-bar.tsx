import React from 'react';
import "./Top-bar.css";
import SearchBar from '../micro/SearchBar';
import LogoYT from '../micro/LogoYT';
import Login from '../micro/Login';

const TopBar = () => {

    return(

        <div className='flex head'>
            <LogoYT></LogoYT>
            <SearchBar></SearchBar>
            <Login></Login>
        </div>

    );

}

export default TopBar;