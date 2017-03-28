import React from 'react'
import Container from 'container'

class Info extends Container{
  showInfo(){
    this.dispatch({type: "MODAL_OPENED", modal: "info"})
  }
  render(){
    return(
      <div className={"home-info-container flying"}>
        <div className={"home-info-text-container"}>
          <p className={"home-info-text"}>
            <span>NO MORE RIP-OFF!</span> SherwoodBET is the PvP betting site. We - unlike other
            betting sites - don <span>NOT</span> want you to lose your money on our site. For them it
            is the business model. Their <span>ONLY</span> income is the money you lose there.
            Here - thanks to our revolutionary gaming platform - the players can play with each
            other. <span>FOR FREE, FOREVER.</span> We don't care who wins or loses... but we <span>GUARANTEE</span>, that the
            money you play with will stay among <span>YOU</span>.
          </p>
        </div>
        <div  className={"home-info-button-container"}>
          <button className={"sherwood-button home-info-button"} onClick={()=>this.showInfo()}>
            Read more
          </button>
        </div>
      </div>
    )
  }
}

export default Info
