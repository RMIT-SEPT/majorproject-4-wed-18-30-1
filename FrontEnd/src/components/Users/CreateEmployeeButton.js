import React from 'react'
import {link, Link} from "react-router-dom";

const CreateEmployeeButton=() => {
        return (
            <React.Fragment>
            <Link to="/addEmployee"
            className="btn btn-lg btn-info">
            Register as Employee
            </Link>
            </React.Fragment>
        )
};

export default CreateEmployeeButton;
