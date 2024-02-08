using System;
using System.Collections.Generic;
using System.Windows.Forms;
using System.IO;
using System.Xml;
using ServerTester.Util;
using RestSharp;
using System.Net;
using System.Text.RegularExpressions;

namespace ServerTester.TestItem
{
    internal class TestItemService
    {
        public Dictionary<string, List<TestItemDto>> testItemDtosDic { get; } = new Dictionary<string, List<TestItemDto>>();

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
                    testItemDtosDic[testItemDto.description.group].Add(testItemDto);

                    this.totalCount += 1;
                }

            }
            catch(Exception e)
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

                testItemDto.tests.Add(testItemTestDto);
            }


            return testItemDto; 
        }


        public TestItemTestResultDto executeTestItemTest(TestItemTestDto testItemTestDto)
        {
            TestItemTestResultDto testItemTestResultDto = new TestItemTestResultDto();

            try
            {
                // Create Http request by using testItemTestResultDto
                RestResponse response = HttpUtil.request(
                    HttpUtil.getMethodByString(testItemTestDto.method), 
                    testItemTestDto.baseUrl, 
                    testItemTestDto.resourceUrl
                );
                testItemTestResultDto.statusCode = response.StatusCode;
                testItemTestResultDto.requestLog = makeRequestLog(response.Request, testItemTestDto);
                testItemTestResultDto.responseLog = makeResponseLog(response);


                foreach (TestItemTestCheckDto check in testItemTestDto.checks)
                {
                    if(check.type == "status") {
                        // Check if the response has valid status code
                        if (!(Regex.IsMatch(((int)(response.StatusCode)).ToString(), check.value)))
                        {
                            testItemTestResultDto.isPass = false;
                            testItemTestResultDto.resultLog = string.Format(
                                "유효하지 않은 Status Code. (기대값: {0}, 결과값: {1})",
                                check.value, (int)(response.StatusCode)
                            );
                            return testItemTestResultDto;
                        }
                    }
                    else if(check.type == "data")
                    {
                        // Check if the reponse content contains the value of check
                        if (!(response.Content.Contains(check.value)))
                        {
                            testItemTestResultDto.isPass = false;
                            testItemTestResultDto.resultLog = string.Format(
                                 "유효하지 않은 데이터. (포함되기를 기대한 값: {0})",
                                 check.value
                            );
                            return testItemTestResultDto;
                        }
                    }
                }

                testItemTestResultDto.isPass = true;
                testItemTestResultDto.resultLog = "테스트 항목 통과";

                return testItemTestResultDto;
            }
            catch(Exception e)
            {
                testItemTestResultDto.isPass = false;
                testItemTestResultDto.resultLog = e.Message;
                return testItemTestResultDto;
            }
        }

        public string makeRequestLog(RestRequest request, TestItemTestDto testItemTestDto)
        {
            List<string> reqeustLogs = new List<string> {
                string.Format("{0} {1}/{2}", request.Method, testItemTestDto.baseUrl, request.Resource),
                ""
            };

            foreach (Parameter parameter in request.Parameters)
                reqeustLogs.Add(string.Format("{0}: {1}", parameter.Name, parameter.Value));
            
            return string.Join(Environment.NewLine, reqeustLogs); ;
        }

        public string makeResponseLog(RestResponse response)
        {
            List<string> responseLogs = new List<string> {
                string.Format("{0}({1}) {2}", response.StatusCode, (int)(response.StatusCode), response.StatusDescription),
                "",
                response.Content
            };

            return string.Join(Environment.NewLine, responseLogs);
        }
    }


    class TestItemDto
    {
        public TestItemDescriptionDto description { get; set; } = new TestItemDescriptionDto();
        public List<TestItemTestDto> tests { get; set; } = new List<TestItemTestDto>();
        public string filePath { get; set; } = "";
        public bool isPassed { get; set; } = false;
    }

    class TestItemDescriptionDto
    {
        public string group { get; set; } = "";
        public string title { get; set; } = "";
        public string help { get; set; } = "";
    }

    class TestItemTestDto
    {
        public string title { get; set; } = "";
        public string help { get; set; } = "";

        public string method { get; set; } = "";
        public string baseUrl { get; set; } = "";
        public string resourceUrl { get; set; } = "";


        public List<TestItemTestCheckDto> checks { get; set; } = new List<TestItemTestCheckDto>();
        public TestItemTestResultDto result { get; set; } = new TestItemTestResultDto();
    }

    class TestItemTestCheckDto
    {
        public string type { get; set; } = "";
        public string value { get; set; } = "";
    }

    class TestItemTestResultDto
    {
        public string resultLog { get; set; } = "";
        public string requestLog { get; set; } = "";
        public string responseLog { get; set; } = "";
        public HttpStatusCode statusCode { get; set; } = HttpStatusCode.OK;
        public bool isPass { get; set; } = false;
    }
}
