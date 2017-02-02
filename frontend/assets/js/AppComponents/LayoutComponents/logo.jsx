import React from 'react'

class Logo extends React.Component {
  constructor(){
    super()
    this.divStyle = {
      height: '300px',
    }
    this.quoteStyle = {
      fontFamily: "'Sacramento', cursive",
      textAlign: 'right',
      width: '75%',
      marginLeft: '25%',
      fontSize: '1.4em',
      marginBottom: '20px',
    }
    this.logoPlace = {
      marginTop: '50px',
    }
    this.SherwoodStyle = {
      fontSize: '1.8em',
      color: 'gold',
    }
    this.BetStyle = {
      fontSize: '1.8em',
      color: 'green',
    }
  }
  render() {
    return (
      <div className="well" style={this.divStyle}>
        <p style={this.quoteStyle}>
          That's inevitably what happens. (...)
          It purges the system of these old models that are obsolete.
        </p>
        <p>-Steve Jobs <br/> 1985, Playboy interview</p>
        <h1 style={this.logoPlace}>
          <span style={this.SherwoodStyle}>Sherwood</span><span style={this.BetStyle}>BET</span>
        </h1>
      </div>
    )
  }
}

export default Logo
