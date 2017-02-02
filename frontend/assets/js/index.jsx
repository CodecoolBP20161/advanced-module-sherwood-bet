import React from 'react'
import ReactDOM from 'react-dom'

require("../../../common/css/style.css")

import mediator from './core'
import App from './app'
const app = document.getElementById('app')

const render = () => {
  console.log(mediator.store.getState())
  ReactDOM.render(<App data={mediator.store.getState()}/>, app)
}

mediator.store.subscribe(render)
render()
