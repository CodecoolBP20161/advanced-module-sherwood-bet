import React from 'react'
import mediator from '../../../core'

class BackButton extends React.Component {
  constructor(){
    super()
    this.style = {
      width: '150px',
      height: '50px',
      fontSize: '1.5em',
      marginTop: '30px',
    }
    this.turnOff = (event) => {
      document.getElementById('loginModal').classList.add('disappear')
      document.getElementById('loginModalBackground').classList.add('fadeaway')
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
