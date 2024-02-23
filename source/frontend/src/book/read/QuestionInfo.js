import React, {useState} from 'react';
import { Box, Button } from "@mui/material";
import NormalText from '../../_global/components/text/NormalText';

const QuestionInfo = () => {
    const [isShowAnswer, setIsShowAnswer] = useState(false)

    return (
        <Box>
            <NormalText sx={{width: "840px", fontSize: "15px", marginTop: "5px", textOverflow: "ellipsis", whiteSpace: "pre-line"}}>
                Q1. What is the Python? <br/>
                
                A. Python is a programming language. <br/>
                B. Python is a snake. <br/>
                C. Python is a food. <br/>
            </NormalText>
            <Box>
                <NormalText sx={{float: "left"}}>
                    Answer:
                </NormalText>
                
                {
                    (isShowAnswer) ? (
                        <Button onClick={()=>{setIsShowAnswer(false)}} sx={{float: "left", marginLeft: "3px", marginTop: "1px", height: "18px", display: "flex", justifyContent: "left"}}>
                            <NormalText sx={{textAlign: "left"}}>112123123123</NormalText>
                        </Button>
                    ) : (
                        <Button onClick={()=>{setIsShowAnswer(true)}} sx={{float: "left", marginLeft: "3px", marginTop: "1px", backgroundColor: "gray", height: "18px", width: "100px"}}>

                        </Button>
                    )
                }
            </Box>
        </Box>
    )
}

export default QuestionInfo;