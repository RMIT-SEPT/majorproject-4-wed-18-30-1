import React from 'react'
import {link, Link} from "react-router-dom";

const CreateBusinessButton=() => {
        return (
            <React.Fragment>
            <Link to="/addBusiness"
            className="btn btn-lg btn-info">
            Register a Business
            </Link>
            </React.Fragment>
        )
};

export default CreateBusinessButton;
