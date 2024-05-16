import React, { Component } from 'react';
import './../../index.css';
import HomeLoginComponent from "./trace/trace";


class HomeLoginComponent extends Component {

    render() {
        return (
            <form onSubmit={handleSubmitEvent}>
                <div className="form_control">
                    <label htmlFor="user-email">Email:</label>
                    <input
                    type="email"
                    id="user-email"
                    name="email"
                    placeholder="example@yahoo.com"
                    aria-describedby="user-email"
                    aria-invalid="false"
                    onChange={handleInput}
                    />
                    <div id="user-email" className="sr-only">
                    Please enter a valid username. It must contain at least 6 characters.
                    </div>
                </div>
                <div className="form_control">
                    <label htmlFor="password">Password:</label>
                    <input
                    type="password"
                    id="password"
                    name="password"
                    aria-describedby="user-password"
                    aria-invalid="false"
                    onChange={handleInput}
                    />
                    <div id="user-password" className="sr-only">
                    your password should be more than 6 character
                    </div>
                </div>
                <button className="btn-submit">Submit</button>
            </form>
        )
    }
}

export default HomeLoginComponent;
