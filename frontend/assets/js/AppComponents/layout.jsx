import React from 'react'

import Header from './LayoutComponents/header'
import Body from './LayoutComponents/body'
import Footer from './LayoutComponents/footer'
import Logo from './LayoutComponents/logo'
import Buttons from './LayoutComponents/buttons'

class Layout extends React.Component {
  render() {
    return (
      <div className="container">
        <div className="row">
          <div className="col-sm-8 col-md-8 col-lg-8">
            <Header/>
            <Body picture="Innovative"/>
            <Body picture="Community based"/>
            <Body picture="Sustainable"/>
            <Footer/>
          </div>
          <div className="col-sm-4 col-md-4 col-lg-4">
            <Logo/>
            <Buttons/>
          </div>
        </div>
      </div>
    )
  }
}

export default Layout
