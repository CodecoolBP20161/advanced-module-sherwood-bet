import React from 'react'

import SignupInterface from './SignupModalComponents/SignupInterface'
import Success from './SignupModalComponents/success'

class SignupModal extends React.Component {
  render() {
    let data = this.props.data
    let messages = this.props.messages
    let status = this.props.status
    return (
      <div>
        <div className="s-modal" id="signupModal">
          {status == "start" ? <SignupInterface data={data} messages={messages}/> : <Success/>}
        </div>
        <ModalBackground/>
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

const ModalBackground = () => (
  <div className="s-modal-background" id="signupModalBackground"/>
)

export default SignupModal
