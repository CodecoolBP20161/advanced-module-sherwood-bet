const stateTree = {
  state: {
    view: "base",
    infoView: "quickstart",
    responseStatus: null,
    loginError: false,
    signupErrors: {
      username: "",
      email: "",
      password: "",
      passwordAgain: ""
    }
  },
  data: {
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
}

export default stateTree
