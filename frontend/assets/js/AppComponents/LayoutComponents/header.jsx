import React from 'react'

class Header extends React.Component {
  constructor(){
    super()
    this.divStyle = {
      height: '120px',
      color: 'green',
    }
  }
  render() {
    return (
      <div className="well" style={this.divStyle}>
        <p>
          SherwoodBET is the PvP betting site. 
        </p>
      </div>
    )
  }
}

export default Header
