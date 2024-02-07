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

namespace ServerTester
{
    public partial class MainForm : Form
    {
        public MainForm()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            // HTTP Get request to "http://localhost:8082/sanityCheck"
            string response = HttpUtil.Get("http://localhost:8082", "sanityCheck");
            MessageBox.Show(response);
        }
    }
}
