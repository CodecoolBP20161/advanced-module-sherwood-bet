from django.shortcuts import render, redirect
from django.http import JsonResponse
from server.API import AuthController, ApplicationController, DataController


def signup(request):
    controller = AuthController(request)
    result_of_process = controller.signup_user()
    return JsonResponse(result_of_process)


def login(request):
    controller = AuthController(request)
    result_of_process = controller.login_user()
    return JsonResponse(result_of_process)


def logout(request):
    controller = AuthController(request)
    result_of_process = controller.logout_user()
    return JsonResponse(result_of_process)

def get_session_data(request):
    controller = AuthController(request)
    result_of_process = controller.get_session_data()
    return JsonResponse(result_of_process)

def home(request):
    controller = AuthController(request)
    is_authenticated = controller.authenticate_session()
    controller = ApplicationController(request)
    frontend_application = controller.get_home(is_authenticated)
    return render(request, frontend_application)

def game(request):
    controller = AuthController(request)
    is_authenticated = controller.authenticate_session()
    if not is_authenticated:
        return redirect('/')
    controller = ApplicationController(request)
    frontend_application = controller.get_game(is_authenticated)
    return render(request, frontend_application)

def archive(request):
    controller = AuthController(request)
    is_authenticated = controller.authenticate_session()
    if not is_authenticated:
        return redirect('/')
    controller = ApplicationController(request)
    frontend_application = controller.get_archive(is_authenticated)
    return render(request, frontend_application)

def community(request):
    controller = AuthController(request)
    is_authenticated = controller.authenticate_session()
    if not is_authenticated:
        return redirect('/')
    controller = ApplicationController(request)
    frontend_application = controller.get_community(is_authenticated)
    return render(request, frontend_application)
