export const singupResponseReducer = (current, action) => {
  let nextState = current
  nextState.state.responseStatus = null
  let response = action.data.errors
  let usernameErrorExist = response.includes("username")
  let emailErrorExist = response.includes("email")
  if (!usernameErrorExist && !emailErrorExist) {
    nextState.state.view = "signupSuccess"
    nextState.data.inputFields.signup.username = ""
    nextState.data.inputFields.signup.email = ""
    nextState.data.inputFields.signup.password = ""
    nextState.data.inputFields.signup.passwordAgain = ""
  }
  if (usernameErrorExist) {
    nextState.state.signupErrors.username = "Occupied"
  }
  if (emailErrorExist) {
    nextState.state.signupErrors.email = "Occupied"
  }
  return nextState
}

export const loginResponseReducer = (current, action) => {
  let nextState = current
  let loginIsSuccessful = action.data["login_successful"]
  if (loginIsSuccessful) {
    location.reload();
  } else {
    nextState.state.loginError = true
  }
  return nextState
}
