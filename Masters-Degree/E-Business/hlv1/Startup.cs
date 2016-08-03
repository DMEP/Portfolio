using Microsoft.Owin;
using Owin;

[assembly: OwinStartupAttribute(typeof(hlv1.Startup))]
namespace hlv1
{
    public partial class Startup {
        public void Configuration(IAppBuilder app) {
            ConfigureAuth(app);
        }
    }
}
