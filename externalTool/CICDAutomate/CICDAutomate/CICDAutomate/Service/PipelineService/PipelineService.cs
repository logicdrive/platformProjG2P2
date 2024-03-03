using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;

namespace CICDAutomate.Service.PipelineService
{
    class PipelineService
    {
        public List<PipelineDto> pipelineDtoList { get; set; } = new List<PipelineDto>();

        
    }


    class PipelineDto
    {
        public string title { get; set; } = "";
        public DateTime createdDate { get; set; } = DateTime.Now;
        public DateTime updatedDate { get; set; } = DateTime.Now;

        List<ActionDto> actionList { get; set; } = new List<ActionDto>();


        public string dataPath { get; set; } = "";
        public string logPath { get; set; } = "";
    }

    class ActionDto
    {
        public string selectImagePath { get; set; } = "";
        public string title { get; set; } = "";
        public string description { get; set; } = "";
        Window editWindow { get; set; } = null;
    }
}
