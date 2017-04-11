from django.contrib import admin
from game.models import Team, Match, Ticket, Event, MatchEvent, MatchEventOfTicket, UserTicket, Bet

class TeamAdmin(admin.ModelAdmin):
    list_display = ('name', 'short_name', 'stadium')

class UserTicketAdmin(admin.ModelAdmin):
    list_display = ('user', 'ticket', 'result', 'rank', 'status')

admin.site.register(Team, TeamAdmin)
admin.site.register(Match)
admin.site.register(Ticket)
admin.site.register(Event)
admin.site.register(MatchEvent)
admin.site.register(MatchEventOfTicket)
admin.site.register(UserTicket, UserTicketAdmin)
admin.site.register(Bet)
