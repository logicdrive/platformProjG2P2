using RestSharp;
using ServerTester.Util;
using System;
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

            HelpTextBox.Text = string.Format("{0}개의 테스트 항목이 성공적으로 로드됨 !", this.testItemService.testItemDtos.Count);
        }

        private void controlModifySampleCode()
        {
            // Set 'log1\nlog2' value to 'ResultLogTextBox' TextBox
            ResultLogTextBox.Text = "log1" + Environment.NewLine + "log2";

            // Add 'New Item 1' to 'TestGroupListBox' ListBox
            TestGroupListBox.Items.Add("New Item 1");


            // Null check for 'TestGroupListBox' ListBox
            if ((TestGroupListBox.SelectedItem == null) || (TestGroupListBox.SelectedItem.ToString().Length <= 0))
                return;

            // Check if user selected ''New Item 1' from 'TestGroupListBox' ListBox
            if (TestGroupListBox.SelectedItem.ToString() == "New Item 1")
            {
                // Set selected item to 'ResultLogTextBox' TextBox
                ResultLogTextBox.Text = TestGroupListBox.SelectedItem.ToString();
            
                // Clear 'TestListBox' ListBox and Add New Sub Item 1 ~ 3 to 'TestListBox' ListBox
                TestListBox.Items.Clear();
                TestListBox.Items.Add("New Sub Item 1");
                TestListBox.Items.Add("New Sub Item 2");
                TestListBox.Items.Add("New Sub Item 3");
            }


            // Check if user selected 0th item from 'TestGroupListBox' ListBox
            if (TestGroupListBox.SelectedIndex == 0)
            {
            }

            // Set progress to 'TestProgressBar' ProgressBar
            TestProgressBar.Value = 50;
        }

        private void xmlFileReadSampleCode()
        {
            // Search all file paths in './testItem' directory recursively
            string[] filePaths = Directory.GetFiles("./testItem", "*.*", SearchOption.AllDirectories);
            foreach (string filePath in filePaths)
            {
                // File path could't include 'copilotSample' string
                if (filePath.Contains("copilotSample"))
                    continue;


                XmlDocument doc = new XmlDocument();
                doc.Load(filePath);


                // Show testItem's description
                XmlNode descriptionNode = doc.SelectSingleNode("/root/description");
                MessageBox.Show(descriptionNode.SelectSingleNode("group").InnerText);
                MessageBox.Show(descriptionNode.SelectSingleNode("title").InnerText);
                MessageBox.Show(descriptionNode.SelectSingleNode("help").InnerText);


                // Show each test information
                XmlNodeList testNodeList = doc.SelectNodes("/root/tests/test");
                foreach (XmlNode testNode in testNodeList)
                {
                    MessageBox.Show(testNode.SelectSingleNode("title").InnerText);
                    MessageBox.Show(testNode.SelectSingleNode("help").InnerText);

                    XmlNodeList checkNodeList = testNode.SelectNodes("checks/check");
                    foreach (XmlNode checkNode in checkNodeList)
                    {
                        // Get 'type' attribute value
                        MessageBox.Show(checkNode.Attributes["type"].Value);
                        MessageBox.Show(checkNode.InnerText);
                    }
                }
            }
        }

        private void etcSample()
        {

        }
    }


    internal class TestItemService
    {
        public List<TestItemDto> testItemDtos { get; } = new List<TestItemDto>();
    }

    class TestItemDto
    {
        public TestItemDescriptionDto description { get; set; } = new TestItemDescriptionDto();
        public List<TestItemTestDto> tests { get; set; } = new List<TestItemTestDto>();
        public String filePath { get; set; } = "";
    }

    class TestItemDescriptionDto
    {
        public string group { get; set; } = "";
        public string title { get; set; } = "";
        public string help { get; set; } = "";
    }

    class TestItemTestDto
    {
        public String title { get; set; } = "";
        public String help { get; set; } = "";

        public String method { get; set; } = "";
        public String baseUrl { get; set; } = "";
        public String resourceUrl { get; set; } = "";


        public List<TestItemTestCheckDto> checks { get; set; } = new List<TestItemTestCheckDto>();
    }

    class TestItemTestCheckDto
    {
        public String type { get; set; } = "";
        public String value { get; set; } = "";
    }
}
