namespace ServerTester
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
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(MainForm));
            this.TestExecuteButton = new System.Windows.Forms.Button();
            this.TestGroupListBox = new System.Windows.Forms.ListBox();
            this.label1 = new System.Windows.Forms.Label();
            this.TestListBox = new System.Windows.Forms.ListBox();
            this.label3 = new System.Windows.Forms.Label();
            this.RequestHistoryListBox = new System.Windows.Forms.ListBox();
            this.label4 = new System.Windows.Forms.Label();
            this.contextMenuStrip1 = new System.Windows.Forms.ContextMenuStrip(this.components);
            this.RequestLogTextBox = new System.Windows.Forms.TextBox();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.groupBox2 = new System.Windows.Forms.GroupBox();
            this.ResponseLogTextBox = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.TestButton = new System.Windows.Forms.Button();
            this.groupBox3 = new System.Windows.Forms.GroupBox();
            this.ResultLogTextBox = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.HelpTextBox = new System.Windows.Forms.TextBox();
            this.RequestProgressBar = new System.Windows.Forms.ProgressBar();
            this.groupBox1.SuspendLayout();
            this.groupBox2.SuspendLayout();
            this.groupBox3.SuspendLayout();
            this.SuspendLayout();
            // 
            // TestExecuteButton
            // 
            this.TestExecuteButton.Location = new System.Drawing.Point(37, 26);
            this.TestExecuteButton.Name = "TestExecuteButton";
            this.TestExecuteButton.Size = new System.Drawing.Size(219, 60);
            this.TestExecuteButton.TabIndex = 0;
            this.TestExecuteButton.Text = "실행";
            this.TestExecuteButton.UseVisualStyleBackColor = true;
            this.TestExecuteButton.Click += new System.EventHandler(this.TestExecuteButton_Click);
            // 
            // TestGroupListBox
            // 
            this.TestGroupListBox.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.TestGroupListBox.FormattingEnabled = true;
            this.TestGroupListBox.HorizontalScrollbar = true;
            this.TestGroupListBox.ItemHeight = 32;
            this.TestGroupListBox.Location = new System.Drawing.Point(37, 141);
            this.TestGroupListBox.Name = "TestGroupListBox";
            this.TestGroupListBox.Size = new System.Drawing.Size(219, 996);
            this.TestGroupListBox.TabIndex = 1;
            this.TestGroupListBox.SelectedIndexChanged += new System.EventHandler(this.TestGroupListBox_SelectedIndexChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label1.Location = new System.Drawing.Point(80, 106);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(142, 32);
            this.label1.TabIndex = 2;
            this.label1.Text = "테스트 그룹";
            // 
            // TestListBox
            // 
            this.TestListBox.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.TestListBox.FormattingEnabled = true;
            this.TestListBox.HorizontalScrollbar = true;
            this.TestListBox.ItemHeight = 32;
            this.TestListBox.Location = new System.Drawing.Point(268, 141);
            this.TestListBox.Name = "TestListBox";
            this.TestListBox.Size = new System.Drawing.Size(438, 996);
            this.TestListBox.TabIndex = 3;
            this.TestListBox.SelectedIndexChanged += new System.EventHandler(this.TestListBox_SelectedIndexChanged);
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label3.Location = new System.Drawing.Point(757, 654);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(38, 32);
            this.label3.TabIndex = 6;
            this.label3.Text = "▶";
            // 
            // RequestHistoryListBox
            // 
            this.RequestHistoryListBox.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.RequestHistoryListBox.FormattingEnabled = true;
            this.RequestHistoryListBox.HorizontalScrollbar = true;
            this.RequestHistoryListBox.ItemHeight = 32;
            this.RequestHistoryListBox.Location = new System.Drawing.Point(850, 141);
            this.RequestHistoryListBox.Name = "RequestHistoryListBox";
            this.RequestHistoryListBox.Size = new System.Drawing.Size(355, 996);
            this.RequestHistoryListBox.TabIndex = 7;
            this.RequestHistoryListBox.SelectedIndexChanged += new System.EventHandler(this.RequestHistoryListBox_SelectedIndexChanged);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label4.Location = new System.Drawing.Point(976, 106);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(118, 32);
            this.label4.TabIndex = 8;
            this.label4.Text = "요청 내역";
            // 
            // contextMenuStrip1
            // 
            this.contextMenuStrip1.ImageScalingSize = new System.Drawing.Size(32, 32);
            this.contextMenuStrip1.Name = "contextMenuStrip1";
            this.contextMenuStrip1.Size = new System.Drawing.Size(61, 4);
            // 
            // RequestLogTextBox
            // 
            this.RequestLogTextBox.Location = new System.Drawing.Point(12, 40);
            this.RequestLogTextBox.Multiline = true;
            this.RequestLogTextBox.Name = "RequestLogTextBox";
            this.RequestLogTextBox.ReadOnly = true;
            this.RequestLogTextBox.ScrollBars = System.Windows.Forms.ScrollBars.Both;
            this.RequestLogTextBox.Size = new System.Drawing.Size(776, 357);
            this.RequestLogTextBox.TabIndex = 0;
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.RequestLogTextBox);
            this.groupBox1.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.groupBox1.Location = new System.Drawing.Point(1217, 289);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(811, 421);
            this.groupBox1.TabIndex = 12;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "요청 로그";
            // 
            // groupBox2
            // 
            this.groupBox2.Controls.Add(this.ResponseLogTextBox);
            this.groupBox2.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.groupBox2.Location = new System.Drawing.Point(1217, 716);
            this.groupBox2.Name = "groupBox2";
            this.groupBox2.Size = new System.Drawing.Size(811, 421);
            this.groupBox2.TabIndex = 13;
            this.groupBox2.TabStop = false;
            this.groupBox2.Text = "응답 로그";
            // 
            // ResponseLogTextBox
            // 
            this.ResponseLogTextBox.Location = new System.Drawing.Point(6, 38);
            this.ResponseLogTextBox.Multiline = true;
            this.ResponseLogTextBox.Name = "ResponseLogTextBox";
            this.ResponseLogTextBox.ReadOnly = true;
            this.ResponseLogTextBox.ScrollBars = System.Windows.Forms.ScrollBars.Both;
            this.ResponseLogTextBox.Size = new System.Drawing.Size(782, 359);
            this.ResponseLogTextBox.TabIndex = 0;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label2.Location = new System.Drawing.Point(414, 106);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(142, 32);
            this.label2.TabIndex = 14;
            this.label2.Text = "테스트 항목";
            // 
            // TestButton
            // 
            this.TestButton.Location = new System.Drawing.Point(1803, 26);
            this.TestButton.Name = "TestButton";
            this.TestButton.Size = new System.Drawing.Size(219, 60);
            this.TestButton.TabIndex = 15;
            this.TestButton.Text = "테스트";
            this.TestButton.UseVisualStyleBackColor = true;
            this.TestButton.Click += new System.EventHandler(this.TestButton_Click);
            // 
            // groupBox3
            // 
            this.groupBox3.Controls.Add(this.ResultLogTextBox);
            this.groupBox3.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.groupBox3.Location = new System.Drawing.Point(1217, 141);
            this.groupBox3.Name = "groupBox3";
            this.groupBox3.Size = new System.Drawing.Size(811, 142);
            this.groupBox3.TabIndex = 13;
            this.groupBox3.TabStop = false;
            this.groupBox3.Text = "검토 결과";
            // 
            // ResultLogTextBox
            // 
            this.ResultLogTextBox.Location = new System.Drawing.Point(12, 40);
            this.ResultLogTextBox.Multiline = true;
            this.ResultLogTextBox.Name = "ResultLogTextBox";
            this.ResultLogTextBox.ReadOnly = true;
            this.ResultLogTextBox.ScrollBars = System.Windows.Forms.ScrollBars.Both;
            this.ResultLogTextBox.Size = new System.Drawing.Size(776, 83);
            this.ResultLogTextBox.TabIndex = 0;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label5.Location = new System.Drawing.Point(31, 1159);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(86, 32);
            this.label5.TabIndex = 16;
            this.label5.Text = "도움말";
            // 
            // HelpTextBox
            // 
            this.HelpTextBox.Location = new System.Drawing.Point(123, 1154);
            this.HelpTextBox.Name = "HelpTextBox";
            this.HelpTextBox.ReadOnly = true;
            this.HelpTextBox.ScrollBars = System.Windows.Forms.ScrollBars.Both;
            this.HelpTextBox.Size = new System.Drawing.Size(1899, 35);
            this.HelpTextBox.TabIndex = 1;
            // 
            // RequestProgressBar
            // 
            this.RequestProgressBar.Location = new System.Drawing.Point(268, 31);
            this.RequestProgressBar.Name = "RequestProgressBar";
            this.RequestProgressBar.Size = new System.Drawing.Size(1523, 50);
            this.RequestProgressBar.TabIndex = 17;
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(13F, 24F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(2074, 1229);
            this.Controls.Add(this.RequestProgressBar);
            this.Controls.Add(this.HelpTextBox);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.groupBox3);
            this.Controls.Add(this.TestButton);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.groupBox2);
            this.Controls.Add(this.groupBox1);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.RequestHistoryListBox);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.TestListBox);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.TestGroupListBox);
            this.Controls.Add(this.TestExecuteButton);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.MaximizeBox = false;
            this.Name = "MainForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "ServerTester";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.groupBox2.ResumeLayout(false);
            this.groupBox2.PerformLayout();
            this.groupBox3.ResumeLayout(false);
            this.groupBox3.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button TestExecuteButton;
        private System.Windows.Forms.ListBox TestGroupListBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ListBox TestListBox;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.ListBox RequestHistoryListBox;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.ContextMenuStrip contextMenuStrip1;
        private System.Windows.Forms.TextBox RequestLogTextBox;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.GroupBox groupBox2;
        private System.Windows.Forms.TextBox ResponseLogTextBox;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Button TestButton;
        private System.Windows.Forms.GroupBox groupBox3;
        private System.Windows.Forms.TextBox ResultLogTextBox;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox HelpTextBox;
        private System.Windows.Forms.ProgressBar RequestProgressBar;
    }
}

