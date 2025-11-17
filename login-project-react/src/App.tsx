import React, { useState } from 'react';
import './App.css';
import { Button } from './login/micro/Button';
import { SignIn } from './login/macro/SingIn';
import { SignUp } from './login/macro/SignUp';

const App = () => {

  const [view, setView] = useState<"home" | "login" | "register">("home");

  return (
    <>
      {view === "home" && (
        <div className='homebutton flex'>
          <Button textButton={'Register'} onclick={() => {setView("register")}}></Button>
          <Button textButton={'Login'} onclick={() => {setView("login")}}></Button>
        </div>
      )}
      {view === "login" && <SignIn></SignIn>}
      {view === "register" && <SignUp></SignUp>}
    </>
  ); //ReactRouter da fare

}

export default App;
