from django.shortcuts import render
from django.http import JsonResponse
from game.API import TicketController, ResultController

def get_playable_tickets(request):
    controller = TicketController(request)
    result_of_process = controller.get_playable_tickets()
    return JsonResponse(result_of_process)

def get_ticket(request):
    controller = TicketController(request)
    result_of_process = controller.get_ticket()
    return JsonResponse(result_of_process)

def bet(request):
    controller = TicketController(request)
    result_of_process = controller.place_bet()
    return JsonResponse(result_of_process)

def get_my_tickets(request):
    controller = ResultController(request)
    result_of_process = controller.get_my_tickets()
    return JsonResponse(result_of_process)
