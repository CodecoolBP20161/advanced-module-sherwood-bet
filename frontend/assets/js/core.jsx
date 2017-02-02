import { createStore } from 'redux'
import stateManager from './statemanager'

const mediator = (() => {

  const initialState = {
    view: "base",

    modalStatus: "start",

    loginError: null,

    signupErrors: {
      username: "",
      email: "",
      password: "",
      passwordAgain: "",
    },

    inputFields: {
      login: {
        username: "",
        password: "",
      },
      signup: {
        username: "",
        email: "",
        password: "",
        passwordAgain: "",
      }
    }
  }

  const reducer = (state = initialState, action) => {
    stateManager.update(state)
    switch (action.type) {

      case "SIGNUP_RESPONSE_ARRIVED":
        return stateManager.signupResponseReducer(action.data)
      case "LOGIN_RESPONSE_ARRIVED":
        return stateManager.loginResponseReducer(action.data)

      case "LOGIN_START_REQUESTED":
        return stateManager.viewReducer("loginModal")
      case "SIGNUP_START_REQUESTED":
        return stateManager.viewReducer("signupModal")
      case "READING_START_REQUESTED":
        return stateManager.viewReducer("infoModal")
      case "BACK_TO_BASE":
        return stateManager.viewReducer("base")

      case "LOGIN_USERNAME_CHANGED":
        return stateManager.loginUsernameUpdateReducer(action.value)
      case "LOGIN_PASSWORD_CHANGED":
        return stateManager.loginPasswordUpdateReducer(action.value)

      case "SIGNUP_USERNAME_CHANGED":
        return stateManager.signupUsernameUpdateReducer(action.value)
      case "SIGNUP_EMAIL_CHANGED":
        return stateManager.signupEmailUpdateReducer(action.value)
      case "SIGNUP_PASSWORD_CHANGED":
        return stateManager.signupPasswordUpdateReducer(action.value)
      case "SIGNUP_PASSWORD_AGAIN_CHANGED":
        return stateManager.signupPasswordAgainUpdateReducer(action.value)

      case "SIGNUP_USERNAME_LEFT":
        return stateManager.signupUsernameLeftReducer()
      case "SIGNUP_EMAIL_LEFT":
        return stateManager.signupEmailLeftReducer()
      case "SIGNUP_PASSWORD_LEFT":
        return stateManager.signupPasswordLeftReducer()
      case "SIGNUP_PASSWORDAGAIN_LEFT":
        return stateManager.signupPasswordAgainLeftReducer()

      case "SIGNUP_USERNAME_FOCUS":
        return stateManager.signupUsernameFocusReducer()
      case "SIGNUP_EMAIL_FOCUS":
        return stateManager.signupEmailFocusReducer()

      default:
        return state
    }
  }

  const store = createStore(reducer)

  return {store}

})()

export default mediator
