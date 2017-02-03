import React from 'react'
import mediator from '../../../core'

class PasswordInput extends React.Component{
  constructor(){
    super()
    this.style = {
      width: '60%',
      margin: '10px',
      fontSize: '1.4em',
      textAlign: 'center',
    }
    this.updatePassword = (event) => {
      mediator.store.dispatch({type: "LOGIN_PASSWORD_CHANGED", value: event.target.value})
    }
  }
  render(){
    let password = this.props.data
    return (
      <input
        type="password"
        onChange={this.updatePassword}
        value={password}
        style={this.style}
        className="sherwood-input"
        placeholder="Password"/>
    )
  }
}

export default PasswordInput
