import React from 'react';
import { MemoryRouter} from 'react-router-dom'
import { Theme } from './components/Theme';
import { router as Route} from './pages/router'
import {solarize} from './components/asset-icons'
import { Nav } from './components/Header/Nav';
import { NavSub } from './components/Header/NavSub';
import { useHistory } from 'react-router';

const App = () => {

  return (
    <MemoryRouter>
      <Nav/>
      {/* Route is located at pages folder */}
      <Route/>
    </MemoryRouter>
  );
}

export default App;
