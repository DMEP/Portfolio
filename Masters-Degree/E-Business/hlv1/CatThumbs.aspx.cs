using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using hlv1.Models;
using hlv1.Logic;


namespace hlv1
{
    public partial class CatThumbs : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }
        public IQueryable<Category> GetCategories()
        {
            var _db = new hlv1.Models.ProductContext();
            IQueryable<Category> query = _db.Categories;
            return query;
        }
    }
}