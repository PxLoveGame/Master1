using System;
using RestfulService.Identity;
using Microsoft.Owin;
using Microsoft.Owin.Security.OAuth;
using Owin;
using RestfulService.DB;

namespace RestfulService
{
    public partial class Startup
    {
        public void ConfigureOAuth(IAppBuilder app)
        {
            app.CreatePerOwinContext(() => new BibliothequeContext());
            app.CreatePerOwinContext(() => new BibliothequeUserManager());

            app.UseOAuthAuthorizationServer(new OAuthAuthorizationServerOptions
            {
                AllowInsecureHttp = true,
                TokenEndpointPath = new PathString("/oauth2/token"),
                AccessTokenExpireTimeSpan = TimeSpan.FromMinutes(60),
                Provider = new CustomOAuthProvider(),               
            });

            app.UseOAuthBearerAuthentication(new OAuthBearerAuthenticationOptions());
        }
    }
}