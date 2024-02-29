using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Support.UI;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace FrontendTester.Service
{
    internal class SeleniumService
    {
        public ChromeDriver driver = new ChromeDriver();

        public void exit()
        {
            driver.Quit();
        }


        public void goToUrl(string url, double waitSecond=30)
        {
            driver.Navigate().GoToUrl(url);
            (new WebDriverWait(driver, TimeSpan.FromSeconds(waitSecond))).Until(x => {
                try {
                    return (driver.Url == url);
                } catch {
                    return false;
                }
            });
        }


        public void clickButton(string xpath, double waitSecond = 30)
        {
            this.waitElement(xpath, waitSecond).Click();
        }

        public IWebElement waitElement(string xpath, double waitSecond = 30)
        {
            (new WebDriverWait(driver, TimeSpan.FromSeconds(waitSecond))).Until(x => {
                try {
                    return (driver.FindElement(By.XPath(xpath)) != null);
                } catch {
                    return false;
                }
            });
            return driver.FindElement(By.XPath(xpath));
        }   
    }
}
