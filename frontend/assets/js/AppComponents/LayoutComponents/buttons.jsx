import React from 'react'
import mediator from '../../core'

class Buttons extends React.Component {
  constructor() {
    super()
    this.divStyle = {
      textAlign: 'center',
    }
    this.buttonStyle = {
      width: '70%',
      height: '50px',
      fontSize: '1.5em',
    }
    this.horizontalLine = {
      fontSize: '1.7em',
      width: '40%',
      textAlign: 'center',
      borderBottom: '5px solid green',
      lineHeight: '0.1em',
      margin: '20px 0 20px',
      marginLeft: '30%',
    }
    this.lineText = {
      padding: '0 10px',
      background: 'white',
      color: 'green',
    }
  }
  startLogin() {
    let action = {type: "LOGIN_START_REQUESTED"}
    mediator.store.dispatch(action)
  }
  startSignup() {
    let action = {type: "SIGNUP_START_REQUESTED"}
    mediator.store.dispatch(action)
  }
  startReading() {
    let action = {type: "READING_START_REQUESTED"}
    mediator.store.dispatch(action)
  }
  render() {
    return (
      <div style={this.divStyle}>
        <button className="sherwood-button" style={this.buttonStyle} onClick={this.startLogin.bind(this)}>
          <span className="glyphicon glyphicon-user" aria-hidden="true"></span>&nbsp;&nbsp;Login
        </button>
        <button className="sherwood-button" style={this.buttonStyle} onClick={this.startSignup.bind(this)}>
          <span className="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>&nbsp;&nbsp;Signup
        </button>
        <p style={this.horizontalLine}><span style={this.lineText}>&nbsp;OR&nbsp;</span></p>
        <button className="sherwood-button" style={this.buttonStyle} onClick={this.startReading.bind(this)}>
          <span className="glyphicon glyphicon-list-alt" aria-hidden="true"></span>&nbsp;&nbsp;Read more
        </button>
      </div>
    )
  }
}

export default Buttons
