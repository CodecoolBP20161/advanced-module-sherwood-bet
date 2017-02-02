import React from 'react'

import BackButton from './backbutton'
import InputField from './inputfield'
import SignupButton from './signupbutton'

class SignupInterface extends React.Component {
  render() {
    let data = this.props.data
    let messages = this.props.messages
    return (
      <div>
        <SignupTitle/>
        <div style={{width: '100%'}}>
          <InputField data={data.username} type="text" placeholder="Username"
            event="SIGNUP_USERNAME_CHANGED" leave="SIGNUP_USERNAME_LEFT" focus="SIGNUP_USERNAME_FOCUS"/>
          <ErrorMessage message={messages.username}/>
        </div>
        <div style={{width: '100%'}}>
          <InputField data={data.email} type="email" placeholder="Email"
            event="SIGNUP_EMAIL_CHANGED" leave="SIGNUP_EMAIL_LEFT" focus="SIGNUP_EMAIL_FOCUS"/>
          <ErrorMessage message={messages.email}/>
        </div>
        <div style={{width: '100%'}}>
          <InputField data={data.password} type="password" placeholder="Password"
            event="SIGNUP_PASSWORD_CHANGED" leave="SIGNUP_PASSWORD_LEFT" focus="SIGNUP_PASSWORD_FOCUS"/>
          <ErrorMessage message={messages.password}/>
        </div>
        <div style={{width: '100%'}}>
          <InputField data={data.passwordAgain} type="password" placeholder="Password again"
            event="SIGNUP_PASSWORD_AGAIN_CHANGED" leave="SIGNUP_PASSWORDAGAIN_LEFT" focus="SIGNUP_PASSWORD_FOCUS"/>
          <ErrorMessage message={messages.passwordAgain}/>
        </div>
        <div style={{textAlign: 'right', marginRight: '20px'}}>
          <BackButton/>
          <SignupButton data={data}/>
        </div>
      </div>
    )
  }
}

const SignupTitle = () => (
  <h1 style={{margin: '5px 5px 30px 5px'}}>Create your account in seconds</h1>
)

const ErrorMessage = (props) => (
  <p style={{color: '#d9534f', display: 'inline', marginLeft: '15px'}}>{props.message}</p>
)

export default SignupInterface
