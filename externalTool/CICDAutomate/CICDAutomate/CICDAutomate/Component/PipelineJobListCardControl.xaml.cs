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
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace CICDAutomate.Component
{
    public partial class PipelineJobListCardControl : UserControl
    {
        public PipelineJobListCardControl(string selectImagePath, string title, string description)
        {
            InitializeComponent();

            SelectImage.Source = new BitmapImage(new Uri(selectImagePath, UriKind.Relative));
            SelectTitleLabel.Content = title;
            DescriptionLabel.Content = description;
        }
    }
}
