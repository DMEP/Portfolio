using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.Owin;

namespace hlv1.Account
{
    // This code will handle the logins
    public partial class ManageLogins : System.Web.UI.Page
    {

        // Success message used in other methods
        protected string SuccessMessage
        {
            get;
            private set;
        }
        // Are there external login that can be removed
        protected bool CanRemoveExternalLogins
        {
            get;
            private set;
        }

        // Does the account have a password
        private bool HasPassword(ApplicationUserManager manager)
        {
            return manager.HasPassword(User.Identity.GetUserId());
        }

        protected void Page_Load(object sender, EventArgs e)
        {
            var manager = Context.GetOwinContext().GetUserManager<ApplicationUserManager>();
            CanRemoveExternalLogins = manager.GetLogins(User.Identity.GetUserId()).Count() > 1;

            SuccessMessage = String.Empty;
            successMessage.Visible = !String.IsNullOrEmpty(SuccessMessage);
        }

        // Get login information
        public IEnumerable<UserLoginInfo> GetLogins()
        {
            var manager = Context.GetOwinContext().GetUserManager<ApplicationUserManager>();
            var accounts = manager.GetLogins(User.Identity.GetUserId());
            CanRemoveExternalLogins = accounts.Count() > 1 || HasPassword(manager);
            return accounts;
        }

        // Remove an external login
        public void RemoveLogin(string loginProvider, string providerKey)
        {
            var manager = Context.GetOwinContext().GetUserManager<ApplicationUserManager>();
            var signInManager = Context.GetOwinContext().Get<ApplicationSignInManager>();
            var result = manager.RemoveLogin(User.Identity.GetUserId(), new UserLoginInfo(loginProvider, providerKey));
            string msg = String.Empty;
            if (result.Succeeded)
            {
                var user = manager.FindById(User.Identity.GetUserId());
                signInManager.SignIn(user, isPersistent: false, rememberBrowser: false);
                msg = "?m=RemoveLoginSuccess";
            }
            Response.Redirect("~/Account/ManageLogins" + msg);
        }
    }
}