from game.models import Match, Bet

def run():
    all_resultless_bets = Bet.objects.filter(result=None).all()
    for bet in all_resultless_bets:
        if bet.match.result != "n/a":
            result = bet.match.result
            if result == "home":
                bet.result = bet.home
            if result == "draw":
                bet.result = bet.draw
            if result == "away":
                bet.result = bet.away
            bet.save()
