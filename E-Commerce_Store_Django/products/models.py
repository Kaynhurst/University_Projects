from django.db import models

from cyberwareStore.settings import BASE_DIR

# Create your models here.

class Category (models.Model):
    categoryName = models.CharField(max_length=50)
    
    def __str__(self):
        return (f"Category ID : {self.id} | Category Name : {self.categoryName}")
    

class Grade (models.Model):
    gradeName = models.CharField(max_length=50)

    def __str__(self):
        return (f"Grade ID : {self.id} | Grade Name : {self.gradeName}")
    

class Product(models.Model):
    productName = models.CharField(max_length=50)
    productPrice = models.FloatField()
    productGrade = models.ForeignKey(Grade,on_delete=models.CASCADE)
    productCategory = models.ForeignKey(Category,on_delete=models.CASCADE)
    productImage = models.ImageField(upload_to='static/images/productImages',default='static/images/placeholder_item.png')

    def __str__(self):
        return (f"Product ID : {self.id} | Product Name : {self.productName} | Price : {self.productPrice} | Grade : {self.productGrade.gradeName} | Category : {self.productCategory.categoryName}")
    

class CheckoutCart (models.Model):
    item = models.ForeignKey(Product,on_delete=models.CASCADE)
    quantity = models.IntegerField(default=0)

    def __str__(self):
        return (f"Product Name : {self.item.productName}\nProduct Price : {self.item.productPrice}")