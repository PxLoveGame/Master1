using Microsoft.AspNet.Identity.EntityFramework;

namespace RestfulService.DB
{
    public class BibliothequeUserStore : UserStore<IdentityUser>
    {
        public BibliothequeUserStore() : base(new BibliothequeContext())
        {
        }
    }
}