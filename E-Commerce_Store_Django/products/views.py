from django.http import Http404
from django.shortcuts import render ,redirect
from .models import *

# Create your views here.

#Index
def index(request):
    productsList = Product.objects.all()
    gradeList = Grade.objects.all()
    categoryList = Category.objects.all()

    cart = CheckoutCart.objects.all()
    sum = cart.count()

    if productsList is not None :
        return render(request,'products/index.html',{'productsList':productsList , 'gradeList':gradeList, 'categoryList' : categoryList,'total' : sum})
    else:
        return Http404("Something went wrong with database...")
    

#Shopping Cart Methods
def cart(request):
    checkoutCart = CheckoutCart.objects.all()
    total = 0

    for product in checkoutCart:
        total += sum(product.item.productPrice * item.quantity for item in checkoutCart)

    values = {
        'checkoutCart' : checkoutCart,
        'total' : total
    }
    return render(request,'products/cart.html',values)
   

def addProduct(request, item_id):

    product = Product.objects.get(id=item_id)
    cart_item, created = CheckoutCart.objects.get_or_create(item=product)

    cart_item.quantity += 1
    cart_item.save()

    return redirect('products:index')


def removeProduct(request, item_id):

    print(Product.objects.get(id=item_id))
    product = Product.objects.get(id=item_id)
    product.delete()
    product.save()

    return redirect('products:cart')

def searchProduct(request):
    search_result = None 
    search_result = request.GET.get("search_product")

    products = []
    if search_result is not None:
        # Filter products where productName contains the search_result (case-insensitive)
        products = Product.objects.filter(productName__icontains=search_result)
        print(Product.objects.filter(productName__icontains=search_result))
            
    values = {
        'products': products
        }

    return render(request, 'products/search.html', values)