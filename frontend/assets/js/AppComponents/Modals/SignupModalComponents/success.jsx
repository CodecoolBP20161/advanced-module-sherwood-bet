import React from 'react'
import mediator from '../../../core'

class Success extends React.Component{
  constructor(){
    super()
    this.returnHome = (event) => {
      document.getElementById('signupModal').classList.add('disappear')
      document.getElementById('signupModalBackground').classList.add('fadeaway')
      setTimeout(() => {
        mediator.store.dispatch({type: "BACK_TO_BASE"})
      }, 1000)
    }
  }
  render(){
    return (
      <div style={{textAlign: 'center', padding:'50px'}}>
        <Picture/>
        <button className="sherwood-button" style={{width: '150px', height: '50px', margin: '30px', fontSize: '1.5em'}}
          onClick={this.returnHome}>Continue</button>
      </div>
    )
  }
}

const Picture = () => (
  <div style={{border: '15px solid gold', borderRadius: '30px',backgroundColor: '#5cb85c', color: 'gold', padding: '20px', fontSize: '1.4em'}}>
    <p>You are now a member of the community of the gamblers.</p>
    <h3>Welcome!</h3>
    <p>You can log in now, and start exploring the site.</p>
  </div>
)

export default Success
