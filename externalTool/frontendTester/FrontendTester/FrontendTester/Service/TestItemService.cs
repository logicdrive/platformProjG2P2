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
        public SortedDictionary<string, List<TestItemDto>> testItemDtosDic { get; } = new SortedDictionary<string, List<TestItemDto>>();

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

                // Sort each testItemDto list by description.title
                foreach (string key in testItemDtosDic.Keys)
                    testItemDtosDic[key].Sort((x, y) => x.description.title.CompareTo(y.description.title));

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
                    resourceUrl = testNode.SelectSingleNode("resourceUrl").InnerText,
                    data = testNode.SelectSingleNode("data").InnerText
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

                XmlNodeList headerNodeList = testNode.SelectNodes("headers/header");
                foreach (XmlNode headerNode in headerNodeList)
                {
                    testItemTestDto.headers.Add(headerNode.Attributes["key"].Value, headerNode.InnerText);
                }
                testItemTestDto.result.requestLog = this.makeRequestLog(testItemTestDto);


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
                    testItemTestDto.resourceUrl,
                    testItemTestDto.data,
                    testItemTestDto.headers
                );
                testItemTestResultDto.statusCode = response.StatusCode;
                testItemTestResultDto.requestLog = makeRequestLog(testItemTestDto);
                testItemTestResultDto.responseLog = makeResponseLog(response);

                List<string> resultLogs = new List<string>() { "테스트 항목 통과"  };
                foreach (TestItemTestCheckDto check in testItemTestDto.checks)
                {
                    if(check.type == "status") {
                        // Check if the response has valid status code
                        if (!(Regex.IsMatch(((int)(response.StatusCode)).ToString(), check.value)))
                        {
                            testItemTestResultDto.isPass = false;
                            testItemTestResultDto.resultLog = string.Format(
                                "유효하지 않은 Status Code. (기대값 정규식: {0}, 결과값: {1})",
                                check.value, (int)(response.StatusCode)
                            );
                            return testItemTestResultDto;
                        }
                        resultLogs.Add(string.Format("매칭된 상태코드({0}): {1}", check.value,
                            Regex.Match(((int)(response.StatusCode)).ToString(), check.value).Value));
                    }
                    else if(check.type == "data")
                    {
                        // Check if the reponse content contains the value of check
                        if (!(Regex.IsMatch(response.Content, check.value)))
                        {
                            testItemTestResultDto.isPass = false;
                            testItemTestResultDto.resultLog = string.Format(
                                 "유효하지 않은 데이터. (기대값 정규식: {0})",
                                 check.value
                            );
                            return testItemTestResultDto;
                        }
                        resultLogs.Add(string.Format("매칭된 데이터({0}): {1}", check.value,
                            Regex.Match(response.Content, check.value).Value));
                    }
                }

                testItemTestResultDto.isPass = true;
                testItemTestResultDto.resultLog = string.Join(Environment.NewLine, resultLogs);

                return testItemTestResultDto;
            }
            catch(Exception e)
            {
                testItemTestResultDto.isPass = false;
                testItemTestResultDto.resultLog = e.Message;
                return testItemTestResultDto;
            }
        }

        public string makeRequestLog(TestItemTestDto testItemTestDto)
        {
            string headerString = "";
            foreach (var header in testItemTestDto.headers)
                headerString += string.Format("{0}: {1}{2}", header.Key, header.Value, Environment.NewLine);

            List<string> reqeustLogs = new List<string> {
                string.Format("{0} {1}/{2}", testItemTestDto.method, testItemTestDto.baseUrl, testItemTestDto.resourceUrl),
                "",
                headerString,
                "",
                testItemTestDto.data
            };
            
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


        public Dictionary<string, string> headers { get; set; } = new Dictionary<string, string>();
        public string data { get; set; } = "";


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
