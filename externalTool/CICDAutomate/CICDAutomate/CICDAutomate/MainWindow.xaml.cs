using CICDAutomate.Component;
using CICDAutomate.Dialog;
using CICDAutomate.Service.PipelineService;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace CICDAutomate
{
    public partial class MainWindow : Window
    {
        private PipelineService pipelineService = new PipelineService();

        public MainWindow()
        {
            InitializeComponent();

            ProjectStackPanel.Children.Add(new PipelineCardControl("USER SERVICE"));
        }


        private void PipelineAddButton_Click(object sender, RoutedEventArgs e)
        {
            (new PipelineAddDialog(pipelineService)).ShowDialog();
        }
    }
}