from game.services import ResultService
import json

class ResultController:

    def __init__(self, request):
        self.request = request

    def get_my_tickets(self):

        manager = ResultService(self.request)
        request_status = manager.process_request()
        if request_status["error"] is not None:
            return request_status
        response = manager.form_response()
        return response
