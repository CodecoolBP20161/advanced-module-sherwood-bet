import React from "react"

import Layout from "./AppComponents/layout"
import InfoModal from "./AppComponents/Modals/infoModal"
import LoginModal from "./AppComponents/Modals/loginModal"
import SignupModal from "./AppComponents/Modals/signupModal"

class App extends React.Component {
  constructor(){
    super()
    this.showModalIfNeedid = (modal, data) => {
      switch (modal) {
        case "loginModal":
          return <LoginModal data={data.inputFields.login} message={data.loginError}/>
        case "signupModal":
          return <SignupModal data={data.inputFields.signup} messages={data.signupErrors} status={data.modalStatus}/>
        case "infoModal":
          return <InfoModal/>
        default:
          null
       }
     }
   }
   render() {
     let data = this.props.data
     if (data.loginError == false) {location.reload()}
     return (
       <div className="app">
         <Layout/>
         {this.showModalIfNeedid(data.view, data)}
       </div>
     )
   }
}

export default App
