export const inputFieldChangeReducer = (current, action) => {
  let nextState = current
  let value = action.value
  switch (action.input) {
    case "login-username":
      nextState.data.inputFields.login.username = value
      break
    case "login-password":
      nextState.data.inputFields.login.password = value
      break
    case "signup-username":
      nextState.data.inputFields.signup.username = value
      break
    case "signup-email":
      nextState.data.inputFields.signup.email = value
      break
    case "signup-password":
      nextState.data.inputFields.signup.password = value
      break
    case "signup-password-again":
      nextState.data.inputFields.signup.passwordAgain = value
      break
    default:
      nextState = nextState
  }
  return nextState
}

export const inputFieldGotFocusReducer = (current, action) => {
  let nextState = current
  switch (action.input) {
    case "login-username":
      nextState.state.loginError = false
      nextState.state.signupErrors.username = ""
      nextState.state.signupErrors.email = ""
      nextState.state.signupErrors.password = ""
      nextState.state.signupErrors.passwordAgain = ""
      break
    case "login-password":
      nextState.state.loginError = false
      nextState.state.signupErrors.username = ""
      nextState.state.signupErrors.email = ""
      nextState.state.signupErrors.password = ""
      nextState.state.signupErrors.passwordAgain = ""
      break
    case "signup-username":
      nextState.state.signupErrors.username = ""
      break
    case "signup-email":
      nextState.state.signupErrors.email = ""
      break
    case "signup-password":
      nextState.state.signupErrors.password = ""
      break
    case "signup-password-again":
      nextState.state.signupErrors.passwordAgain = ""
      break
    default:
      nextState = nextState
  }
  return nextState
}

export const inputFieldLostFocusReducer = (current, action) => {
  let nextState = current
  switch (action.input) {
    case "signup-username":
      if (!(usernameIsValid(nextState.data.inputFields.signup.username))) {
        nextState.state.signupErrors.username = "Min 6 characters"
      }
      break
    case "signup-email":
      if (!(emailIsValid(nextState.data.inputFields.signup.email))) {
        nextState.state.signupErrors.email = "Invalid email"
      }
      break
    case "signup-password":
      if (!(passwordIsValid(nextState.data.inputFields.signup.password))) {
        nextState.state.signupErrors.password = "Min 6 characters"
      }
      break
    case "signup-password-again":
      if (!(passwordsAreValid(nextState.data.inputFields.signup.password, nextState.data.inputFields.signup.passwordAgain))) {
        nextState.state.signupErrors.passwordAgain = "Does not match"
      }
      break
    default:
      nextState = nextState
    }
  return nextState
}

const usernameIsValid = (username) => {
  return username.length > 5
}

const emailIsValid = (email) => {
  return email.includes("@") && email.includes(".") && email.length > 7
}

const passwordIsValid = (password) => {
  return password.length > 5
}

const passwordsAreValid = (password, passwordAgain) => {
  return password == passwordAgain
}
