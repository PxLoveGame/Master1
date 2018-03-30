using Microsoft.AspNet.Identity;
using System;
using System.Linq;
using System.Threading.Tasks;
using RestfulService.Models;
using Microsoft.AspNet.Identity.EntityFramework;


namespace RestfulService.DB
{
    public class AuthRepository : IDisposable
    {
        private BibliothequeContext context;

        private BibliothequeUserManager bibliothequeUserManager;
      
        
        public AuthRepository()
        {
            context = new BibliothequeContext();
            bibliothequeUserManager = new BibliothequeUserManager();
        
        }

        public async void SetupRoles()
        {
            var role = context.Roles.SingleOrDefault(r => r.Name == "Inscrit");
            if(role == null)
            {
                context.Roles.Add(new IdentityRole { Name = "Inscrit" });
                
            }

            role = context.Roles.SingleOrDefault(r => r.Name == "Bibliothecaire");
            if (role == null)
            {
                context.Roles.Add(new IdentityRole { Name = "Bibliothecaire" });
            }

            await context.SaveChangesAsync();
        }

        public async Task<IdentityUser> FindUser(string nomInscrit, string mdpInscrit)
        {
            IdentityUser inscrit = await bibliothequeUserManager.FindAsync(nomInscrit, mdpInscrit);

            return inscrit;
        }

        // Inscrit méthodes
        public async Task<IdentityResult> RegisterInscrit(Inscrit inscrit)
        {
            IdentityUser user = new IdentityUser
            {
                UserName = inscrit.nom
            };

            
            var result = await bibliothequeUserManager.CreateAsync(user, inscrit.mdp);

            var role = context.Roles.SingleOrDefault(r => r.Name == "Inscrit");
            user.Roles.Add(new IdentityUserRole { RoleId = role.Id });

            await bibliothequeUserManager.UpdateAsync(user);

            if (result.Succeeded)
            {
                context.Inscrits.Add(inscrit);
            }

            await context.SaveChangesAsync();
            return result;
        }

        public async Task<IdentityResult> UnRegisterInscrit(Inscrit inscrit)
        {
            IdentityUser user = await FindUser(inscrit.nom, inscrit.mdp);

            var result = await bibliothequeUserManager.DeleteAsync(user);
            if (result.Succeeded)
            {
                context.Inscrits.Remove(inscrit);
            }

            await context.SaveChangesAsync();
            return result;
        }

        //Bibliothecaire méthodes
        public async Task<IdentityResult> RegisterBibliothecaire(Bibliothecaire bibliothecaire)
        {
            IdentityUser user = new IdentityUser
            {
                UserName = bibliothecaire.nom
            };

            var role = context.Roles.SingleOrDefault(r => r.Name == "Inscrit");
            user.Roles.Add(new IdentityUserRole { RoleId = role.Id });
            var result = await bibliothequeUserManager.CreateAsync(user, bibliothecaire.mdp);
            
            if (result.Succeeded)
            {
                context.Bibliothecaires.Add(bibliothecaire);
            }

            return result;
        }

        public async Task<IdentityResult> UnRegisterBibliothecaire(Bibliothecaire bibliothecaire)
        {
            IdentityUser user = await FindUser(bibliothecaire.nom, bibliothecaire.mdp);

            var result = await bibliothequeUserManager.DeleteAsync(user);
            if (result.Succeeded)
            {
                context.Bibliothecaires.Remove(bibliothecaire);
            }

            await context.SaveChangesAsync();
            return result;
        }


        public void Dispose()
        {
            context.Dispose();
            bibliothequeUserManager.Dispose();

        }
    }
}