from django.http import HttpResponse


class ApplicationController:

    def __init__(self, request):
        self.request = request

    def get_home(self, is_authenticated):
        if is_authenticated:
            frontend_application = "wall.html"
        else:
            frontend_application = "home.html"
        return frontend_application

    def get_game(self, is_authenticated):
        if is_authenticated:
            frontend_application = "game.html"
        else:
            frontend_application = None
        return frontend_application

    def get_archive(self, is_authenticated):
        if is_authenticated:
            frontend_application = "archive.html"
        else:
            frontend_application = None
        return frontend_application

    def get_community(self, is_authenticated):
        if is_authenticated:
            frontend_application = "community.html"
        else:
            frontend_application = None
        return frontend_application
