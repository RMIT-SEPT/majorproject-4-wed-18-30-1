import React, { Component } from 'react'
import Header from './Layout/Header';
import CreateCustomerButton from './Users/CreateCustomerButton';
import CreateEmployeeButton from './Users/CreateEmployeeButton';
import CreateBusinessButton from './Users/CreateBusinessButton';

class Registration extends Component {
    render() {
        return (
            <div>

                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <br/>
                            <CreateCustomerButton/>
                            <br/>
                            <br/>
                            <CreateEmployeeButton/>
                            <br/>
                            <br/>
                            <CreateBusinessButton/>
                            <br/>
                            <br/>
                        </div>
                    </div>
                </div> 
            </div>
        )
    }
}
export default Registration;