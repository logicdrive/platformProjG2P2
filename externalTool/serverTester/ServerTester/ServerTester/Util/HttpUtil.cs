using System.Collections.Generic;
using RestSharp;

namespace ServerTester.Util
{
    // Make a HTTP reuqest to the server
    internal class HttpUtil
    {
        // Make request request by using json
        public static RestResponse request(Method method, string baseUrl, string resourceUrl, string jsonString="", Dictionary<string, string> headers=null)
        {
            RestRequest request = new RestRequest(resourceUrl, method);
            if (jsonString != "")
                request.AddJsonBody(jsonString);
            if (headers != null)
                foreach (var header in headers)
                    request.AddHeader(header.Key, header.Value);

            return (new RestClient(baseUrl)).Execute(request);
        }

        public static Method getMethodByString(string method)
        {
            switch (method.ToUpper())
            {
                case "GET":
                    return Method.Get;
                case "POST":
                    return Method.Post;
                case "PUT":
                    return Method.Put;
                case "DELETE":
                    return Method.Delete;
                default:
                    return Method.Get;
            }
        }
    }
}
