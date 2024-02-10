using System;
using RestSharp;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using ServerTester.Util;
using System.IO;
using System.Xml;
using ServerTester.TestItem;

namespace ServerTester
{
    public partial class MainForm : Form
    {
        private TestItemService testItemService;


        public MainForm()
        {
            InitializeComponent();
            this.Size = new Size(1300, 650);
            this.initMainForm();
        }

        public void initMainForm()
        {
            this.ClearAllControlls();

            this.testItemService = new TestItemService();
            HelpTextBox.Text = string.Format("{0}개의 테스트 항목이 성공적으로 로드됨 !", this.testItemService.totalCount);

            foreach (string key in this.testItemService.testItemDtosDic.Keys)
                TestGroupListBox.Items.Add(key);
        }

        public void ClearAllControlls()
        {
            TestGroupListBox.SelectedIndex = -1;
            TestGroupListBox.Items.Clear();

            TestListBox.SelectedIndex = -1;
            TestListBox.Items.Clear();

            RequestHistoryListBox.SelectedIndex = -1;
            RequestHistoryListBox.Items.Clear();

            HelpTextBox.Text = "";
            ResultLogTextBox.Text = "";
            RequestLogTextBox.Text = "";
            ResponseLogTextBox.Text = "";

            RequestProgressBar.Value = 0;   
        }

        private void InitButton_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("정말 초기화 하시겠습니까?", "경고", MessageBoxButtons.YesNo, MessageBoxIcon.Warning) == DialogResult.No)
                return;

            this.initMainForm();
            MessageBox.Show("초기화가 완료되었습니다.", "성공", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }


        private void TestExecuteButton_Click(object sender, EventArgs e)
        {
            if ((TestGroupListBox.SelectedItem == null) || (TestGroupListBox.SelectedItem.ToString().Length <= 0))
            {
                MessageBox.Show("테스트 그룹을 선택해주세요.", "에러", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            if (TestListBox.SelectedIndex < 0)
            {
                MessageBox.Show("테스트 항목을 선택해주세요.", "에러", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }


            TestItemDto selectedTestItemDto = this.testItemService.testItemDtosDic[TestGroupListBox.SelectedItem.ToString()][TestListBox.SelectedIndex];
            RequestProgressBar.Value = 0;
            foreach(TestItemTestDto testItemTestDto in selectedTestItemDto.tests)
            {
                testItemTestDto.result = this.testItemService.executeTestItemTest(testItemTestDto);
                ResultLogTextBox.Text = testItemTestDto.result.resultLog;
                RequestLogTextBox.Text = testItemTestDto.result.requestLog;
                ResponseLogTextBox.Text = testItemTestDto.result.responseLog;


                if(!testItemTestDto.result.isPass)
                {
                    MessageBox.Show(testItemTestDto.result.resultLog, "에러", MessageBoxButtons.OK, MessageBoxIcon.Error);
                    this.TestGroupListBox_SelectedIndexChanged(null, null);
                    this.TestListBox_SelectedIndexChanged(null, null);
                    return;
                }

                RequestProgressBar.Value += 100 / selectedTestItemDto.tests.Count;
            }
            RequestProgressBar.Value = 100;


            selectedTestItemDto.isPassed = true;
            MessageBox.Show("테스트가 성공적으로 완료되었습니다.", "성공", MessageBoxButtons.OK, MessageBoxIcon.Information);
            this.TestGroupListBox_SelectedIndexChanged(null, null);
            this.TestListBox_SelectedIndexChanged(null, null);
        }

        private void EachTestExecuteButton_Click(object sender, EventArgs e)
        {
            if ((TestGroupListBox.SelectedItem == null) || (TestGroupListBox.SelectedItem.ToString().Length <= 0))
            {
                MessageBox.Show("테스트 그룹을 선택해주세요.", "에러", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            if (TestListBox.SelectedIndex < 0)
            {
                MessageBox.Show("테스트 항목을 선택해주세요.", "에러", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
            if (RequestHistoryListBox.SelectedIndex < 0)
            {
                MessageBox.Show("요청시킬 항목을 선택해주세요.", "에러", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }


            TestItemTestDto testItemTestDto = this.testItemService.testItemDtosDic[TestGroupListBox.SelectedItem.ToString()][TestListBox.SelectedIndex].tests[RequestHistoryListBox.SelectedIndex];
            testItemTestDto.result = this.testItemService.executeTestItemTest(testItemTestDto);

            if (!testItemTestDto.result.isPass)
            {
                MessageBox.Show(testItemTestDto.result.resultLog, "에러", MessageBoxButtons.OK, MessageBoxIcon.Error);
                this.TestListBox_SelectedIndexChanged(null, null);
                return;
            }


            MessageBox.Show("성공적으로 요청되었습니다.", "성공", MessageBoxButtons.OK, MessageBoxIcon.Information);
            this.TestListBox_SelectedIndexChanged(null, null);
        }


        private void TestGroupListBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if ((TestGroupListBox.SelectedItem == null) || (TestGroupListBox.SelectedItem.ToString().Length <= 0))
                return;
            if(e != null) {
                RequestHistoryListBox.Items.Clear();
                ResultLogTextBox.Text = "";
                RequestLogTextBox.Text = "";
                ResponseLogTextBox.Text = "";
            }

            int savedSelectedIndex = TestListBox.SelectedIndex;
            TestListBox.Items.Clear();
            foreach (TestItemDto testItemDto in this.testItemService.testItemDtosDic[TestGroupListBox.SelectedItem.ToString()])
            {
                if(testItemDto.isPassed) 
                    TestListBox.Items.Add("[OK] " + testItemDto.description.title);
                else
                    TestListBox.Items.Add(testItemDto.description.title);
            }

            if (e == null)
                TestListBox.SelectedIndex = savedSelectedIndex;
        }

        private void TestListBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(TestListBox.SelectedIndex < 0) return;
            if(e != null) {
                ResultLogTextBox.Text = "";
                RequestLogTextBox.Text = "";
                ResponseLogTextBox.Text = "";
            }


            TestItemDto selectedTestItemDto = this.testItemService.testItemDtosDic[TestGroupListBox.SelectedItem.ToString()][TestListBox.SelectedIndex];
            HelpTextBox.Text = string.Format("{0}({1})", selectedTestItemDto.description.help, selectedTestItemDto.filePath);

            int savedSelectedIndex = RequestHistoryListBox.SelectedIndex;
            RequestHistoryListBox.Items.Clear();
            foreach (TestItemTestDto test in selectedTestItemDto.tests)
            {
                if((test.result != null) && (test.result.responseLog.Length > 0))
                {
                    RequestHistoryListBox.Items.Add(string.Format(
                        "[{0}] {1}", test.result.statusCode.ToString(), test.title
                    ));
                }
                else
                    RequestHistoryListBox.Items.Add(test.title);
            }

            if (e == null)
                RequestHistoryListBox.SelectedIndex = savedSelectedIndex;
        }

        private void RequestHistoryListBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(RequestHistoryListBox.SelectedIndex < 0) return;

            TestItemTestDto testItemTestDto = this.testItemService.testItemDtosDic[TestGroupListBox.SelectedItem.ToString()][TestListBox.SelectedIndex].tests[RequestHistoryListBox.SelectedIndex];
            HelpTextBox.Text = testItemTestDto.help;
            ResultLogTextBox.Text = testItemTestDto.result.resultLog;
            RequestLogTextBox.Text = testItemTestDto.result.requestLog;
            ResponseLogTextBox.Text = testItemTestDto.result.responseLog;
        }
    }
}
