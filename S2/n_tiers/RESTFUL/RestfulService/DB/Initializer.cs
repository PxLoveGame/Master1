using System.Data.Entity;

namespace RestfulService.DB
{
    public class Initializer : MigrateDatabaseToLatestVersion<BibliothequeContext,Configuration>
    {
    }
}