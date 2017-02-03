import React from 'react'

class InfoModal extends React.Component {
  render() {
    return (
      <div>
        <div className="s-modal" style={{textAlign: 'justify', width: '80%', left: '10%', height: '500px'}} id="infoModal">
          Info
        </div>
        <ModalBackground/>
      </div>
    )
  }
}

const ModalBackground = () => (
  <div className="s-modal-background" id="loginModalBackground"/>
)

export default InfoModal
