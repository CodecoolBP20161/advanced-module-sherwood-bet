import mediator from './core'
const Cookies = require('./cookies')

const dataManager = (() => {

  const URL = 'http://127.0.0.1:8080/'
  const csrftoken = Cookies.get('csrftoken')

  const JSONtransfer = (req) => {
    const request = new XMLHttpRequest()
    request.open(req.method, URL + req.destination, true)
    if (req.method == "POST") {
      request.setRequestHeader("X-CSRFToken", csrftoken)
      request.setRequestHeader("Content-Type", "application/json;charset=UTF-8")
    }
    request.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        let action = {
          type: req.action,
          data: JSON.parse(this.responseText)
        }
        mediator.store.dispatch(action)
      }
    }
    if (req.method == "POST") {
      request.send(JSON.stringify(req.data))
    }
    if (req.method == "GET") {
      request.send()
    }
  }

  return {JSONtransfer}

})()


export default dataManager
