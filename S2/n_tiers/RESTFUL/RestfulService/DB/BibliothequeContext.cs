using Microsoft.AspNet.Identity.EntityFramework;
using RestfulService.Models;
using System.Data.Entity;

namespace RestfulService.DB
{
    public class BibliothequeContext : IdentityDbContext
    {
        public DbSet<Livre> Livres { get; set; }
        public DbSet<Inscrit> Inscrits { get; set; }
        public DbSet<Bibliothecaire> Bibliothecaires { get; set; }
        public DbSet<Commentaire> Commentaires { get; set; }
    }
}