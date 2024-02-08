using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using System.IO;
using System.Xml;

namespace ServerTester.TestItem
{
    internal class TestItemService
    {
        public Dictionary<String, List<TestItemDto>> testItemDtosDic { get; } = new Dictionary<string, List<TestItemDto>>();

        public int totalCount = 0;


        public TestItemService()
        {
            try
            {

                string[] filePaths = Directory.GetFiles("./testItem", "*.*", SearchOption.AllDirectories);
                foreach (string filePath in filePaths)
                {
                    if (filePath.Contains("copilotSample"))
                        continue;

                    TestItemDto testItemDto = this.loadXmlFile(filePath);
                    if (!testItemDtosDic.ContainsKey(testItemDto.description.group))
                        testItemDtosDic.Add(testItemDto.description.group, new List<TestItemDto> {});
                    this.totalCount += 1;
                }

            }
            catch(Exception e)
            {
                MessageBox.Show(e.Message);
                System.Console.WriteLine(e.Message);
            }
        }

        private TestItemDto loadXmlFile(String filePath)
        {
            TestItemDto testItemDto = new TestItemDto();


            XmlDocument doc = new XmlDocument();
            doc.Load(filePath);

            XmlNode descriptionNode = doc.SelectSingleNode("/root/description");
            testItemDto.description.group = descriptionNode.SelectSingleNode("group").InnerText;
            testItemDto.description.title = descriptionNode.SelectSingleNode("title").InnerText;
            testItemDto.description.help = descriptionNode.SelectSingleNode("help").InnerText;

            XmlNodeList testNodeList = doc.SelectNodes("/root/tests/test");
            foreach (XmlNode testNode in testNodeList)
            {
                TestItemTestDto testItemTestDto = new TestItemTestDto
                {
                    title = testNode.SelectSingleNode("title").InnerText,
                    help = testNode.SelectSingleNode("help").InnerText,
                    method = testNode.SelectSingleNode("method").InnerText,
                    baseUrl = testNode.SelectSingleNode("baseUrl").InnerText,
                    resourceUrl = testNode.SelectSingleNode("resourceUrl").InnerText
                };

                XmlNodeList checkNodeList = testNode.SelectNodes("checks/check");
                foreach (XmlNode checkNode in checkNodeList)
                {
                    testItemTestDto.checks.Add(new TestItemTestCheckDto
                    {
                        type = checkNode.Attributes["type"].Value,
                        value = checkNode.InnerText
                    });
                }

                testItemDto.tests.Append(testItemTestDto);
            }


            return testItemDto; 
        }
    }


    class TestItemDto
    {
        public TestItemDescriptionDto description { get; set; } = new TestItemDescriptionDto();
        public List<TestItemTestDto> tests { get; set; } = new List<TestItemTestDto>();
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
