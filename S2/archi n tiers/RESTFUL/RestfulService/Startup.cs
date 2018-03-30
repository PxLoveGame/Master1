using Microsoft.Owin;
using Owin;
using RestfulService.DB;
using System.Data.Entity;
using System.Web.Http;

[assembly: OwinStartup(typeof(RestfulService.Startup))]
namespace RestfulService
{
    public partial class Startup
    {
        public void Configuration(IAppBuilder app)
        {
            HttpConfiguration config = new HttpConfiguration();
            ConfigureOAuth(app);

            WebApiConfig.Register(config);
           
            Database.SetInitializer(new Initializer());
            app.UseCors(Microsoft.Owin.Cors.CorsOptions.AllowAll);
            app.UseWebApi(config);
        }
    }
}