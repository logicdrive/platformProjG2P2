using System;
using System.Collections.Generic;
using System.Windows.Forms;
using System.IO;
using System.Xml;
using System.Text.RegularExpressions;
using OpenQA.Selenium.DevTools.V120.Debugger;

namespace ServerTester.TestItem
{
    internal class TestItemService
    {
        public SortedDictionary<string, TestItemDto> testItemDtoDic { get; } = new SortedDictionary<string, TestItemDto>();


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
                    testItemDtoDic.Add(testItemDto.title, testItemDto);
                }
            }
            catch (Exception e)
            {
                MessageBox.Show(e.Message);
                System.Console.WriteLine(e.Message);
            }
        }

        private TestItemDto loadXmlFile(string filePath)
        {
            TestItemDto testItemDto = new TestItemDto();
            testItemDto.filePath = filePath;


            XmlDocument doc = new XmlDocument();
            doc.Load(filePath);


            XmlNode descriptionNode = doc.SelectSingleNode("/root/description");
            testItemDto.title = descriptionNode.SelectSingleNode("title").InnerText;
            testItemDto.help = descriptionNode.SelectSingleNode("help").InnerText;


            XmlNodeList functionNodeList = doc.SelectNodes("/root/functions/function");
            foreach (XmlNode functionNode in functionNodeList)
            {
                TestItemFunctionDto testItemTestDto = new TestItemFunctionDto();
                testItemTestDto.method = functionNode.Attributes["type"].Value;

                switch (functionNode.Attributes["type"].Value)
                {
                    case "goToUrl":
                        testItemTestDto.title = "URL 이동";
                        testItemTestDto.help = string.Format("'{0}' URL로 이동합니다.", functionNode.InnerText);
                        testItemTestDto.values.Add(functionNode.InnerText);
                        break;

                    case "checkUrl":
                        testItemTestDto.title = "URL 체크";
                        testItemTestDto.help = string.Format("'{0}' URL로 이동되었는지 확인합니다.", functionNode.InnerText);
                        testItemTestDto.values.Add(functionNode.InnerText);
                        break;


                    case "clickElement":
                        testItemTestDto.title = "엘리먼트 클릭";
                        testItemTestDto.help = string.Format("'{0}' XPath 경로를 가진 엘리먼트를 클릭합니다.", functionNode.InnerText);
                        testItemTestDto.values.Add(functionNode.InnerText);
                        break;

                    case "sendKeysToElement":
                        testItemTestDto.title = "엘리먼트에 값 입력";
                        testItemTestDto.help = string.Format("'{0}' XPath 경로를 가진 엘리먼트에 '{1}' 값을 입력합니다.",
                            functionNode.Attributes["target"].Value, functionNode.InnerText);
                        testItemTestDto.values.Add(functionNode.Attributes["target"].Value);
                        testItemTestDto.values.Add(functionNode.InnerText);
                        break;

                    case "checkElementExist":
                        testItemTestDto.title = "엘리먼트 존재여부 체크";
                        testItemTestDto.help = string.Format("'{0}' XPath 경로를 가진 엘리먼트가 존재하는지 확인합니다.", functionNode.InnerText);
                        testItemTestDto.values.Add(functionNode.InnerText);
                        break;
                }

                testItemDto.functions.Add(testItemTestDto);
            }


            return testItemDto;
        }
    }


    class TestItemDto
    {
        public string title { get; set; } = "";
        public string help { get; set; } = "";
        public string filePath { get; set; } = "";
        public bool isPassed { get; set; } = false;

        public List<TestItemFunctionDto> functions { get; set; } = new List<TestItemFunctionDto>();
    }

    class TestItemFunctionDto
    {
        public string title { get; set; } = "";
        public string help { get; set; } = "";
        public string method { get; set; } = "";
        public bool isPassed { get; set; } = false;

        public List<string> values { get; set; } = new List<string>();
    }
}