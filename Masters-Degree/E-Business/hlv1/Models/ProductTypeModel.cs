using hlv1.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

public class ProductTypeModel
{
    // Admin add new category
    public string InsertCategory(Category category)
    {
        try
        {
            hlv1.Models.ProductContext db = new hlv1.Models.ProductContext();
            db.Categories.Add(category);
            db.SaveChanges();

            return category.CategoryName + " was succesfully inserted";
        }
        catch (Exception e)
        {
            return "Error:" + e;
        }
    }

    // Admin edit category
    public string UpdateCategory(int id, Category category)
    {
        try
        {
            hlv1.Models.ProductContext db = new hlv1.Models.ProductContext();

            //Fetch object from db
            Category p = db.Categories.Find(id);

            //Replace the data of p with table
            p.CategoryName = category.CategoryName;
            p.Description = category.Description;
            p.CatImagePath = category.CatImagePath;

            db.SaveChanges();

            return category.CategoryName + " was succesfully updated";

        }
        catch (Exception e)
        {
            return "Error:" + e;
        }
    }
    
    // Admin delete category
    public string DeleteCategory(int id)
    {
        try
        {
            hlv1.Models.ProductContext db = new hlv1.Models.ProductContext();
            Category category = db.Categories.Find(id);

            db.Categories.Attach(category);
            db.Categories.Remove(category);

            db.SaveChanges();

            return category.CategoryName + " was succesfully deleted";
        }
        catch (Exception e)
        {
            return "Error:" + e;
        }
    }
    // Get category
    public Category GetCategory(int id)
    {
        try
        {
            using (hlv1.Models.ProductContext db = new hlv1.Models.ProductContext())
            {
                Category category = db.Categories.Find(id);
                return category;
            }
        }
        catch (Exception)
        {
            return null;
        }
    }
}