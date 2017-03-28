import {createStore} from 'redux'
import createApiController from 'datamanager'
import stateTree from './state_tree'

import {
  inputFieldChangeReducer,
  inputFieldGotFocusReducer,
  inputFieldLostFocusReducer
} from './reducers/input_reducers'

import {
  singupResponseReducer,
  loginResponseReducer
} from './reducers/response_reducers'

const core = (() => {

  const initialState = stateTree

  const reducer = (state = initialState, action) => {
    let current = state
    let nextState
    switch (action.type) {
      case "INPUT_FIELD_CHANGED":
        nextState = inputFieldChangeReducer(current, action)
        return nextState
      case "INPUT_FIELD_GOT_FOCUS":
        nextState = inputFieldGotFocusReducer(current, action)
        return nextState
      case "INPUT_FIELD_LOST_FOCUS":
        nextState = inputFieldLostFocusReducer(current, action)
        return nextState
      case "SIGNUP_RESPONSE_ARRIVED":
        nextState = singupResponseReducer(current, action)
        return nextState
      case "LOGIN_RESPONSE_ARRIVED":
        nextState = loginResponseReducer(current, action)
        return nextState
      case "MODAL_OPENED":
        nextState = current
        nextState.state.view = action.modal
        return nextState
      case "MODAL_VIEW_MODIFIED":
        nextState.state.view = action.view
        return nextState
      case "MODAL_CLOSED":
        nextState = current
        nextState.state.view = "base"
        return nextState
      default:
        nextState = current
        return nextState
    }
  }

  const store = createStore(reducer)
  store.JSONtransfer = createApiController(store)

  //--> controller.dispatch, controller.getState, controller.subscribe, controller.JSONtransfer
  return {store}

})()

export default core
