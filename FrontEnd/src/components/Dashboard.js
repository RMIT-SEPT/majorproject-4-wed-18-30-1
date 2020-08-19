import React, { Component } from 'react'
import User from './Users/User'
import Header from './Layout/Header';
import "bootstrap/dist/css/bootstrap.min.css"

class Dashboard extends Component {
    render() {
        return (
            <div>
                <Header/>
                <h1 className="alert alert-warning"> Welcome to Dashboard</h1>
                <User/>
            </div>
        )
    }
}
export default Dashboard;