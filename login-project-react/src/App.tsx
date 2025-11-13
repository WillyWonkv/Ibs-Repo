import React from 'react';
import './App.css';
import { Container } from './login/micro/Container';

export interface Props{

  textTitle? : string;
  textButton? : string;

}

const App = () => {
  return (
    <div className="flex body">
      <Container textTitle='SIGN UP' textButton='Register'></Container>
    </div>
  );
}

export default App;
