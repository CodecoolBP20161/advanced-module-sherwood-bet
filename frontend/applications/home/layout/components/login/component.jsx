import React from 'react'
import Container from 'container'

class Login extends Container{
  reportChange(event, input){
    this.dispatch({type: "INPUT_FIELD_CHANGED", input: input, value: event.target.value})
  }
  reportFocus(input){
    this.dispatch({type: "INPUT_FIELD_GOT_FOCUS", input: input})
  }
  login(username, password){
    let data = {
      username: username,
      password: password
    }
    let request = {
      destination: "login",
      method: "POST",
      data: data,
      action: {type: "LOGIN_RESPONSE_ARRIVED"}
    }
    this.JSONtransfer(request)
    this.dispatch({type: "LOGIN_REQUESTED"})
  }
  render(){
    let username = this.props.data.username
    let password = this.props.data.password
    let loginError = this.props.error
    return(
      <div className={"home-login-container flying"}>
        <div className={"home-login-logo-conatiner"}>
          <p className={"home-login-logo"}>Sherwood<span id={"bet"}>BET</span></p>
        </div>
        <div className={"home-login-input-container"}>
          <div className={"home-login-error-container"}>
            {loginError ? <LoginError/> : null}
          </div>
          <input
            value={username}
            onChange={(event)=>this.reportChange(event, "login-username")}
            onFocus={()=>this.reportFocus("login-username")}
            placeholder={"Username or email"}
            className={"home-login-input"}
            type="text"/>
          <input
            value={password}
            onChange={(event)=>this.reportChange(event, "login-password")}
            onFocus={()=>this.reportFocus("login-password")}
            placeholder={"password"}
            className={"home-login-input"}
            type="password"/>
          <button
            onClick={()=>this.login(username, password)}
            className={"sherwood-button home-login-button"}>
            Login
          </button>
        </div>
      </div>
    )
  }
}

const LoginError = (props) => (
  <p className={"home-login-error"}>Invalid credentials!</p>
)

export default Login
