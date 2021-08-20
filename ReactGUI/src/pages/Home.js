import React, {useState, useEffect} from 'react'
import { Card } from '../components/Card/Card'
import { Link } from 'react-router-dom'
export const Home = () => {
  const [getHost, setHost] = useState({});
  useEffect(
    ()=>(
      fetch('http://localhost:8888')
        .then(x=>x.text())
        .then(x=>setHost(x))
        .catch(x=>setHost({error: "cannot do the thing"}))
    ),
    []
  )
  return (
    <div className='flex-center-column gap-sm test'>
      <h1 className='title'>Welcome! Remote Control Server</h1>
      <p> GUI configuration for android remote control app </p>
      <p className="text">{JSON.stringify(getHost)}</p>

      <Card >
        <h4  style={{color : 'var(--color-gray-700)'}}>Key Bindings</h4>
        <p className='color-gray-500 my-md font-default'>Manage shortcuts, assign images</p>
        <Link className='color-gray-400 logo'to={{ pathname:'/components',state: { fromDashboard: true }}} > <button className='button-solid-md px-lg font-micro'>Shortcuts</button></Link>
      </Card>

      <Card >
        <h4 style={{color : 'var(--color-gray-700)'}}>SEO Friendly 😍</h4>
        <p className='color-gray-500 my-md font-default'>100% optimized for search engine results ranking based on Lighthouse</p>
        <Link className='color-gray-400 logo'to={{ pathname:'/about',state: { fromDashboard: true }}} > <button className='button-ghost-md px-lg font-micro'>Docs</button></Link>
      </Card>

      <Card >
        <h4 style={{color : 'var(--color-gray-700)'}}>PWA Optimized 📲 </h4>
        <p className='color-gray-500 my-md font-default'>Fully optimized and ready to install on any devices by clicking the menu bar on your web browser then selecting Install App options</p>
        <Link className='color-gray-400 logo'to={{ pathname:'/about',state: { fromDashboard: true }}} ><button  className='button-outline-md px-lg font-micro'>Docs</button></Link>
      </Card>

      <Card >
        <h4 style={{color : 'var(--color-gray-700)'}}>API Routes 🐶</h4>
        <p className='color-gray-500 my-md font-default'>Optionally create API endpoints to provide backend functionality.</p>
        <Link className='color-gray-400 logo'to={{ pathname:'about',state: { fromDashboard: true }}} ><button className='button-solid-md px-lg font-micro'>Docs</button></Link>
      </Card>

      <div className='flex-center-column gap-md h-sm'/>
    </div>
  )
}
