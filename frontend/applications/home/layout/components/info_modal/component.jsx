import React from 'react'
import Modal from 'sherwood_modal'

class InfoModal extends Modal{
  setStyle(){
    return {
      width: "96%",
      left: "2%",
      top: "2vh"
    }
  }
  content(){
    return(
      <div className={"info-modal-content"}>
        <div className={"info-modal-controller"}>
          <button className={"info-modal-button"} onClick={()=>this.close()}>x</button>
        </div>
        <h1>Our Mission</h1>
        <p>
          <span className={"sherwood"}>Sherwood</span><span className={"bet"}>BET</span> is the new era of
          online gambling. Right now, the gambling market
          has probably the worst business model from the customer (player) point of view.
          But this is about to change.
        </p>
        <p>
          You want to bet on a match. You feel like you know what is gonna happen. You feel like
          you know it better then anyone else. You want to play. You want to WIN. There are millions
          out there, who feel the same. You could play against each other. But your only choices
          are large, online gambling companies. They uses mathematicians, algorithms, hundreds of
          computers all made for one purpose, and one purpose only. <span>To make you lose your money.</span>
        </p>
        <p>
          And without a better option, you try to win there. And you do, occasionally. But all in all, on a
          long term you lose. And you, the mass of gamblers lose on a short term too. Every month, just in Europe
          these bookmakers take away <span>1.6 billion euros</span> from you. Then they spend it on adverts, they make their
          name appear in the name of stadiums, on the kit of football players and on billboards.
        </p>
        <p>
          And there, they lie. <span>From your money, they lie to you.</span> They pretend they provide you an
          offer, a place where you can win, a place
          which is not made only to make you lose your money. <span>They ARE made for that</span>. No valid platform has
          ever been built where you can play against each other. That is not a business for them.
        </p>
        <p>
          <span>This has changed. We have brought you a platform.</span> We
          at <span className={"sherwood"}>Sherwood</span><span className={"bet"}>BET</span> invented the future of
          gambling, and we did that for you. With our unique, revolutionary gaming platform you can play
          against each other, thus all these billions of euros will stay among you.
        </p>
        <p className={"info-last-p"}>
          Come, and play with us. Invite your friends. Built the community of gamblers. There will be no
          register fee. Ever.
        </p>
        <div className={"info-modal-link-controller"}>
          <button className={"link-button info-link"} onClick={()=>this.close()}>Sign up NOW</button>
        </div>
      </div>
    )
  }
}

export default InfoModal
