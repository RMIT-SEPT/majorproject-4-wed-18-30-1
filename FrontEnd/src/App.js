import React from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import Dashboard from './components/Dashboard';
import {BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";

import Registration from "./components/Registration";

function App() {
  return (
    <div>
    <Registration/>

    </div>
  );
}

export default App;
