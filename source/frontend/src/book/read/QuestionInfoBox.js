import React, { useState, useEffect } from 'react';
import { Box, Button } from "@mui/material";
import NormalText from '../../_global/components/text/NormalText';
import DictionaryTool from '../../_global/tool/DictionaryTool';

const QuestionInfoBox = ({rawProblemInfo, order}) => {
    const [isShowAnswer, setIsShowAnswer] = useState(false)
    const [problemInfo, setProblemInfo] = useState({})

    useEffect(() => {
        (async () => {
            if(DictionaryTool.isEmpty(rawProblemInfo)) {
                setProblemInfo({})
                return
            }

            setProblemInfo({
                id: rawProblemInfo.problemId,
                content: `Q${order}. ${rawProblemInfo.content}`,
                answer: rawProblemInfo.answer
            })
            setIsShowAnswer(false)
        })()
    }, [rawProblemInfo, order])


    return (
        <Box>
            <NormalText sx={{width: "840px", fontSize: "15px", marginTop: "5px", textOverflow: "ellipsis", whiteSpace: "pre-line"}}>
                {problemInfo.content}
            </NormalText>
            <Box>
                <NormalText sx={{float: "left"}}>
                    Answer:
                </NormalText>
                
                {
                    (isShowAnswer) ? (
                        <Button onClick={()=>{setIsShowAnswer(false)}} sx={{float: "left", marginLeft: "3px", marginTop: "1px", height: "18px", display: "flex", justifyContent: "left"}}>
                            <NormalText sx={{textAlign: "left"}}>{problemInfo.answer.split(".")[0]}</NormalText>
                        </Button>
                    ) : (
                        <Button onClick={()=>{setIsShowAnswer(true)}} sx={{float: "left", marginLeft: "3px", marginTop: "1px", backgroundColor: "gray", height: "18px"}}>

                        </Button>
                    )
                }
            </Box>
        </Box>
    )
}

export default QuestionInfoBox;