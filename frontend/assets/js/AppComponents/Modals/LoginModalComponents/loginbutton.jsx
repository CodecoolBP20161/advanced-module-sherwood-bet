import React from 'react'
import dataManager from '../../../datamanager'

class LoginButton extends React.Component{
  constructor(){
    super()
    this.style = {
      width: '150px',
      height: '50px',
      fontSize: '1.5em',
      marginTop: '30px',
    }
    this.sendData = (event) => {
      let loginData = this.props.loginData
      let data = {
        username: loginData.username,
        password: loginData.password,
      }
      let request = {
        method: "POST",
        destination: "login",
        data: data,
        action: "LOGIN_RESPONSE_ARRIVED",
      }
      dataManager.JSONtransfer(request)
    }
  }
  render(){
    return (
      <button className="sherwood-button" onClick={this.sendData} style={this.style}>
        Login&nbsp;&nbsp;
       <span className="glyphicon glyphicon-play" aria-hidden="true"></span>
      </button>
    )
  }
}

export default LoginButton
