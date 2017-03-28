import React from 'react'
import Container from 'container'

class Signup extends Container{
  reportChange(event, input){
    this.dispatch({type: "INPUT_FIELD_CHANGED", input: input, value: event.target.value})
  }
  reportFocus(input){
    this.dispatch({type: "INPUT_FIELD_GOT_FOCUS", input: input})
  }
  reportBlur(input){
    this.dispatch({type: "INPUT_FIELD_LOST_FOCUS", input: input})
  }
  signup(username, email, password, passwordAgain){
    if (this.allFieldsAreCorrect(username, email, password, passwordAgain)) {
      let data = {
        username: username,
        email: email,
        password: password
      }
      let request = {
        destination: "signup",
        method: "POST",
        data: data,
        action: {type: "SIGNUP_RESPONSE_ARRIVED"},
      }
      this.JSONtransfer(request)
      this.dispatch({type: "SIGNUP_REQUESTED"})
    }
  }
  allFieldsAreCorrect(username, email, password, passwordAgain){
    return (
      username.length > 5 && email.includes("@") && email.includes(".") && password.length > 5 && password == passwordAgain
    )
  }
  render(){
    let username = this.props.data.username
    let email = this.props.data.email
    let password = this.props.data.password
    let passwordAgain = this.props.data.passwordAgain
    let errors = this.props.errors
    let responseStatus = this.props.responseStatus
    return(
      <div className={"home-signup-container flying"}>
        <div className={"home-signup-title-container"}>
          <h2 className={"home-signup-title"}>
            Create your account in seconds!
          </h2>
        </div>
        <div className={"home-signup-input-container"}>
          <input
            value={username}
            onChange={(event)=>this.reportChange(event, "signup-username")}
            onFocus={()=>this.reportFocus("signup-username")}
            onBlur={()=>this.reportBlur("signup-username")}
            className={"home-signup-input"}
            placeholder={"Username"}
            type={"text"}/>
          <p className={"home-signup-error"}>{errors.username}</p>
        </div>
        <div className={"home-signup-input-container"}>
          <input
            value={email}
            onChange={(event)=>this.reportChange(event, "signup-email")}
            onFocus={()=>this.reportFocus("signup-email")}
            onBlur={()=>this.reportBlur("signup-email")}
            className={"home-signup-input"}
            placeholder={"Email"}
            type={"email"}/>
          <p className={"home-signup-error"}>{errors.email}</p>
        </div>
        <div className={"home-signup-input-container"}>
          <input
            value={password}
            onChange={(event)=>this.reportChange(event, "signup-password")}
            onFocus={()=>this.reportFocus("signup-password")}
            onBlur={()=>this.reportBlur("signup-password")}
            className={"home-signup-input"}
            placeholder={"Password"}
            type={"password"}/>
          <p className={"home-signup-error"}>{errors.password}</p>
        </div>
        <div className={"home-signup-input-container"}>
          <input
            value={passwordAgain}
            onChange={(event)=>this.reportChange(event, "signup-password-again")}
            onFocus={()=>this.reportFocus("signup-password-again")}
            onBlur={()=>this.reportBlur("signup-password-again")}
            className={"home-signup-input"}
            placeholder={"Password again"}
            type={"password"}/>
          <p className={"home-signup-error"}>{errors.passwordAgain}</p>
        </div>
        <div className={"home-signup-button-container"}>
          <button
            onClick={()=>this.signup(username, email, password, passwordAgain)}
            className={"sherwood-button home-signup-button"}>
            Signup
          </button>
        </div>
      </div>
    )
  }
}

export default Signup
