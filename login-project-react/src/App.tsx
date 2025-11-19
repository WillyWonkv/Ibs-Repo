import React, { useState } from 'react';
import './App.css';
import { SignInForm } from './pages/SingInForm';
import { SignUpForm } from './pages/SignUpForm';
import { Route, Routes } from 'react-router-dom';
import { Home } from './pages/Home';
import { ShowUserForm } from './pages/ShowUser';
import { Dashboard } from './pages/Dashboard';
import { PrivateRoute } from './service/PrivateRoute';

const App = () => {

  return (
    <Routes>
      <Route 
        path='/' 
        element={<Home />} 
      />
      <Route 
        path='/register' 
        element={<SignUpForm />} 
      />
      <Route 
        path='/login' 
        element={<SignInForm />} 
      />
      <Route 
        path='/dashboard' 
        element={
          <PrivateRoute>
            <Dashboard />
          </PrivateRoute>
        }  
      />
      <Route 
        path='/users/getall' 
        element={
          <PrivateRoute>
            <ShowUserForm />
          </PrivateRoute>
        } 
      />
    </Routes>
  );

}

export default App;
