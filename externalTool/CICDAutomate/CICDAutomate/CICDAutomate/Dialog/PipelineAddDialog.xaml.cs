using CICDAutomate.Service.PipelineService;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace CICDAutomate.Dialog
{
    public partial class PipelineAddDialog : Window
    {
        private PipelineService pipelineService;

        public PipelineAddDialog(PipelineService pipelineService)
        {
            InitializeComponent();
            this.pipelineService = pipelineService;
        }


        private void AddButton_Click(object sender, RoutedEventArgs e)
        {
            this.pipelineService.pipelineDtoList.Add(new PipelineDto() { 
                title = PipelineTitleTextBox.Text,
                createdDate = DateTime.Now,
                updatedDate = DateTime.Now,
                dataPath = string.Format("./Pipeline/{0}", PipelineTitleTextBox.Text),
                logPath = string.Format("./Log/{0}", PipelineTitleTextBox.Text)
            });
            this.Close();
        }
    }
}
