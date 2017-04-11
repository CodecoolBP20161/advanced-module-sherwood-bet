from game.models import UserTicket, Bet

def run():
    all_resultless_user_tickets = UserTicket.objects.all().filter(result=None)
    for user_ticket in all_resultless_user_tickets:
        all_bets = user_ticket.bet_set.all()
        all_bets_number = len(["x" for bet in all_bets])
        number_of_bets_with_result = len(["x" for bet in all_bets if bet.result != None])
        if number_of_bets_with_result == all_bets_number:
            user_ticket.result = 1
            for bet in all_bets:
                current = user_ticket.result
                user_ticket.result = bet.result * current
            user_ticket.save()
