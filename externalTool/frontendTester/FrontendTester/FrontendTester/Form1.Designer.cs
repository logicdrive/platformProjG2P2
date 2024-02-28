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
            this.ExecuteTestSetBtn = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.TestSetListBox = new System.Windows.Forms.ListBox();
            this.SuspendLayout();
            // 
            // ExecuteTestSetBtn
            // 
            this.ExecuteTestSetBtn.Location = new System.Drawing.Point(12, 12);
            this.ExecuteTestSetBtn.Name = "ExecuteTestSetBtn";
            this.ExecuteTestSetBtn.Size = new System.Drawing.Size(211, 59);
            this.ExecuteTestSetBtn.TabIndex = 0;
            this.ExecuteTestSetBtn.Text = "테스트 항목 실행";
            this.ExecuteTestSetBtn.UseVisualStyleBackColor = true;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("맑은 고딕", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(129)));
            this.label1.Location = new System.Drawing.Point(85, 86);
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
            this.TestSetListBox.Size = new System.Drawing.Size(286, 796);
            this.TestSetListBox.TabIndex = 2;
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(13F, 24F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(2109, 937);
            this.Controls.Add(this.TestSetListBox);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.ExecuteTestSetBtn);
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

        private System.Windows.Forms.Button ExecuteTestSetBtn;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.ListBox TestSetListBox;
    }
}

