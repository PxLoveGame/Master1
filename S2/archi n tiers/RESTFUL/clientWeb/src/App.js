import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Books from './Books/code/Books.js'
import Authenticate from './Auth/code/Authenticate.js'

class App extends Component {
  render() {
    return (
      <MuiThemeProvider>
        <div>
          <Authenticate></Authenticate>
        </div>
        <div>
          <Books></Books>
        </div>
      </MuiThemeProvider>
    );
  }
}

export default App;
