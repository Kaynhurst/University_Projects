#Main URLs
from django.urls import path, include
from .views import *


urlpatterns = [
    path('api/', AppView.as_view()),
    path('api/<int:app_id>/',AppDetailView.as_view()),
    path('register',UserView.as_view()),
    path('', index, name ="home")
]