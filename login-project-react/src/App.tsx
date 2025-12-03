
import './App.css';
import { SignInForm } from './pages/SingInForm';
import { SignUpForm } from './pages/SignUpForm';
import { Route, Routes } from 'react-router-dom';
import { Dashboard } from './pages/Dashboard';

import React, { createContext, useState, useContext, useEffect } from 'react';
import { loginResponse } from './types';
import { notification } from 'antd';

interface contextInterface {
  store:loginResponse,
  setStore: React.Dispatch<React.SetStateAction<loginResponse>>
}

export const AppContext = createContext<contextInterface>({
  store: {
    token:"",
    username:"",
    roles:[],
    permissions:[]
  },
  setStore: () => {}
});

notification.config({
    duration: 3,
    top: 70,
});

export const openNotification = (
    type: 'success' | 'error' | 'info',
    title: string,
    description?: string, 
    )=> {
    notification[type]({
        title,
        description,
        placement: 'topRight',
    });
};

const App = () => {


  const [store, setStore] = useState<loginResponse>({
    token:"",
    username:"",
    roles:[],
    permissions:[]
  });
  
  return ( 
    <AppContext.Provider value={{store,setStore}}>
        <Routes>
          <Route 
            path='/' 
            element={<Dashboard />}
          />
          <Route 
            path='/users/register' 
            element={<SignUpForm />} 
          />
          <Route 
            path='/users/login' 
            element={<SignInForm />} 
          />
        </Routes>
    </AppContext.Provider>
  );

}

export default App;
