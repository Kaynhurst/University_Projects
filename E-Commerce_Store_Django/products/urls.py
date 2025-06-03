#Product URLS

from django.urls import path
from products import views


app_name = 'products'

urlpatterns = [
    path('products/' , views.index , name="index"),
    path('cart/' , views.cart , name = "cart"),
    
    path('search/',views.searchProduct,name="search"),
    path('search/<str:name>/',views.searchProduct,name="search"),

    path('add/<int:item_id>/', views.addProduct, name="add_product"),
    path('remove/<int:item_id>/',views.removeProduct,name="remove_product")
]
