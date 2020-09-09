import React from 'react'
import {link, Link} from "react-router-dom";

const CreateCustomerButton=() => {
        return (
            <React.Fragment>
            <Link to="/addCustomer"
            className="btn btn-lg btn-info">
            Register as Customer
            </Link>
            </React.Fragment>
        )
};

export default CreateCustomerButton;
