from game.models import Team, Match, Ticket
from server.API import AuthController

class OfferService:

    def __init__(self, request):

        self.request = request
        self.auth_controller = AuthController(request)
        self.mode = None

    def process_request(self):
        session_status = self.process_session()
        if session_status["error"] is not None:
            return session_status
        mode_status = self.process_mode_param()
        if mode_status["error"] is not None:
            return mode_status
        return {"error": None}

    def process_session(self):
        if not self.auth_controller.authenticate_session():
            return {"error": "Authenticaton failed"}
        return {"error": None}

    def process_mode_param(self):
        self.mode = self.request.GET.get("mode")
        if not (self.mode == "free" or self.mode == "pro"):
            return {"error": "Query error"}
        return {"error": None}

    def form_response(self):

        playable_tickets = Ticket.objects.filter(playable=True).filter(mode=self.mode)
        response = {"tickets": [ticket.__asDict__() for ticket in playable_tickets]}
        return response
