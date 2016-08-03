using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using hlv1.Models;
using System.IO;
using System.Collections;

namespace hlv1.Admin
{

    // This class will handle the adding or editing of products
    public partial class ManageProduct : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (!IsPostBack)
            {
                //Check if the url contains an id parameter
                if (!String.IsNullOrWhiteSpace(Request.QueryString["id"]))
                {
                    int id = Convert.ToInt32(Request.QueryString["id"]);
                    FillPage(id);
                }
            }
        }

        // The button that submits the new or edited data
        protected void EditButton_Click(object sender, EventArgs e)
        {
            ProductModel productModel = new ProductModel();
            Product product = CreateProduct();

            //Check if the url contains an id parameter
            if (!String.IsNullOrWhiteSpace(Request.QueryString["id"]))
            {
                //ID exists -> Update existing row
                int id = Convert.ToInt32(Request.QueryString["id"]);
                LabelEditStatus.Text = productModel.UpdateProduct(id, product);
            }
            else
            {
                //ID does not exist -> Create a new row
                LabelEditStatus.Text = productModel.InsertProduct(product);
            }
        }

        // Fill the text boxes with relevant data from database
        private void FillPage(int id)
        {
            //Get selected product from DB
            ProductModel productModel = new ProductModel();
            Product product = productModel.GetProduct(id);

            //Fill Textboxes
            AddProductDescription.Text = product.Description;
            AddProductName.Text = product.ProductName;
            AddProductPrice.Text = product.UnitPrice.ToString();
           
            //Set dropdownlist values
            DropDownAddCategory.SelectedValue = product.CategoryID.ToString();
        }

        // Create new product
        private Product CreateProduct()
        {
            Product product = new Product();

            product.ProductName = AddProductName.Text;
            product.UnitPrice = Convert.ToDouble(AddProductPrice.Text);
            product.CategoryID = Convert.ToInt32(DropDownAddCategory.SelectedValue);
            product.Description = AddProductDescription.Text;
            product.ImagePath = ProductImage.FileName;

            return product;
        }

        // Get categories
        public IQueryable GetCategories()
        {
            var _db = new hlv1.Models.ProductContext();
            IQueryable query = _db.Categories;
            return query;
        }

    }
}