import React, { Component } from 'react'
import Header from './Layout/Header';

class Registration extends Component {
    render() {
        return (
            <div>
                <Header/>

                <h3>Sign Up</h3>

                <form>
                    <div className="form-group">
                        <label>Email Address</label>
                        <input type="text" className="form-control" placeholder="Enter email" />
                    </div>
                    
                    <div className="form-group">
                        <label>First Name</label>
                        <input type="text" className="form-control" placeholder="First name" />
                    </div>

                    <div className="form-group">
                        <label>Last Name</label>
                        <input type="text" className="form-control" placeholder="Last name" />
                    </div>

                    <div className="form-group">
                        <label>Password</label>
                        <input type="text" className="form-control" placeholder="Enter password" />
                    </div>

                    <button type="submit" className="btn btn-primary btn-block">Register</button>
                    <p className="forgot-password text-right">
                        Already Registered <a href="#">sign in?</a>
                    </p>
                </form>



            </div>
        )
    }
}
export default Registration;