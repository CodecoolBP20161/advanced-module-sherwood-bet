from django.contrib.auth.models import User
from server.models import Profile, Account
from django.contrib.auth import authenticate, login, logout


class AuthService:

    def __init__(self, data):
        self.data = data

    def authenticate(self):
        is_authenticated = self.data.__contains__('_auth_user_id')

        return is_authenticated

    def check_if_possible(self):
        username_to_check = self.data["username"]
        email_to_check = self.data["email"]

        username_exists = User.objects.filter(username=username_to_check).exists()
        email_exists = User.objects.filter(email=email_to_check).exists()

        errors = []
        if username_exists:
            errors.append("username")
        if email_exists:
            errors.append("email")

        return errors

    def create_user(self):
        username = self.data["username"]
        email = self.data["email"]
        password = self.data["password"]

        user = User.objects.create_user(
            username=username,
            email=email,
            password=password
        )
        Profile.objects.create_profile(user)
        Account.objects.create_account(user)

        return user

    def login_user(self, request):
        username = self.data["username"]
        password = self.data["password"]

        user = authenticate(username=username, password=password)
        if user is not None:
            login(request, user)
            self.update_session(request, user)
            return True

        return False

    def logout_user(self):
        logout(self.data)
        return True

    def update_session(self, request, user):
        request.session['name'] = user.username
        request.session['rank'] = user.profile.rank
        request.session['profile_picture'] = user.profile.profile_picture
        request.session['basic_points'] = user.profile.basic_points
        request.session['monthly_points'] = user.profile.monthly_points
        request.session['balance'] = user.account.balance
        request.session['game_money'] = user.account.game_money

    def get_user(self):
        user = None
        user_exists = User.objects.filter(pk=int(self.data)).exists()
        if user_exists:
            user = User.objects.get(pk=int(self.data))
        return user
