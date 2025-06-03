from django.shortcuts import render
from products.models import *
import random

# Create your views here.

def index(request):
    cart = CheckoutCart.objects.all()
    productsList = Product.objects.all()

    #Give random Product Recomendations
    randomProducts = random.sample(list(productsList), min(len(productsList), 5))
    sum = cart.count()
    userLogin = True
    values = {
        'total' : sum,
        'randomProducts' : randomProducts
    }
    return render(request,'home/index.html',values)