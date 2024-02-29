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

namespace FrontendTester
{
    public partial class MainForm : Form
    {
        SeleniumService seleniumService = new SeleniumService();

        public MainForm()
        {
            InitializeComponent();
            this.Size = new Size(1300, 650);
        }

        private void MainForm_FormClosed(object sender, FormClosedEventArgs e)
        {
            this.seleniumService.exit();
        }


        private void ExecuteTestSetButton_Click(object sender, EventArgs e)
        {

        }

        private void ResetButton_Click(object sender, EventArgs e)
        {
            seleniumService.goToUrl("http://localhost:8088/user/signIn");
            seleniumService.clickButton("//button[text()='회원가입']");
        }
    }
}
