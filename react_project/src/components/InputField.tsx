import React from 'react'
import './styles.css'

const InputField = () => {
  return <form className='input'>
        <input className='input_box' type='input' placeholder='Enter a task'/>  
        <button className='input_submit' type='submit'>Go</button>
  </form>

};

export default InputField
