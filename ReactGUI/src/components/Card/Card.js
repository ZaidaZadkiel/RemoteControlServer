import React from 'react'

export const Card = (props) => {
    
    return (
        <div className='container-xs flex-start-column flex-space-between h-sm p-md my-sm card' {...props} >
            {props.children}
        </div>
    )
}
