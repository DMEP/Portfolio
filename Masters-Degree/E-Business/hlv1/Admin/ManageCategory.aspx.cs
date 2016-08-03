using hlv1.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace hlv1.Admin
{
    //This class will handle the adding and editing of categories
    public partial class ManageCategory : System.Web.UI.Page
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
        protected void EditCategoryButton_Click(object sender, EventArgs e)
        {
            ProductTypeModel productTypeModel = new ProductTypeModel();
            Category category = CreateCategory();

            //Check if the url contains an id parameter
            if (!String.IsNullOrWhiteSpace(Request.QueryString["id"]))
            {
                //ID exists -> Update existing row
                int id = Convert.ToInt32(Request.QueryString["id"]);
                LabelEditStatus.Text = productTypeModel.UpdateCategory(id, category);
            }
            else
            {
                //ID does not exist -> Create a new row
                LabelEditStatus.Text = productTypeModel.InsertCategory(category);
            }
        }

        // Fill the textboxes with relevant data from database
        private void FillPage(int id)
        {
            //Get selected category from DB
            ProductTypeModel productTypeModel = new ProductTypeModel();
            Category category = productTypeModel.GetCategory(id);

            //Fill Textboxes
            AddCategoryName.Text = category.CategoryName;
            AddCategoryDescription.Text = category.Description;
                        
        }

        // Create new category
        private Category CreateCategory()
        {
            Category category = new Category();

            category.CategoryName = AddCategoryName.Text;
            category.Description = AddCategoryDescription.Text;
            category.CatImagePath = CategoryImage.FileName;
            
            return category;
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
