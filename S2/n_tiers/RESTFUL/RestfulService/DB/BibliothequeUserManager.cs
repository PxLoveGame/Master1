using Microsoft.AspNet.Identity;
using Microsoft.AspNet.Identity.EntityFramework;


namespace RestfulService.DB
{
    public class BibliothequeUserManager : UserManager<IdentityUser>
    {
        public BibliothequeUserManager() : base(new BibliothequeUserStore())
        {
        }
    }
}