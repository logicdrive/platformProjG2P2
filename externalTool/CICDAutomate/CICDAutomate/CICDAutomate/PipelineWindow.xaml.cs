using CICDAutomate.Component;
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

namespace CICDAutomate
{
    /// <summary>
    /// PipelineWindow.xaml에 대한 상호 작용 논리
    /// </summary>
    public partial class PipelineWindow : Window
    {
        public PipelineWindow()
        {
            InitializeComponent();

            JobSelectStackPanel.Children.Add(new PipelineJobSelectCardControl(
                "/Resource/github.png", "Github", "트리거", "Github 관련 파이프라인 트리거 생성"
            ));
            JobSelectStackPanel.Children.Add(new PipelineJobSelectCardControl(
                "/Resource/maven.png", "Maven", "빌드", "Maven 빌드 작업 생성"
            ));
            JobSelectStackPanel.Children.Add(new PipelineJobSelectCardControl(
                "/Resource/docker.png", "Docker", "빌드", "Docker 빌드 작업 생성"
            ));
            JobSelectStackPanel.Children.Add(new PipelineJobSelectCardControl(
                "/Resource/kubernetes.png", "Kubernetes", "배포", "Kubernetes 배포 작업 생성"
            ));
        }
    }
}
