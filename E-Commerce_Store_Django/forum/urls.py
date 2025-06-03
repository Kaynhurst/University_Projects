#Forum URLS

from django.urls import path
from forum import views

app_name= 'forum'

urlpatterns = [
    path('forum/' , views.index , name = "index")
]