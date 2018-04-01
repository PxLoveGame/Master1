using System.Data.Entity.Migrations;

namespace RestfulService.DB
{
    public class Configuration : DbMigrationsConfiguration<BibliothequeContext>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = true;
            AutomaticMigrationDataLossAllowed = true;
        }
    }
}