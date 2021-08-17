import React, { useEffect, useState } from 'react'
import { Theme as DarkToggle } from '../Theme'
import {logo, menu, arrow_left } from '../asset-icons'
import { Link,  useHistory, useLocation } from 'react-router-dom'
import { NavSub } from './NavSub'

export const Nav = () => {
    
    const history = useHistory()
    const location = useLocation()
    const pathname = location.pathname
    const [isPathname, setPathname] = useState(pathname)
    
    useEffect(() => {
        setPathname(pathname)
    }, [pathname])
    
    return (
        <>
       <div className='flex-space-between-row p-md fixed-top nav-bg'>
           {isPathname === '/'? <button className='color-gray-500'>{menu}</button> : <button  onClick={() => history.goBack()}>{arrow_left}</button>}
            <Link className='color-gray-400 logo'to={{ pathname:'/',state: { fromDashboard: true }}} > {logo}</Link>
            {isPathname === '/'? <DarkToggle className='color-gray-500'/> : <> <button>{menu}</button> <DarkToggle className='hidden color-gray-500'/></>}

        </div> 
        </>
    )
}
