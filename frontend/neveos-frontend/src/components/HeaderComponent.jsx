import React, { Component } from 'react'
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

class HeaderComponent extends Component {
    
    render() {
        return (
            <header>
                <Navbar bg="light" variant="light">
                    <Container>
                        <a href="/Trace">
                            <img src={require("./../img/logo-actia.png")} alt="" width="170" height="40"></img>
                        </a>
                        <Navbar.Brand href="/Trace">
                            <img src={require("./../img/home.png")} alt="Home" height="30" ></img>
                            Trace viewer
                        </Navbar.Brand>
                        <Nav className="me-auto">
                        </Nav>
                    </Container>
                </Navbar>
                <div className="actia-line"></div>
            </header>
        )
    }
}

export default HeaderComponent
