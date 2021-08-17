import React from 'react'
import { Route, Switch } from 'react-router-dom'
import { PageNotFound } from './404'
import { About } from './About'
import { Components } from './Components'
import { Home } from './Home'


export const router = () => {
    return (
        <Switch>
            <Route path={'/components'} exact component={Components}/>
            <Route path={'/about'} exact  component={About}/>
            <Route path={'/'} exact component={Home}/>
            <Route component={Home}/>
            // <Route component={PageNotFound}/>
        </Switch>
    )
}
