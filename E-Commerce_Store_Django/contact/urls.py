#Contact URLS

from django.urls import path
from . import views

app_name='contact'

urlpatterns = [
    path('contact/',views.index , name="index")
]