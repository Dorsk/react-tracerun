import React, { Component } from 'react';
import './../../index.css';
import TraceAppComponent from "./trace/trace";


class TraceComponent extends Component {

    render() {
        return (
            <div style={{ 'margin-left': 40, 'margin-right': 40}}>
                <h1>Trace Run Viewer</h1>
                <p>Import your trace run</p>
                <TraceAppComponent/>
            </div>
        )
    }
}

export default TraceComponent;
