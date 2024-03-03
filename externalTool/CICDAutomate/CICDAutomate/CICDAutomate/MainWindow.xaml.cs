using CICDAutomate.Component;
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
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();

            // Add new ProjectCardControl to the StackPanel
            ProjectStackPanel.Children.Add(new ProjectCardControl("CICD Automate 1"));
            ProjectStackPanel.Children.Add(new ProjectCardControl("CICD Automate 2"));
            ProjectStackPanel.Children.Add(new ProjectCardControl("CICD Automate 3"));
        }
    }
}