from game.models import Team, Match, Ticket, UserTicket, Bet
from server.API import AuthController
import json

class BetService:

    def __init__(self, request):

        self.request = request
        self.auth_controller = AuthController(request)
        self.user = None
        self.bet_data = None
        self.ticket = None
        self.category = None
        self.mode = None
        self.user_ticket = None
        self.bets = []

    def process_request(self):
        session_status = self.process_session()
        if session_status["error"] is not None:
            return session_status
        bet_status = self.parseJSON()
        if bet_status["error"] is not None:
            return bet_status
        ticket_status = self.get_ticket()
        if ticket_status["error"] is not None:
            return ticket_status
        category_status = self.get_category()
        if category_status["error"] is not None:
            return category_status
        user_ticket_status = self.get_user_ticket()
        if user_ticket_status["error"] is not None:
            return user_ticket_status
        user_ticket_mode = self.get_mode()
        if user_ticket_mode["error"] is not None:
            return user_ticket_mode
        account_status = self.get_account_status()
        if account_status["error"] is not None:
            return account_status
        return {"error": None}

    def process_session(self):
        if not self.auth_controller.authenticate_session():
            return {"error": "Authenticaton failed"}
        self.user = self.auth_controller.get_user_from_session()
        if self.user == None:
            return {"error": "Authenticaton failed"}
        return {"error": None}

    def parseJSON(self):
        try:
            self.bet_data = json.loads(self.request.body.decode("utf-8"))
            return {"error": None}
        except:
            return {"error": "Wrong JSON"}

    def get_ticket(self):
        try:
            ticket_id = int(self.bet_data["ticket_id"])
        except:
            return {"error": "Invalid ID"}
        ticket_exists = Ticket.objects.filter(number=ticket_id).exists()
        if not ticket_exists:
            return {"error": "Not found"}
        self.ticket = Ticket.objects.get(number=ticket_id)
        return {"error": None}

    def get_category(self):
        try:
            self.category = int(self.bet_data["category"])
        except:
            return {"error": "Category not found"}
        if self.category == 1 or self.category == 10 or self.category == 100:
            return {"error": None}
        return {"error": "Invalid category"}

    def get_mode(self):
        self.mode = self.bet_data["mode"]
        if self.mode == "free" or self.mode == "pro":
            return {"error": None}
        return {"error": "Invalid mode"}

    def get_user_ticket(self):
        exists = UserTicket.objects.filter(user=self.user, ticket=self.ticket, category=self.category).exists()
        if exists:
            self.user_ticket = UserTicket.objects.get(user=self.user, ticket=self.ticket, category=self.category)
            return {"error": None}
        return {"error": "User ticket not found"}

    def validate_payment(self):
        if self.mode == "free":
            balance = self.user.account.game_money
        if self.mode == "pro":
            balance = self.user.account.balance
        amount = self.category
        new_payment_possible = amount <= balance
        already_paid = self.user_ticket.paid
        return new_payment_possible or already_paid

    def get_account_status(self):
        transfer_accepted = self.validate_payment()
        if transfer_accepted:
            return {"error": None}
        return {"error": "cheat"}

    def pay_if_needed(self):
        if not self.user_ticket.paid:
            if self.mode == "free":
                current_account = self.user.account.game_money
                current_account -= self.category
                self.user.account.game_money = current_account
            if self.mode == "pro":
                current_account = self.user.account.balance
                current_account -= self.category
                self.user.account.balance = current_account
            self.user.account.save()

    def update_user_ticket(self):
        update_status = self.update_bets()
        if update_status["error"] is not None:
            return {"error": "Error occured during updating bets"}
        self.save_bets()
        self.user_ticket.status = "modifiable"
        self.pay_if_needed()
        self.user_ticket.paid = True
        self.user_ticket.save()
        return {"Ticket_modified": True}

    def update_bets(self):
        try:
            for match_events in self.bet_data["match_events"]:
                bet = match_events["bet"]
                related_bet = Bet.objects.get(id=int(bet["id"]))
                related_bet.home = bet["home"]
                related_bet.draw = bet["draw"]
                related_bet.away = bet["away"]
                related_bet.out = bet["out"]
                self.bets.append(related_bet)
            return {"error": None}
        except:
            return {"error": "Error occured"}

    def save_bets(self):
        for bet in self.bets:
            bet.save()
