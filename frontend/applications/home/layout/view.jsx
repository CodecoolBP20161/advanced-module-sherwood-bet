import React from 'react'

import ViewDependencies from 'view_dependencies'

import Info from './components/info/component'
import Quote from './components/quote/component'
import Signup from './components/signup/component'
import Login from './components/login/component'
import InfoModal from './components/info_modal/component'
import Background from './components/background/component'
import SuccessModal from './components/success_modal/component'

class View extends React.Component{
  render(){
    let controller = this.props.controller
    let data = this.props.model.data
    let state = this.props.model.state
    return(
      <div>
        <ViewDependencies/>
        <Background/>

        {/* Fixed, top */}
        <Login
          controller={controller}
          responseStatus={state.responseStatus}
          error={state.loginError}
          data={data.inputFields.login}/>

        {/* Left side, middle */}
        <Quote
          controller={controller}/>

        {/* Right side, upper half */}
        <Info
          controller={controller}/>

        {/* Right side, bottom */}
        <Signup
          controller={controller}
          responseStatus={state.responseStatus}
          errors={state.signupErrors}
          data={data.inputFields.signup}/>

        {/* Modal */}
        {state.view == "info" ?
          <InfoModal
            controller={controller}
            view={state.infoView}/> : null}

        {state.view == "signupSuccess" ?
          <SuccessModal
            controller={controller}/> : null}

      </div>
    )
  }
}

export default View
