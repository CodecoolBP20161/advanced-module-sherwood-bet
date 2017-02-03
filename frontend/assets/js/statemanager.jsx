const stateManager = (() => {

  let nextState

  const update = (currentState) => {
    nextState = currentState
  }

  const viewReducer = (view) => {
    nextState.inputFields.signup.username = ""
    nextState.inputFields.signup.email = ""
    nextState.inputFields.signup.password = ""
    nextState.inputFields.signup.passwordAgain = ""
    nextState.inputFields.login.username = ""
    nextState.inputFields.login.password = ""
    nextState.signupErrors.username = ""
    nextState.signupErrors.email = ""
    nextState.signupErrors.password = ""
    nextState.signupErrors.passwordAgain = ""
    nextState.loginError = null
    nextState.view = view
    nextState.modalStatus = "start"
    return nextState
  }

  const loginResponseReducer = (responseJSON) => {
    nextState.loginError = !responseJSON.login_successful
    return nextState
  }

  const signupResponseReducer = (responseJSON) => {
    if (responseJSON.errors.includes("username")) {
      nextState.signupErrors.username = "Already occupied"
    }
    if (responseJSON.errors.includes("email")) {
      nextState.signupErrors.email = "Already occupied"
    }
    if (responseJSON.errors.length == 0) {
      nextState.modalStatus = "Success"
    }
    return nextState
  }

  const loginUsernameUpdateReducer = (value) => {
    nextState.inputFields.login.username = value
    return nextState
  }

  const loginPasswordUpdateReducer = (value) => {
    nextState.inputFields.login.password = value
    return nextState
  }

  const signupUsernameUpdateReducer = (value) => {
    nextState.inputFields.signup.username = value
    if (nextState.signupErrors.username == "Minimum 6 characters" && value.length > 5) {
      nextState.signupErrors.username = ""
    }
    return nextState
  }

  const signupEmailUpdateReducer = (value) => {
    nextState.inputFields.signup.email = value
    if (nextState.signupErrors.email == "Enter a valid email" && value.includes("@") && value.includes(".")  && value.length > 6) {
      nextState.signupErrors.email = ""
    }
    return nextState
  }

  const signupPasswordUpdateReducer = (value) => {
    nextState.inputFields.signup.password = value
    if (nextState.signupErrors.password == "Minimum 6 characters" && value.length > 5) {
      nextState.signupErrors.password = ""
    }
    if (nextState.signupErrors.passwordAgain == "Passwords should match" && value == nextState.inputFields.signup.passwordAgain) {
      nextState.signupErrors.passwordAgain = ""
    }
    return nextState
  }

  const signupPasswordAgainUpdateReducer = (value) => {
    nextState.inputFields.signup.passwordAgain = value
    if (nextState.signupErrors.passwordAgain == "Passwords should match" && value == nextState.inputFields.signup.password) {
      nextState.signupErrors.passwordAgain = ""
    }
    return nextState
  }

  const signupUsernameLeftReducer = () => {
    if (nextState.inputFields.signup.username.length < 6) {
      nextState.signupErrors.username = "Minimum 6 characters"
    }
    return nextState
  }

  const signupEmailLeftReducer = () => {
    let email = nextState.inputFields.signup.email
    if (email.length < 6 || !email.includes("@") || !email.includes(".")) {
      nextState.signupErrors.email = "Enter a valid email"
    }
    return nextState
  }

  const signupPasswordLeftReducer = () => {
    if (nextState.inputFields.signup.password.length < 6) {
      nextState.signupErrors.password = "Minimum 6 characters"
    }
    return nextState
  }

  const signupPasswordAgainLeftReducer = () => {
    if (nextState.inputFields.signup.passwordAgain != nextState.inputFields.signup.password) {
      nextState.signupErrors.passwordAgain = "Passwords should match"
    }
    return nextState
  }

  const signupUsernameFocusReducer = () => {
    if (nextState.signupErrors.username == "Already occupied"){
      nextState.signupErrors.username = ""
    }
    return nextState
  }

  const signupEmailFocusReducer = () => {
    if (nextState.signupErrors.email == "Already occupied"){
      nextState.signupErrors.email = ""
    }
    return nextState
  }

  const API = {
    update,
    loginResponseReducer,
    signupResponseReducer,
    viewReducer,
    loginUsernameUpdateReducer,
    loginPasswordUpdateReducer,
    signupUsernameUpdateReducer,
    signupEmailUpdateReducer,
    signupPasswordUpdateReducer,
    signupPasswordAgainUpdateReducer,
    signupUsernameLeftReducer,
    signupEmailLeftReducer,
    signupPasswordLeftReducer,
    signupPasswordAgainLeftReducer,
    signupUsernameFocusReducer,
    signupEmailFocusReducer
  }

  return API

})()

export default stateManager
