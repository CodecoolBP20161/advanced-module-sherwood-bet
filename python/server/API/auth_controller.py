import json
from server.services import AuthService


class AuthController:

    def __init__(self, request):
        self.request = request

    def signup_user(self):
        signup_data = json.loads(self.request.body.decode("utf-8"))
        service = AuthService(signup_data)

        errors = service.check_if_possible()
        if not errors:
            service.create_user()
        response = {"errors": errors}

        return response

    def login_user(self):
        login_data = json.loads(self.request.body.decode("utf-8"))
        service = AuthService(login_data)

        is_successful = service.login_user(self.request)
        response = {"login_successful": is_successful}

        return response

    def logout_user(self):
        logout_data = self.request
        service = AuthService(logout_data)

        is_completed = service.logout_user()
        response = {"logout_completed": is_completed}

        return response

    def authenticate_session(self):
        session_data = self.request.session
        service = AuthService(session_data)

        is_authenticated = service.authenticate()

        return is_authenticated

    def get_session_data(self):
        session_data = self.request.session
        user_data = {
            "username": session_data["name"],
            "balance": session_data["balance"],
            "game_money": session_data["game_money"],
            "rank": session_data["rank"],
            "basic_points": session_data["basic_points"],
            "monthly_points": session_data["monthly_points"]
        }
        return user_data

    def get_user_from_session(self):
        user_id = self.request.session["_auth_user_id"]
        service = AuthService(user_id)
        user = service.get_user()
        return user
