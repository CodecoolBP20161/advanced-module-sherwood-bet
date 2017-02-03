import React from 'react'

class Body extends React.Component {
  constructor(){
    super()
    this.divStyle = {
      height: '100px',
      color: 'green',
    }
    this.pictureStyle = {
      display: 'flex',
      alignItems: 'center',
      textAlign: 'center',
      backgroundColor: '#5cb85c',
      border: '15px solid gold',
      borderRadius: '15px',
      color: 'gold',
      height: '100px',
      fontSize: '1.8em',
    }
  }
  render() {
    return (
      <div className="row">
        <div className="col-sm-3 col-md-3 col-lg-3">
          <div style={this.pictureStyle}>
            {this.props.picture}
          </div>
        </div>
        <div className="col-sm-9 col-md-9 col-lg-9">
          <div className="well" style={this.divStyle}>
            text text text text text
          </div>
        </div>
      </div>
    )
  }
}

export default Body
