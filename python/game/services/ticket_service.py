from game.models import Team, Match, Ticket, UserTicket, Bet
from server.API import AuthController

class TicketService:

    def __init__(self, request):

        self.request = request
        self.auth_controller = AuthController(request)
        self.user = None
        self.ticket = None
        self.category = None
        self.mode = None
        self.related_matchevents = []
        self.user_ticket = None
        self.related_bets = {}

    def process_request(self):
        session_status = self.process_session()
        if session_status["error"] is not None:
            return session_status
        ticket_status = self.process_ticket_param()
        if ticket_status["error"] is not None:
            return ticket_status
        category_status = self.process_category_param()
        if category_status["error"] is not None:
            return category_status
        mode_status = self.process_mode_param()
        if mode_status["error"] is not None:
            return mode_status
        return {"error": None}

    def process_session(self):
        if not self.auth_controller.authenticate_session():
            return {"error": "Authenticaton failed"}
        self.user = self.auth_controller.get_user_from_session()
        if self.user == None:
            return {"error": "Authenticaton failed"}
        return {"error": None}

    def process_ticket_param(self):
        try:
            ticket_id = int(self.request.GET.get("ticket"))
        except:
            return {"error": "Ticket not found"}
        ticket_exists = Ticket.objects.filter(number=ticket_id).exists()
        if ticket_exists:
            self.ticket = Ticket.objects.get(number=ticket_id)
            self.related_matchevents = [obj.match_event for obj in self.ticket.matcheventofticket_set.all()]
            return {"error": None}
        return {"error": "Ticket not found"}

    def process_category_param(self):
        try:
            self.category = int(self.request.GET.get("category"))
        except:
            return {"error": "Category not found"}
        if self.category is 1 or self.category is 10 or self.category is 100:
            return {"error": None}
        return {"error": "Category not found"}

    def process_mode_param(self):
        self.mode = self.request.GET.get("mode")
        if self.mode == "free" or self.mode == "pro":
            return {"error": None}
        return {"error": "Category not found"}

    def create_user_ticket_if_needed(self):
        user_ticket_exists = UserTicket.objects.filter(
            user=self.user, ticket=self.ticket, category=self.category, mode=self.mode
        ).exists()
        if not user_ticket_exists:
            UserTicket.objects.create_user_ticket(self.user, self.ticket, self.category, self.mode)

    def get_user_ticket(self):
        self.user_ticket = UserTicket.objects.get(user=self.user, ticket=self.ticket, category=self.category, mode=self.mode)
        self.get_related_bets()
        valid_amount_for_new_ticket = self.validate_amount()
        already_paid = self.user_ticket.paid
        if valid_amount_for_new_ticket or already_paid:
            return {"error": None}
        return {"error": "Not enough money"}

    def validate_amount(self):
        if self.mode == "free":
            balance = self.user.account.game_money
        if self.mode == "pro":
            balance = self.user.account.balance
        amount = self.category
        return amount <= balance

    def get_related_bets(self):
        for match_event in self.related_matchevents:
            bet_exists = Bet.objects.filter(match_event=match_event, user_ticket=self.user_ticket).exists()
            if bet_exists:
                bet = Bet.objects.get(match_event=match_event, user_ticket=self.user_ticket)
            else:
                bet = Bet.objects.create_bet(match_event, self.user_ticket)
            self.related_bets[str(match_event.id)] = bet.__asDict__()

    def form_response(self):
        response = {}
        response["only_results"] = self.user_ticket.ticket.only_results
        response["ticket_id"] = self.user_ticket.ticket.number
        response["title"] = self.user_ticket.ticket.title
        response["mode"] = self.user_ticket.mode
        response["category"] = self.user_ticket.category
        response["match_events"] = [match_event.__asDict__() for match_event in self.related_matchevents]
        for match_event in response["match_events"]:
            match_event["bet"] = self.related_bets[str(match_event["id"])]
        return response
