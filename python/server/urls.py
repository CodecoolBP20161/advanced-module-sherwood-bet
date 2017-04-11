from django.conf.urls import url
from . import views


urlpatterns = [

    # authentication
    url(r'^signup', views.signup, name='signup'),
    url(r'^login', views.login, name='login'),
    url(r'^logout', views.logout, name='logout'),
    url(r'^session_data', views.get_session_data, name='get_session_data'),

    # frontend applications
    url(r'^$', views.home, name='home'),
    url(r'^bet/$', views.game, name='game'),
    url(r'^archive/$', views.archive, name='archive'),
    url(r'^community/$', views.community, name='community'),

]
