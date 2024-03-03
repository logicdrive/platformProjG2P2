using CICDAutomate.Component;
using CICDAutomate.Dialog;
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

            JobListStackPanel.Children.Add(new PipelineJobListCardControl(
                "/Resource/github.png", "Github", "Github 관련 파이프라인 트리거 생성", new GithubJobEditDialog()
            ));
            JobListStackPanel.Children.Add(new PipelineJobListCardControl(
                "/Resource/maven.png", "Maven", "Maven 빌드 작업 생성", new MavenJobEditDialog()
            ));
            JobListStackPanel.Children.Add(new PipelineJobListCardControl(
                "/Resource/docker.png", "Docker", "Docker 빌드 작업 생성", new DockerJobEditDialog()
            ));
            JobListStackPanel.Children.Add(new PipelineJobListCardControl(
                "/Resource/kubernetes.png", "Kubernetes", "Kubernetes 배포 작업 생성", new KubernatesJobEditDialog()
            ));
        }
    }
}
