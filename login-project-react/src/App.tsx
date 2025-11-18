import React, { useState } from 'react';
import './App.css';
import { SignInForm } from './pages/SingInForm';
import { SignUpForm } from './pages/SignUpForm';
import { Route, Routes } from 'react-router-dom';
import { Home } from './pages/Home';

const App = () => {

  return (
    <Routes>
      <Route path='/' element={<Home />} />
      <Route path='/register' element={<SignUpForm />} />
      <Route path='/login' element={<SignInForm />} />
    </Routes>
  );

  //fare pagina getallusers, sitemare il path del back e capire cosa fa navigate

}

export default App;
