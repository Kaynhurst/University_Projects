from django.db import models
from django.contrib.auth.models import User
from django.utils.timezone import localtime

# Create your models here.

class Tasks(models.Model):
    task = models.CharField(max_length=180)
    description = models.CharField(max_length= 400)
    timestamp = models.DateTimeField(auto_now_add= True, auto_now= False, blank = True)
    completed = models.BooleanField(default = False , blank = True)
    updated = models.DateTimeField(auto_now=True, blank = True)
    user = models.ForeignKey(User, on_delete=models.CASCADE, blank = True, null= True)

    def __str__(self):
        return (f"{self.task} (Created: {localtime(self.timestamp).strftime('%Y-%m-%d %I:%M %p')})")

    @property
    def formatted_time(self):
        return localtime(self.timestamp).strftime('%B %d,%Y at %I:%M %p')
    
    @property
    def formatted_update(self):
        return localtime(self.updated).strftime('%B %d,%Y at %I:%M %p') 