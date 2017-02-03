import React from 'react'
import mediator from '../../../core'

class InputField extends React.Component{
  constructor(){
    super()
    this.style = {
      margin: '5px 20px',
      width: '40%',
      display: 'inline',
    }
    this.updateValue = (event) => {
      mediator.store.dispatch({type: this.props.event, value: event.target.value})
    }
    this.leave = (event) => {
      mediator.store.dispatch({type: this.props.leave})
    }
    this.focus = (event) => {
      mediator.store.dispatch({type: this.props.focus})
    }
  }
  render(){
    return (
      <input
        type={this.props.type} style={this.style}
        value={this.props.data} onChange={this.updateValue}
        placeholder={this.props.placeholder} className="sherwood-input"
        onBlur={this.leave} onFocus={this.focus}/>
    )
  }
}

export default InputField
