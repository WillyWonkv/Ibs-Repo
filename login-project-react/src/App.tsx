import React, { useState } from 'react';
import './App.css';
import { SignIn } from './login/macro/SingIn';
import { SignUp } from './login/macro/SignUp';
import { Button } from './login/micro/Button';

const App = () => {

  const [view, setView] = useState<"login" | "register" | null> (null);

  if(!view){
    return (
      <div className='homebutton flex'>
        <Button textButton={'Register'} onclick={() => setView("register")}></Button>
        <Button textButton={'Login'} onclick={() => setView("login")}></Button>
      </div>
    );
  }

  return(
    <div>
      {view === "login" && <SignIn/>}
      {view === "register" && <SignUp/>}
    </div>

  );

}

export default App;
