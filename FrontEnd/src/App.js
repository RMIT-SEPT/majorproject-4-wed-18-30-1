import React from 'react';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import Dashboard from './components/Dashboard';
import {BrowserRouter as Router, Switch, Route, Link } from "react-router-dom";
import {Provider} from "react-redux";
import Registration from "./components/Registration";
import Login from "./components/Login";
import Header from './components/Layout/Header';
import store from './store';
import AddUser from './components/Users/AddUser';
import AddCustomer from './components/Users/AddCustomer';
import AddEmployee from './components/Users/AddEmployee';

function App() {
  return (
    <Provider store={store}>
    <Router>
    <div>
      <Header/>
      <Route exact path="/dashboard" component={Dashboard} />
      <Route exact path="/registration" component={Registration} />
      <Route exact path="/login" component={Login} />
      <Route exact path="/AddUser" component={AddUser} />
      <Route exact path="/addCustomer" component={AddCustomer} />
      <Route exact path="/addEmployee" component={AddEmployee} />
    </div>
    </Router>
    </Provider>
  );
}

export default App;
