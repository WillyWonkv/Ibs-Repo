
import './App.css';
import { SignInForm } from './pages/SingInForm';
import { SignUpForm } from './pages/SignUpForm';
import { Route, Routes } from 'react-router-dom';
import { Dashboard } from './pages/Dashboard';

import React, { createContext, useState, useContext } from 'react';
import { loginResponse } from './types';

interface contextInterface {
  store:loginResponse,
  setStore: React.Dispatch<React.SetStateAction<loginResponse>>
}

export const AppStoreContext = createContext<contextInterface>({
  store: {token:""},
  setStore: () => {}
});

const App = () => {
   const [store, setStore] = useState<loginResponse>({
    token:""
   });

  return ( 
    <AppStoreContext.Provider value={{store,setStore}}>
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
    </AppStoreContext.Provider>
  );

}

export default App;
