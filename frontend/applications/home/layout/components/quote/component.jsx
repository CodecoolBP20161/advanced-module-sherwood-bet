import React from 'react'
import Container from 'container'

class Quote extends Container{
  render(){
    return(
      <div className={"home-quote-container"}>
        <p className={"home-quote-text"}>
          That is inevitably what happens (...) It purges these old models that are obsolete
        </p>
        <p className={"home-quote-details"}>
          &nbsp;-&nbsp;Steve Jobs<br/>Playboy interview<br/>1984
        </p>
      </div>
    )
  }
}

export default Quote
