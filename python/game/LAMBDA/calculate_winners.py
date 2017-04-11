from game.models import UserTicket, Ticket, Match

def get_number_of_players(user_tickets):
    return len(user_tickets)

def enough_players_played(user_tickets):
    return 20 <= user_tickets

def calculate_one_gap(user_tickets):
    return int(user_tickets/20)

def calculate_middle(user_tickets):
    return user_tickets % 20

def set_payoffs(user_tickets, gap, middle_gap):
    sorted_user_tickets = user_tickets.order_by('-result')
    payoffs = [0.1 * number for number in range(21)]
    payoffs.remove(1)
    for index, user_ticket in enumerate(sorted_user_tickets):
        payoff_index = 20
        current_payoff = payoffs[payoff_index]
        if index % gap == 0:
            payoff_index -= 1

def run():
    # all_tickets_with_no_winner = Ticket.objects.filter(winners_calculated=False)
    all_tickets_with_no_winner = Ticket.objects.all()
    for ticket in all_tickets_with_no_winner:
        all_matches = ticket.matchofticket_set.all()
        number_of_matches = len(all_matches)
        number_of_matches_with_result = len(["x" for mot_obj in all_matches if mot_obj.match.result != "n/a"])
        if number_of_matches == number_of_matches_with_result:
            categories = [0, 1, 10, 100]
            for category in categories:
                all_user_tickets = ticket.userticket_set.filter(category=category)
                sorted_tickets = all_user_tickets.order_by('-result')
                number_of_players = len(sorted_tickets)
                for index, user_ticket in enumerate(sorted_tickets):
                    position = index + 1
                    user_ticket.rank = position
                    user_ticket.status = "calculated"
                    user_ticket.save()
            ticket.winners_calculated = True
            ticket.save()
