import React, { useState, useEffect } from 'react'

export const About = () => {
  const [getText, setText] = useState("");
  const tryFetch = () => {
    fetch("/apps")
    .then(x=>x.text())
    .then(x=>setText(x))
    .catch(x=>setText("error: " + x));
  }

  return (
    <div className='flex-center-column h-md text-center'>
      <h1 >Wanna know about me? 🐶 </h1>
      <button onClick={()=>tryFetch()}>click me</button>
      response: {getText}
    </div>
  )
}
