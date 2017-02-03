import React from 'react'

import UsernameInput from './LoginModalComponents/usernameinput'
import PasswordInput from './LoginModalComponents/passwordinput'
import BackButton from './LoginModalComponents/backbutton'
import LoginButton from './LoginModalComponents/loginbutton'

class LoginModal extends React.Component {
  render() {
    let data = this.props.data
    let loginError = this.props.message
    return (
      <div>
        <div className="s-modal" style={{textAlign: 'center'}} id="loginModal">
          <LoginTitle/>
          <MessagePlace message={ loginError == true ? <ErrorMessage/> : null}/>
          <div style={{width: '100%%'}}>
              <UsernameInput data={data.username}/>
          </div>
          <div style={{width: '100%%'}}>
              <PasswordInput data={data.password}/>
          </div>
          <MessagePlace message={ loginError == false ? <SuccessMessage/> : null }/>
          <BackButton/>
          <LoginButton loginData={this.props.data}/>
        </div>
        <ModalBackground/>
      </div>
    )
  }
}

const LoginTitle = () => (
  <h1 style={{margin: '10px 10px 40px 10px'}}>Login and start playing</h1>
)

const MessagePlace = (props) => (
  <div style={{height: '20px'}}>{props.message}</div>
)

const ErrorMessage = () => (
  <p style={{color: '#d9534f'}}>
    <span className="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
    &nbsp;&nbsp;Invalid credentials
  </p>
)

const SuccessMessage = () => (
  <p style={{color: 'green'}}>
    <span className="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
    &nbsp;&nbsp;Authnetication successful, please wait...
  </p>
)

const ModalBackground = () => (
  <div className="s-modal-background" id="loginModalBackground"/>
)

export default LoginModal
