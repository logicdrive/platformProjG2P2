using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using RestSharp;

namespace ServerTester.Util
{
    // Make a HTTP reuqest to the server
    internal class HttpUtil
    {
        public static string Get(string baseUrl, string resourceUrl)
        {
            RestClient client = new RestClient(baseUrl);
            RestRequest request = new RestRequest(resourceUrl, Method.Get);
            RestResponse response = client.Execute(request);
            return response.Content;
        }
    }
}
