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
            this.Size = new Size(1125, 650);
            
            this.testItemService = new TestItemService();
            HelpTextBox.Text = string.Format("{0}개의 테스트 항목이 성공적으로 로드됨 !", this.testItemService.totalCount);

            foreach (string key in this.testItemService.testItemDtosDic.Keys)
                TestGroupListBox.Items.Add(key);
        }


        private void TestExecuteButton_Click(object sender, EventArgs e)
        {

        }

        private void TestButton_Click(object sender, EventArgs e)
        {

        }


        private void TestGroupListBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if ((TestGroupListBox.SelectedItem == null) || (TestGroupListBox.SelectedItem.ToString().Length <= 0))
                return;

            TestListBox.Items.Clear();
            foreach (TestItemDto testItemDto in this.testItemService.testItemDtosDic[TestGroupListBox.SelectedItem.ToString()])
                TestListBox.Items.Add(testItemDto.description.title);
        }

        private void TestListBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(TestListBox.SelectedIndex < 0) return;

            TestItemDto selectedTestItemDto = this.testItemService.testItemDtosDic[TestGroupListBox.SelectedItem.ToString()][TestListBox.SelectedIndex];
            HelpTextBox.Text = string.Format("{0}({1})", selectedTestItemDto.description.help, selectedTestItemDto.filePath);

            RequestHistoryListBox.Items.Clear();
            foreach (TestItemTestDto test in selectedTestItemDto.tests)
                RequestHistoryListBox.Items.Add(test.title);
        }

        private void RequestHistoryListBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(RequestHistoryListBox.SelectedIndex < 0) return;

            TestItemTestDto testItemTestDto = this.testItemService.testItemDtosDic[TestGroupListBox.SelectedItem.ToString()][TestListBox.SelectedIndex].tests[RequestHistoryListBox.SelectedIndex];
            HelpTextBox.Text = testItemTestDto.help;
        }
    }
}
