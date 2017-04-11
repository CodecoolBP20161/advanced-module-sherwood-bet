from django.db import models
from django.contrib.auth.models import User

class Team(models.Model):

    name = models.CharField(max_length=30)
    short_name = models.CharField(max_length=10)
    stadium = models.CharField(max_length=20)

    def __str__(self):
        return self.short_name

class Match(models.Model):

    home_team = models.ForeignKey(Team, on_delete=models.CASCADE, related_name="home_team")
    away_team = models.ForeignKey(Team, on_delete=models.CASCADE, related_name="away_team")
    league = models.CharField(max_length=30, default="Friendly")
    round = models.CharField(max_length=10, default="", blank=True)
    link = models.CharField(max_length=100, default="", blank=True)
    deadline = models.DateField(null=True)
    status = models.CharField(max_length=15, default="Not yet started")

    def __str__(self):
        return self.home_team.short_name + " - " + self.away_team.short_name

    def __asDict__(self):
        return {
            "id": self.id,
            "match": self.__str__(),
            "venue": self.home_team.stadium,
            "tournament": self.league,
            "round_number": self.round,
            "more_info": self.link,
            "match_date": self.deadline
        }

class Ticket(models.Model):

    number = models.IntegerField(primary_key=True)
    title = models.CharField(max_length=30)
    intro = models.CharField(max_length= 300)
    deadline = models.DateField(null=True, blank=True)
    playable = models.BooleanField(default=True)
    winners_calculated = models.BooleanField(default=False)
    mode = models.CharField(default="free", max_length=10)
    only_results = models.BooleanField(default=True)

    def __str__(self):
        return self.title

    def __asDict__(self):
        return {
            "title": self.title,
            "deadline": self.deadline,
            "ticket_id": self.number,
            "playable": self.playable,
            "intro": self.intro,
            "mode": self.mode,
            "only_results": self.only_results,
            "matches": [str(obj.match_event) for obj in self.matcheventofticket_set.all()]
        }

class Event(models.Model):

    name = models.CharField(max_length=30)

    def __str__(self):
        return self.name

class MatchEvent(models.Model):

    match = models.ForeignKey(Match, on_delete=models.CASCADE)
    event = models.ForeignKey(Event, on_delete=models.CASCADE)
    result = models.CharField(max_length=5, default="n/a")

    def __str__(self):
        return str(self.match) + " // " + str(self.event)

    def __asDict__(self):
        return {
            "id": self.id,
            "match": str(self.match),
            "venue": self.match.home_team.stadium,
            "tournament": self.match.league,
            "round_number": self.match.round,
            "more_info": self.match.link,
            "match_date": self.match.deadline,
            "event": self.event.name,
            "status": self.match.status
        }


class MatchEventOfTicket(models.Model):

    ticket = models.ForeignKey(Ticket, on_delete=models.CASCADE)
    match_event = models.ForeignKey(MatchEvent, on_delete=models.CASCADE)

    def __str__(self):
        return str(self.ticket.number) + " - " + str(self.match_event)

class UserTicketManager(models.Manager):

    def create_user_ticket(self, user, ticket, category, mode):
        user_ticket = self.create(
            user=user,
            ticket=ticket,
            category=category,
            status="created",
            mode=mode,
        )
        user_ticket.save()
        return user_ticket

class UserTicket(models.Model):

    user = models.ForeignKey(User, on_delete=models.CASCADE)
    ticket = models.ForeignKey(Ticket, on_delete=models.CASCADE)
    mode = models.CharField(max_length=5)                           # free, lotto, pro
    category = models.IntegerField()                                # 1, 10, 100
    status = models.CharField(max_length=20, default="created")     # created, modifiable, in play, over
    result = models.BigIntegerField(null=True, default=None)        # 0 - 100000000000
    rank = models.IntegerField(null=True, default=None)             # 1 - Nbr of players
    paid = models.BooleanField(default=False)
    payoff = models.FloatField(null=True)                           # 0.0x - 2.0x

    objects = UserTicketManager()

    def __str__(self):
        return str(self.user) + "'s ticket (" + str(self.ticket) + ") with " + str(self.category) + "$"

    def __asDict__(self):
        return {
            "user_ticket_id": self.id,
            "ticket": self.ticket.number,
            "title": self.ticket.title,
            "mode": self.mode,
            "category": self.category,
            "status": self.status,
            "result": self.result,
            "rank": self.rank,
            "payoff": self.payoff
        }

class BetManager(models.Manager):

    def create_bet(self, match_event, user_ticket):
        bet = self.create(
            match_event=match_event,
            user_ticket=user_ticket
        )
        bet.save()
        return bet

class Bet(models.Model):

    match_event = models.ForeignKey(MatchEvent, on_delete=models.CASCADE, null=True)
    user_ticket = models.ForeignKey(UserTicket, on_delete=models.CASCADE)
    home = models.IntegerField(default=0)
    draw = models.IntegerField(default=0)
    away = models.IntegerField(default=0)
    out = models.CharField(max_length=10, null=True)
    result = models.IntegerField(null=True, default=None)

    objects = BetManager()

    def __str__(self):
        return str(self.user_ticket) + " / " + str(self.match_event)

    def __asDict__(self):
        return {
            "match_event": self.match_event.id,
            "user_ticket": self.user_ticket.id,
            "home": self.home,
            "draw": self.draw,
            "away": self.away,
            "id": self.id,
            "out": self.out
        }
