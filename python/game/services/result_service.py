from game.models import Team, Match, Ticket, UserTicket, Bet
from server.API import AuthController
import json

class ResultService:

    def __init__(self, request):
        self.request = request
        self.auth_controller = AuthController(request)
        self.user = None
        self.mode = None
        self.status = None
        self.category = None

    def process_request(self):
        session_status = self.process_session()
        if session_status["error"] is not None:
            return session_status
        mode_status = self.process_mode_param()
        if mode_status["error"] is not None:
            return mode_status
        category_status = self.process_category_param()
        if category_status["error"] is not None:
            return category_status
        status_status = self.process_status_param()
        if status_status["error"] is not None:
            return status_status
        return {"error": None}

    def process_session(self):
        if not self.auth_controller.authenticate_session():
            return {"error": "Authenticaton failed"}
        self.user = self.auth_controller.get_user_from_session()
        if self.user == None:
            return {"error": "Authenticaton failed"}
        return {"error": None}

    def process_mode_param(self):
        self.mode = self.request.GET.get("mode")
        possible_modes = ["free", "pro", "all"]
        if self.mode not in possible_modes:
            return {"error": "Query error"}
        return {"error": None}

    def process_category_param(self):
        self.category = self.request.GET.get("category")
        possible_categories = [1, 10, 100, "all"]
        if self.category not in possible_categories:
            return {"error": "Query error"}
        return {"error": None}

    def process_status_param(self):
        self.status = self.request.GET.get("status")
        possible_statuses = ["won", "lost", "modifiable", "in play", "all"]
        if self.status not in possible_statuses:
            return {"error": "Query error"}
        return {"error": None}

    def form_response(self):
        response = {"user_tickets": []}
        user_tickets = UserTicket.objects.filter(user=self.user).exclude(status="created")
        if self.mode != "all":
            user_tickets = user_tickets.filter(mode=self.mode)
        if self.status != "all":
            user_tickets = user_tickets.filter(status=self.status)
        if self.category != "all":
            user_tickets = user_tickets.filter(category=category)
        response["user_tickets"] = [user_ticket.__asDict__() for user_ticket in user_tickets]
        return response
