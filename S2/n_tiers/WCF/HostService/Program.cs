using System;
using System.ServiceModel;
using Share;
using ServiceRecettes;
namespace HostService
{

    public class Program
    {
        public static void Main(string[] args)
        {
            Uri baseAddress = new Uri("http://localhost:8888/ServiceRecettes");

            Service1 service = new Service1();
            var host = new ServiceHost(service);

            host.Open();

            host.Close();
            /*
            using (ServiceHost host = new ServiceHost(typeof(Recette), baseAddress))
            {
                host.Open();

                Console.WriteLine("The service is ready at {0}", baseAddress);
                Console.WriteLine("Press <Enter> to stop the service.");
                Console.ReadLine();

                // Close the ServiceHost.
                host.Close();
            }
            */
        }
    }
}
