from game.services import OfferService, TicketService, BetService
import json

class TicketController:

    def __init__(self, request):
        self.request = request

    def get_playable_tickets(self):

        manager = OfferService(self.request)
        request_status = manager.process_request()
        if request_status["error"] is not None:
            return request_status
        response = manager.form_response()
        return response

    def get_ticket(self):

        manager = TicketService(self.request)
        request_status = manager.process_request()
        if request_status["error"] is not None:
            return request_status
        manager.create_user_ticket_if_needed()
        user_ticket_status = manager.get_user_ticket()
        if user_ticket_status["error"] is not None:
            return user_ticket_status
        response = manager.form_response()
        # self.auth_controller.update_session(self.request)
        return response

    def place_bet(self):

        manager = BetService(self.request)
        request_status = manager.process_request()
        if request_status["error"] is not None:
            return request_status
        result_of_process = manager.update_user_ticket()
        return result_of_process
