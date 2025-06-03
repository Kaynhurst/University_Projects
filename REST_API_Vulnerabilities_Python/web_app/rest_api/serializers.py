from django.utils.timezone import localtime
from psycopg2 import IntegrityError
from rest_framework import serializers
from .models import Tasks
from django.contrib.auth import get_user_model

UserModel = get_user_model()

class AppSerializer(serializers.ModelSerializer):

    formatted_time = serializers.SerializerMethodField()
    formatted_updated = serializers.SerializerMethodField()

    class Meta:
        model = Tasks
        fields = ['id','task','description','completed','formatted_time','formatted_updated','user']

        
    def create(self, validated_data):
        try:
            return super().create(validated_data)
        except IntegrityError as e:
            if "duplicate key value violates unique constraint" in str(e):
                raise serializers.ValidationError({"id": "A task with this ID already exists."})
            raise e
    
    def get_formatted_time(self, obj):
        return localtime(obj.timestamp).strftime('%B %d, %Y at %I:%M %p')

    def get_formatted_updated(self, obj):
        return localtime(obj.updated).strftime('%B %d, %Y at %I:%M %p')
    


class UserSerializer(serializers.ModelSerializer):

    password = serializers.CharField(write_only=True)

    def create(self, user_data):
        user = UserModel.objects.create_user(
            username= user_data['username'],
            password = user_data['password']) 
        
        return user
    
    class Meta :
        model = UserModel
        fields = ('id','username','password')