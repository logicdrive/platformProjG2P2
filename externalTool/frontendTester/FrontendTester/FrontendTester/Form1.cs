using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

using OpenQA.Selenium.Chrome;
using OpenQA.Selenium;
using OpenQA.Selenium.Support.UI;
using FrontendTester.Service;
using ServerTester.TestItem;

namespace FrontendTester
{
    public partial class MainForm : Form
    {
        SeleniumService seleniumService;
        TestItemService testItemService;

        public MainForm()
        {
            InitializeComponent();
            this.Size = new Size(1300, 650);

            this.loadSerives();
            this.InitializeComponents();
        }

        private void loadSerives()
        {
            try
            {
                seleniumService = new SeleniumService(LogTextBox);
                testItemService = new TestItemService();
            } catch(Exception e)
            {
                MessageBox.Show(e.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                Console.WriteLine(e.Message);
            }
        }

        public void InitializeComponents()
        {
            TestSetListBox.Items.Clear();
            TestDetailSetListBox.Items.Clear();
            LogTextBox.Text = "";
            TestSetProgessBar.Value = 0;

            testItemService.testItemDtoDic.Keys.ToList().ForEach(x => TestSetListBox.Items.Add(x));
            HelpTextBox.Text = string.Format("{0} 개의 테스트 항목이 로드되었습니다.", testItemService.testItemDtoDic.Count);
        }

        private void ResetButton_Click(object sender, EventArgs e)
        {
            InitializeComponents();
            MessageBox.Show("초기화 완료", "알림", MessageBoxButtons.OK, MessageBoxIcon.Information);
        }

        private void MainForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            this.seleniumService.exit();
        }


        private void ExecuteTestSetButton_Click(object sender, EventArgs e)
        {
            if(TestSetListBox.SelectedIndex < 0)
            {
                MessageBox.Show("테스트 항목을 선택해주세요.", "알림", MessageBoxButtons.OK, MessageBoxIcon.Information);
                return;
            }

            try
            {

                TestItemDto testItemDto = testItemService.testItemDtoDic[TestSetListBox.SelectedItem.ToString()];
                testItemService.executeTestItemDto(testItemDto, seleniumService);

                MessageBox.Show("테스트 항목이 성공적으로 실행되었습니다.", "알림", MessageBoxButtons.OK, MessageBoxIcon.Information);

            } catch (Exception ex)
            {
                MessageBox.Show(ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }


        private void TestSetListBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(TestSetListBox.SelectedIndex < 0) return;


            TestItemDto testItemDto = testItemService.testItemDtoDic[TestSetListBox.SelectedItem.ToString()];
            HelpTextBox.Text = testItemDto.help;

            TestDetailSetListBox.Items.Clear();
            for(int i = 0; i < testItemDto.functions.Count; i++)
                TestDetailSetListBox.Items.Add(string.Format("{0}. {1}", i+1, testItemDto.functions[i].title));
        }

        private void TestDetailSetListBox_SelectedIndexChanged(object sender, EventArgs e)
        {
            if(TestSetListBox.SelectedIndex < 0) return;
            if(TestDetailSetListBox.SelectedIndex < 0) return;


            HelpTextBox.Text = testItemService.testItemDtoDic[TestSetListBox.SelectedItem.ToString()].functions[TestDetailSetListBox.SelectedIndex].help;
        }
    }
}
