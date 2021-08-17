import React, { useState } from 'react'
import { solarize } from './asset-icons';

export const Theme = (props) => {
    const [ isDark, setDark ] = useState(true);
    
    if (localStorage.theme === 'dark' || (!('theme' in localStorage) && window.matchMedia('(prefers-color-scheme: dark)').matches)) {
        document.documentElement.setAttribute('data-theme', 'dark')
    } else { document.documentElement.setAttribute('data-theme', 'light') }

    function toggleTheme() {
        if(isDark? localStorage.theme = 'light' : localStorage.theme = 'dark') setDark(!isDark)
    }
    return (
        <button onClick={toggleTheme} {...props}>
            {solarize}
        </button>
    )
}
