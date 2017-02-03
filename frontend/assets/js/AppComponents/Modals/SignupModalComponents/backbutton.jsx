import React from 'react'
import mediator from '../../../core'

class BackButton extends React.Component {
  constructor(){
    super()
    this.style = {
      width: '100px',
      height: '40px',
      marginTop: '30px',
    }
    this.turnOff = (event) => {
      document.getElementById('signupModal').classList.add('disappear')
      document.getElementById('signupModalBackground').classList.add('fadeaway')
      setTimeout(() => {
        mediator.store.dispatch({type: "BACK_TO_BASE"})
      }, 1000)
    }
  }
  render(){
    return (
      <button className="sherwood-button" onClick={this.turnOff} style={this.style}>
       <span className="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
       Back
      </button>
    )
  }
}

export default BackButton
