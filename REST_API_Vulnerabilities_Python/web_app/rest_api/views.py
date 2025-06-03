from django.shortcuts import render
from django.contrib.auth import get_user_model

from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status , permissions
from rest_framework.generics import CreateAPIView
from rest_framework.permissions import BasePermission
from rest_framework.exceptions import NotFound

from .models import Tasks
from .serializers import *


# Create your views here.

#View all Tasks created by the user
class AppView(APIView):

    permission_classes = [permissions.IsAuthenticated]
    def get(self,request,*args,**kwargs):

        user = self.request.user
        if user.is_superuser:
            apps = Tasks.objects.all()
        else :
            apps = Tasks.objects.filter(user = request.user.id)
        
        serializer = AppSerializer(apps , many = True)
        
        return Response(serializer.data, 
                        status = status.HTTP_200_OK)
    
    def post(self,request,*args,**kwargs):
        
        data = {
            'id' : Tasks.objects.last().id + 1 ,
            'task' : request.data.get('task'),
            'completed':request.data.get('completed'),
            'description' : request.data.get('description'),
            'user' : request.user.id
        }

        serializer = AppSerializer(data=data)

        if serializer.is_valid():
            try:
                
                serializer.save()
                return Response(serializer.data, status=status.HTTP_201_CREATED)
            except IntegrityError as e:

                return Response({"error": "Integrity Error: " + str(e)}, status=status.HTTP_400_BAD_REQUEST)
            

        return Response(serializer.errors,
                        status = status.HTTP_400_BAD_REQUEST)
    


class AppDetailView (APIView):

    permission_classes = [permissions.IsAuthenticated]

    def get_object(self,app_id,user_id):

        try:
            return Tasks.objects.get(id=app_id, user=user_id)
        
        except Tasks.DoesNotExist:
           raise NotFound(f"Object with id : {app_id} does not exist.")
        
#View a certain task create by the user
    def get (self,request,app_id,*args,**kwargs):
        try :
            if request.user.is_superuser:                           #Admin can view any task
                app_instance = Tasks.objects.get(id=app_id)
            else:
                app_instance = self.get_object(app_id, request.user.id)

            if not app_instance:
                return Response({"res": "Object is out of reach."},
                                status=status.HTTP_403_FORBIDDEN)
            
            serializer = AppSerializer(app_instance)
            return Response(serializer.data,
                            status=status.HTTP_200_OK)
        
        except Tasks.DoesNotExist:
            return Response({"res": f"Object with id: {app_id} does not exist."}, 
                            status=status.HTTP_404_NOT_FOUND)
        
    def put(self,request,app_id, *args, **kwargs):

        if request.user.is_staff:                       #Admin can edit any task
            app_instance = Tasks.objects.get(id=app_id)
        else:
            app_instance = self.get_object(app_id, request.user.id)

        if not app_instance:
            return Response({f"res": "Object with id: {app_id} does not exist."},
                            status=status.HTTP_404_NOT_FOUND)
        
        data = {
            "task":request.data.get("task",app_instance.task),
            "completed":request.data.get("completed",app_instance.completed),
            "user":request.user.id,
            'description':request.data.get("description",app_instance.description)
        }

        serializer = AppSerializer(instance = app_instance,data=data,partial=True)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data,
                            status=status.HTTP_200_OK)
        
        return Response(serializer.errors,
                        status=status.HTTP_400_BAD_REQUEST)
    
    def delete(self,request,app_id,*args,**kwargs):
        if request.user.is_staff:                       #Admin can delete any task
            app_instance = Tasks.objects.get(id=app_id)
        else:
            app_instance = self.get_object(app_id, request.user.id)


        if not app_instance:
            return Response({f"res": "Object with id: {app_id} does not exist."},
                            status=status.HTTP_404_NOT_FOUND)
        
        app_instance.delete()
        return Response({"res":"Object Deleted."},
                        status = status.HTTP_200_OK)
    

#Create a new user
class UserView(CreateAPIView):

    user = get_user_model()
    permission_classes = [permissions.AllowAny]

    serializer_class = UserSerializer


class isAdmin(BasePermission):

    def has_permission(self, request, view):
        return request.user.is_authenticated
    
    def has_object_permission(self, request, view, data):
        if request.user.is_staff:
            return True
        
        return data.owner == request.user
    
#HTML Views

def index(request) : 
    return render(request,'index/home.html')