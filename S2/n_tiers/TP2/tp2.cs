using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Tp2.GlobalsWeather;
namespace Tp2
{
    class Program
    {

        private static GlobalWeatherSoapClient client;

        static void Main(string[] args)
        {
           client = new GlobalWeatherSoapClient();
           Console.WriteLine(client.GetCitiesByCountry("France"));
           Console.WriteLine(client.GetWeather("Montpellier","France"));

            Console.ReadLine();
        }
    }
}
