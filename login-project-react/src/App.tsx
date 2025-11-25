import React, { useState } from 'react';
import './App.css';
import { SignInForm } from './pages/SingInForm';
import { SignUpForm } from './pages/SignUpForm';
import { Route, Routes } from 'react-router-dom';
import { Dashboard } from './pages/Dashboard';
import { PrivateRoute } from './service/PrivateRoute';

const App = () => {

  return (
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
  );

}

export default App;
