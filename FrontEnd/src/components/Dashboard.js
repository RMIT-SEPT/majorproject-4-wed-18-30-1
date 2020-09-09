import React, { Component } from 'react'
import User from './Users/User'
import Header from './Layout/Header';
import "bootstrap/dist/css/bootstrap.min.css"
import CreateUserButton from './Users/CreateUserButton';

class Dashboard extends Component {
    render() {
        return (
            <div className="Users">
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <h1 className="display-4 text-center">Users</h1>
                        <br/>
                        <CreateUserButton/>
                        <br/>
                        <br/>
                        <User/>
                    </div>
                </div>
            </div> 
            </div>
        )
    }
}
export default Dashboard;