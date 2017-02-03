import React from 'react'
import dataManager from '../../../datamanager'

class SignupButton extends React.Component{
  constructor(){
    super()
    this.style = {
      width: '100px',
      height: '40px',
      marginTop: '30px',
    }
    this.sendData = (event) => {
      let signupData = this.props.data
      let data = {
        username: signupData.username,
        email: signupData.email,
        password: signupData.password,
      }
      let request = {
        method: "POST",
        destination: "signup",
        data: data,
        action: "SIGNUP_RESPONSE_ARRIVED",
      }
      if (
        signupData.username.length > 5 &&
        signupData.email.includes("@") && signupData.email.includes(".") &&
        signupData.password.length > 5 && signupData.password == signupData.passwordAgain
      ){dataManager.JSONtransfer(request)}
    }
  }
  render(){
    return (
      <button className="sherwood-button" onClick={this.sendData} style={this.style}>
        Sign up&nbsp;&nbsp;
       <span className="glyphicon glyphicon-play" aria-hidden="true"></span>
      </button>
    )
  }
}

export default SignupButton
