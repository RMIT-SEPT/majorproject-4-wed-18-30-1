import React, { Component } from 'react'

class AddCustomer extends Component {
    constructor(){
        super();

        this.state= {

        };
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }
    
    onChange(e){
        this.setState({[e.target.name]: e.target.value});
    }
    onSubmit(e){
        e.preventDefault();
        const newCustomer = {
            name: this.state.name,
            email: this.state.email,
            password: this.state.password,
        }

        console.log(newCustomer);
    }  
    
    render() {
        return (
            <div className="Customer">
            <div className="container">
                <div className="row">
                    <div className="col-md-8 m-auto">
                        <h5 className="display-4 text-center">Registration Form</h5> 
                        <hr />
                        <form onSubmit={this.onSubmit}>
                            <div className="form-group">
                                <input type="text" className="form-control form-control-lg "
                                placeholder="User Name"
                                name="name"
                                value= {this.state.name}
                                onChange = {this.onChange}
                                />
                            </div>
                            <div className="form-group">
                                <input type="text" className="form-control form-control-lg"
                                placeholder="email"
                                value= {this.state.email}
                                onChange= {this.onChange}
                                    />
                            </div>

                            <div className="form-group">
                                <input type="text" className="form-control form-control-lg"
                                placeholder="Password"
                                name="password"
                                value= {this.state.password}
                                onChange= {this.onChange}
                                    />
                            </div>

                            <input type="submit" className="btn btn-primary btn-block mt-4" />

                        </form>
                    </div>
                </div>
            </div>    
        </div>
        )
    }
}

export default AddCustomer;
