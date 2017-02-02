import React from 'react'

class Footer extends React.Component {
  constructor(){
    super()
    this.divStyle = {
      height: '150px',
      color: 'green',
    }
  }
  render() {
    return (
      <div className="well" style={this.divStyle}>
        Footer
      </div>
    )
  }
}

export default Footer
