using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using hlv1.Models;

public class ProductModel
{
    public string InsertProduct(Product product)
    {
        try
        {
            hlv1.Models.ProductContext db = new hlv1.Models.ProductContext();
            db.Products.Add(product);
            db.SaveChanges();

            return product.ProductName + " was succesfully inserted";
        }
        catch (Exception e)
        {
            return "Error:" + e;
        }
    }

    public string UpdateProduct(int id, Product product)
    {
        try
        {
            hlv1.Models.ProductContext db = new hlv1.Models.ProductContext();

            //Fetch object from db
            Product p = db.Products.Find(id);

            //Replace the data of p with table
            p.ProductName = product.ProductName;
            p.Description = product.Description;
            p.ImagePath = product.ImagePath;
            p.UnitPrice = product.UnitPrice;
            p.CategoryID = product.CategoryID;
            
            db.SaveChanges();

            return product.ProductName + " was succesfully updated";

        }
        catch (Exception e)
        {
            return "Error:" + e;
        }
    }

    public string DeleteProduct(int id)
    {
        try
        {
            hlv1.Models.ProductContext db = new hlv1.Models.ProductContext();
            Product product = db.Products.Find(id);

            db.Products.Attach(product);
            db.Products.Remove(product);

            db.SaveChanges();

            return product.ProductName + " was succesfully deleted";
        }
        catch (Exception e)
        {
            return "Error:" + e;
        }
    }

    public Product GetProduct(int id)
    {
        try
        {
            using (hlv1.Models.ProductContext db = new hlv1.Models.ProductContext())
            {
                Product product = db.Products.Find(id);
                return product;
            }
        }
        catch (Exception)
        {
            return null;
        }
    }

    public List<Product> GetAllProducts()
    {
        try
        {
            using (hlv1.Models.ProductContext db = new hlv1.Models.ProductContext())
            {
                List<Product> products = (from x in db.Products select x).ToList();
                return products;
            }
        }
        catch (Exception)
        {
            return null;
        }
    }

    public List<Product> GetProductsByType(int typeID)
    {
        try
        {
            using (hlv1.Models.ProductContext db = new hlv1.Models.ProductContext())
            {
                List<Product> products = (from x in db.Products where x.CategoryID == typeID select x).ToList();
                return products;
            }
        }
        catch (Exception)
        {
            return null;
        }
    }
}