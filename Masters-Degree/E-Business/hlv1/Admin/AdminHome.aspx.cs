using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace hlv1.Admin
{
    // This class is for the Admin homepage which will show the contents of the database
    public partial class AdminHome : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {

        }

        // Allow editing of products
        protected void grdProducts_RowEditing(object sender, GridViewEditEventArgs e)
        {
            //Get selected row
            GridViewRow row = grdProducts.Rows[e.NewEditIndex];

            //Get Id of selected product
            int rowId = Convert.ToInt32(row.Cells[1].Text);

            //Redirect user to ManageProducts along with the selected rowId
            Response.Redirect("~/Admin/ManageProduct.aspx?id=" + rowId);
        }

        // Allow editing of categories
        protected void GridView2_RowEditing(object sender, GridViewEditEventArgs e)
        {
            //Get selected row
            GridViewRow row = GridView2.Rows[e.NewEditIndex];

            //Get Id of selected product
            int rowId = Convert.ToInt32(row.Cells[1].Text);

            //Redirect user to ManageProducts along with the selected rowId
            Response.Redirect("~/Admin/ManageCategory.aspx?id=" + rowId);
        }
    }
}