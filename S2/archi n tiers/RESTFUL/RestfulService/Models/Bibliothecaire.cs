using System.ComponentModel.DataAnnotations;

namespace RestfulService.Models
{
    public class Bibliothecaire
    {
        public int Id { get; set; }

        [Required]
        [Display(Name = "Nom")]
        public string nom { get; set; }

        [Required]
        [DataType(DataType.Password)]
        [StringLength(16, ErrorMessage = "The {0} must be at least {2} characters long.", MinimumLength = 6)]
        [Display(Name = "Mot de passe")]
        public string mdp { get; set; }

        [Required]
        [DataType(DataType.Password)]
        [StringLength(16, ErrorMessage = "The {0} must be at least {2} characters long.", MinimumLength = 6)]
        [Display(Name = "Confirmation du mot de passe")]
        public string confirmation { get; set; }
       

        public Bibliothecaire()
        {
        }

        public Bibliothecaire(string nom, string mdp)
        {
           
            this.mdp = mdp;
            this.nom = nom;
        }

    }
}