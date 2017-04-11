from django.conf.urls import url
from . import views


urlpatterns = [

    # tickets
    url(r'^api/playable_tickets', views.get_playable_tickets, name='get_playable_tickets'),
    url(r'^api/get_ticket', views.get_ticket, name='get_ticket'),

    # bet
    url(r'^api/bet', views.bet, name='bet'),

    #usertickets
    url(r'^api/usertickets', views.get_my_tickets, name='get_my_tickets'),
]
