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
    public partial class PipelineJobSelectCardControl : UserControl
    {
        public PipelineJobSelectCardControl(string selectImagePath, string title, string type, string description)
        {
            InitializeComponent();

            SelectImage.Source = new BitmapImage(new Uri(selectImagePath, UriKind.Relative));
            SelectTitleLabel.Content = title;
            TypeLabel.Content = string.Format("유형: {0}", type);
            DescriptionLabel.Content = description;
        }
    }
}
