using RestSharp;
using ServerTester.Util;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;

namespace ServerTester
{
    internal class CopilotSample
    {
        private void httpRequestSampleCode()
        {
            // HTTP Get request to "http://localhost:8082/sanityCheck"
            RestResponse response = HttpUtil.request(Method.Get, "http://localhost:8082", "sanityCheck");
            MessageBox.Show(response.StatusCode + " / " + response.Content);

            // HTTP Get request to "http://localhost:8082/users" and check if the response contains
            response = HttpUtil.request(Method.Get, "http://localhost:8082", "users");
            MessageBox.Show(response.Content.Contains("admin@gmail.com").ToString());


            // HTTP Put request to "http://localhost:8082/users/signUp" with json body
            response = HttpUtil.request(Method.Put, "http://localhost:8082", "users/signUp", "{\"email\":\"testemail1@gmail.com\",\"password\":\"testpassword1\",\"name\":\"testname1\"}");

            // HTTP Put request to "http://localhost:8082/users/signIn" with json body
            response = HttpUtil.request(Method.Put, "http://localhost:8082", "users/signIn", "{\"email\":\"testemail1@gmail.com\",\"password\":\"testpassword1\"}");

            // Print http 'Authorization' header value
            string token = response.Headers.ToList().Find(x => x.Name == "Authorization").Value.ToString();
            MessageBox.Show(token);


            // HTTP Get request to "http://localhost:8088/api/user/sanityCheck/authenticationCheck" with Authorization header token
            response = HttpUtil.request(Method.Get, "http://localhost:8088", "api/user/sanityCheck/authenticationCheck", "", new Dictionary<string, string> { { "Authorization", token } });
            MessageBox.Show(response.StatusCode + " / " + response.Content);

            // HTTP Put request to "http://localhost:8088/api/user/users/updateName" with json body and Authorization header token
            response = HttpUtil.request(Method.Put, "http://localhost:8088", "api/user/users/updateName", "{\"name\":\"changedTestName1\"}", new Dictionary<string, string> { { "Authorization", token } });
            MessageBox.Show(response.StatusCode + " / " + response.Content);
        }
    }
}
