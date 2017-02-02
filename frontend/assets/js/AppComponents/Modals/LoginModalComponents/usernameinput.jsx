import React from 'react'
import mediator from '../../../core'

class UsernameInput extends React.Component{
  constructor(){
    super()
    this.style = {
      width: '60%',
      margin: '10px',
      fontSize: '1.4em',
      textAlign: 'center',
    }
    this.updateUsername = (event) => {
      mediator.store.dispatch({type: "LOGIN_USERNAME_CHANGED", value: event.target.value})
    }
  }
  render(){
    let username = this.props.data
    return (
      <input
        type="text"
        onChange={this.updateUsername}
        value={username}
        style={this.style}
        className="sherwood-input"
        placeholder="Username"/>
    )
  }
}

export default UsernameInput
