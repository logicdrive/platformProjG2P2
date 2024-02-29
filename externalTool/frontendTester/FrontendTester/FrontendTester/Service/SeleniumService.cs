using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Policy;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Windows.Forms.VisualStyles;

namespace FrontendTester.Service
{
    internal class SeleniumService
    {
        public ChromeDriver driver = new ChromeDriver();
        public TextBox logTextBox;
        public SeleniumService(TextBox logTextBox)
        {
            this.logTextBox = logTextBox;
        }

        public void exit()
        {
            this.printLog(String.Format("[*] 테스트 브라우저를 종료합니다..."));
            driver.Quit();
        }

        public void printLog(string log)
        {
            logTextBox.Text += (log + Environment.NewLine);
        }

        public void clearLog()
        {
            logTextBox.Text = "";
        }


        public void goToUrl(string url, double waitSecond=30)
        {
            this.printLog(String.Format("[*] '{0}' URL로 이동 시도중...", url));
            driver.Navigate().GoToUrl(url);

            this.checkUrl(url, waitSecond);
        }

        public void checkUrl(string url, double waitSecond = 30)
        {
            this.printLog(String.Format("[*] '{0}' URL로 이동완료를 기다리는중...", url));
            (new WebDriverWait(driver, TimeSpan.FromSeconds(waitSecond))).Until(x =>
            {
                try
                {
                    return (driver.Url == url);
                } catch
                {
                    return false;
                }
            });

            this.printLog(String.Format("[*] '{0}' URL로 이동완료됨!", url));
        }


        public void clickButton(string xpath, double waitSecond = 30)
        {
            this.printLog(String.Format("[*] '{0}' XPath에 해당하는 엘리먼트 클릭을 시도중...", xpath));
            this.checkElement(xpath, waitSecond).Click();
            this.printLog(String.Format("[*] '{0}' XPath에 해당하는 엘리먼트 클릭함!", xpath));
        }

        public IWebElement checkElement(string xpath, double waitSecond = 30)
        {
            this.printLog(String.Format("[*] '{0}' XPath에 해당하는 엘리먼트 발견을 기다리는중...", xpath));
            (new WebDriverWait(driver, TimeSpan.FromSeconds(waitSecond))).Until(x => {
                try {
                    return (driver.FindElement(By.XPath(xpath)) != null);
                } catch {
                    return false;
                }
            });

            this.printLog(String.Format("[*] '{0}' XPath에 해당하는 엘리먼트를 발견함!", xpath));
            return driver.FindElement(By.XPath(xpath));
        } 
    }
}
