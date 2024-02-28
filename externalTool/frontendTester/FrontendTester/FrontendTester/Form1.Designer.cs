namespace FrontendTester
{
    partial class MainForm
    {
        /// <summary>
        /// 필수 디자이너 변수입니다.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 사용 중인 모든 리소스를 정리합니다.
        /// </summary>
        /// <param name="disposing">관리되는 리소스를 삭제해야 하면 true이고, 그렇지 않으면 false입니다.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form 디자이너에서 생성한 코드

        /// <summary>
        /// 디자이너 지원에 필요한 메서드입니다. 
        /// 이 메서드의 내용을 코드 편집기로 수정하지 마세요.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.ExecuteTestSetButton = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.TestSetListBox = new System.Windows.Forms.ListBox();
            this.label2 = new System.Windows.Forms.Label();
            this.HelpTextBox = new System.Windows.Forms.TextBox();
            this.label3 = new System.Windows.Forms.Label();
            this.TestSetProgessBar = new System.Windows.Forms.ProgressBar();
            this.ResetButton = new System.Windows.Forms.Button();
            this.TestDetailSetListBox = new System.Windows.Forms.ListBox();
            this.LogTextBox = new System.Windows.Forms.TextBox();
            this.label4 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // ExecuteTestSetButton
            // 
            this.ExecuteTestSetButton.Location = new System.Drawing.Point(12, 12);
            this.ExecuteTestSetButton.Name = "ExecuteTestSetButton";
            this.ExecuteTestSetButton.Size = new System.Drawing.Size(211, 59);
            this.ExecuteTestSetButton.TabIndex = 0;
            this.ExecuteTestSetButton.Text = "테스트 항목 실행";
            this.ExecuteTestSetButton.UseVisualStyleBackColor = true;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label1.Location = new System.Drawing.Point(133, 86);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(142, 32);
            this.label1.TabIndex = 1;
            this.label1.Text = "테스트 항목";
            // 
            // TestSetListBox
            // 
            this.TestSetListBox.FormattingEnabled = true;
            this.TestSetListBox.ItemHeight = 24;
            this.TestSetListBox.Location = new System.Drawing.Point(12, 129);
            this.TestSetListBox.Name = "TestSetListBox";
            this.TestSetListBox.Size = new System.Drawing.Size(394, 1036);
            this.TestSetListBox.TabIndex = 2;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label2.Location = new System.Drawing.Point(12, 1178);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(86, 32);
            this.label2.TabIndex = 3;
            this.label2.Text = "도움말";
            // 
            // HelpTextBox
            // 
            this.HelpTextBox.Location = new System.Drawing.Point(91, 1170);
            this.HelpTextBox.Name = "HelpTextBox";
            this.HelpTextBox.ReadOnly = true;
            this.HelpTextBox.Size = new System.Drawing.Size(2276, 35);
            this.HelpTextBox.TabIndex = 4;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label3.Location = new System.Drawing.Point(609, 86);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(198, 32);
            this.label3.TabIndex = 5;
            this.label3.Text = "테스트 세부 항목";
            // 
            // TestSetProgessBar
            // 
            this.TestSetProgessBar.Location = new System.Drawing.Point(242, 12);
            this.TestSetProgessBar.Name = "TestSetProgessBar";
            this.TestSetProgessBar.Size = new System.Drawing.Size(1894, 59);
            this.TestSetProgessBar.TabIndex = 6;
            // 
            // ResetButton
            // 
            this.ResetButton.Location = new System.Drawing.Point(2156, 12);
            this.ResetButton.Name = "ResetButton";
            this.ResetButton.Size = new System.Drawing.Size(211, 59);
            this.ResetButton.TabIndex = 7;
            this.ResetButton.Text = "초기화";
            this.ResetButton.UseVisualStyleBackColor = true;
            // 
            // TestDetailSetListBox
            // 
            this.TestDetailSetListBox.FormattingEnabled = true;
            this.TestDetailSetListBox.ItemHeight = 24;
            this.TestDetailSetListBox.Location = new System.Drawing.Point(413, 129);
            this.TestDetailSetListBox.Name = "TestDetailSetListBox";
            this.TestDetailSetListBox.Size = new System.Drawing.Size(583, 1036);
            this.TestDetailSetListBox.TabIndex = 8;
            // 
            // LogTextBox
            // 
            this.LogTextBox.Location = new System.Drawing.Point(1002, 129);
            this.LogTextBox.Multiline = true;
            this.LogTextBox.Name = "LogTextBox";
            this.LogTextBox.ReadOnly = true;
            this.LogTextBox.ScrollBars = System.Windows.Forms.ScrollBars.Both;
            this.LogTextBox.Size = new System.Drawing.Size(1365, 1036);
            this.LogTextBox.TabIndex = 9;
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label4.Location = new System.Drawing.Point(1640, 86);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(142, 32);
            this.label4.TabIndex = 10;
            this.label4.Text = "테스트 로그";
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(13F, 24F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(2424, 1229);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.LogTextBox);
            this.Controls.Add(this.TestDetailSetListBox);
            this.Controls.Add(this.ResetButton);
            this.Controls.Add(this.TestSetProgessBar);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.HelpTextBox);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.TestSetListBox);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.ExecuteTestSetButton);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "MainForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "FrontendTester v1.0.0";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button ExecuteTestSetButton;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ListBox TestSetListBox;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox HelpTextBox;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.ProgressBar TestSetProgessBar;
        private System.Windows.Forms.Button ResetButton;
        private System.Windows.Forms.ListBox TestDetailSetListBox;
        private System.Windows.Forms.TextBox LogTextBox;
        private System.Windows.Forms.Label label4;
    }
}

