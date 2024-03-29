﻿using System;
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
    public partial class PipelineCardControl : UserControl
    {
        public PipelineCardControl(string title)
        {
            InitializeComponent();

            TitleLabel.Content = title;
        }


        private void EditButton_MouseDown(object sender, RoutedEventArgs e)
        {
            PipelineWindow pipelineWindow = new PipelineWindow();
            pipelineWindow.ShowDialog();
        }
    }
}
