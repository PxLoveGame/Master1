using System.ComponentModel.DataAnnotations;


namespace RestfulService.Models
{
    public class Inscrit 
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

        public Inscrit() { }

        public Inscrit(string nom, string mdp)
        {
            

            this.nom = nom;
            this.mdp = mdp;

        }
    }
}