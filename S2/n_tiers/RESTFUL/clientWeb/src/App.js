import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Authenticate from './Auth/code/Authenticate.js'
import SwitchTabs from './SwitchTabs.js'

class App extends Component {
  render() {
    return (
      <MuiThemeProvider>
          <Authenticate></Authenticate>
          <SwitchTabs></SwitchTabs>
      </MuiThemeProvider>
    );
  }
}

export default App;
