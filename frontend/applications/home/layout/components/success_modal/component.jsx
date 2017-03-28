import React from 'react'
import Container from 'container'

class SuccessModal extends Container{
  confirmSuccess(){
    this.modal.classList.add("disappear")
    this.modal.classList.add("flyaway")
    setTimeout(()=>{this.dispatch({type: "MODAL_CLOSED"})}, 400)
  }
  componentDidMount(){
    this.modal = document.getElementById('successModal')
    this.background = document.getElementById('successBackground')
  }
  render(){
    return(
      <div>
        <div className={"home-success-modal-background"} id={"successBackground"}></div>
        <div className={"home-success-modal"} id={"successModal"}>
          <h1 className={"home-success-modal-title"}>
            Congratulations!
          </h1>
          <p className={"home-success-modal-text"}>
            You are now a member of the community of gamblers.
            You can continue with a login, and explore the site.
          </p>
          <div className={"home-success-modal-button-container"}>
            <button
              onClick={()=>this.confirmSuccess()}
              className={"sherwood-button"}>
              Continue
            </button>
          </div>
        </div>
      </div>
    )
  }
}

export default SuccessModal
