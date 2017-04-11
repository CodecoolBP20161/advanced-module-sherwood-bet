from django.db import models
from django.contrib.auth.models import User


class ProfileManager(models.Manager):

    def create_profile(self, user):
        username = user.username
        profile_picture_url = "https://robohash.org/" + username + "?size=120x120&set=set3"
        profile = Profile.objects.create(
            user=user,
            profile_picture=profile_picture_url
        )
        profile.save()
        return profile


class Profile(models.Model):

    user = models.OneToOneField(User, on_delete=models.CASCADE)
    rank = models.CharField(max_length=150, default="newbie")
    profile_picture = models.CharField(max_length=250, default="https://robohash.org/bela?size=120x120&set=set3")
    basic_points = models.IntegerField(default=0)
    monthly_points = models.IntegerField(default=0)

    objects = ProfileManager()

    def __str__(self):
        return self.user.username + "'s profile"


class AccountManager(models.Manager):

    def create_account(self, user):
        account = Account.objects.create(
            user=user,
        )
        account.save()
        return account


class Account(models.Model):

    user = models.OneToOneField(User, on_delete=models.CASCADE)
    balance = models.FloatField(default=0)
    game_money = models.FloatField(default=1000)

    objects = AccountManager()

    def __str__(self):
        return self.user.username + "'s account"
