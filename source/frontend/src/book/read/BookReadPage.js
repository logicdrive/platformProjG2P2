import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Divider, Box, Stack } from "@mui/material";
import ListIcon from '@mui/icons-material/List';
import AutoStoriesIcon from '@mui/icons-material/AutoStories';
import QuizIcon from '@mui/icons-material/Quiz';
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BoldText from '../../_global/components/text/BoldText';
import NormalText from '../../_global/components/text/NormalText';
import NavText from '../../_global/components/text/NavText';
import QuestionInfo from './QuestionInfo';
import IndexesInfoBox from './IndexesInfoBox';

const BookReadPage = () => {
    const {bookId, indexId} = useParams()
    console.log("BookId :", bookId, "IndexId :", indexId)

    const navigate = useNavigate();

    return (
        <>
            <MainNavAppBar focusedIndex={0} backArrowUrl={-1}/>

            <BoldText sx={{fontSize: "25px", marginTop: "10px", cursor: "context-menu"}}>Python</BoldText>
            <Divider sx={{marginTop: "5px"}}/>

            <Box sx={{display: "flex"}}>
                <Stack sx={{float: "left", width: "300px"}}>
                    <Box sx={{cursor: "context-menu"}}>
                        <ListIcon sx={{float: "left", paddingTop: "2px"}}/>
                        <NormalText sx={{fontSize: "20px", float: "left"}}>목차</NormalText>
                    </Box>
                    
                    <IndexesInfoBox 
                        bookId={bookId} 
                        rawIndexInfos={[
                            {id: 1, title: "Python 소개"},
                            {id: 2, title: "Python 기초 문법"}]}
                        focusedIndex={indexId}
                    />
                </Stack>

                <Divider orientation="vertical" flexItem/>
                <Stack sx={{float: "left", flex: "1 0 auto", paddingX: "5px"}}>
                    <Stack>
                        <Box sx={{cursor: "context-menu"}}>
                            <AutoStoriesIcon sx={{float: "left", paddingTop: "2px"}}/>
                            <NormalText sx={{fontSize: "20px", float: "left", marginLeft: "2px"}}>학습</NormalText>
                        </Box>
                        
                        <Box
                                component="img"
                                sx={{
                                    height: 250,
                                    width: 500,
                                    backgroundColor: "lightgray",
                                    borderRadius: 3,
                                    border: "1px solid lightgray",
                                    float:"left",
                                    marginX: "auto"

                                }}
                                alt="업로드된 이미지가 표시됩니다."
                                src={"/src/NoImage.jpg"}
                            />
                            
                            <NormalText sx={{width: "840px", fontSize: "15px", marginTop: "5px", textOverflow: "ellipsis"}}>
                                Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is 
                            </NormalText>
                    </Stack>
                    <Divider sx={{marginY: "5px"}}/>

                    <Stack>
                        <Box sx={{cursor: "context-menu"}}>
                            <QuizIcon sx={{float: "left", paddingTop: "2px"}}/>
                            <NormalText sx={{fontSize: "20px", float: "left", marginLeft: "2px"}}>퀴즈</NormalText>
                        </Box>
                        
                        <Stack
                            spacing={2}
                        >
                            <QuestionInfo/>
                            <QuestionInfo/>
                        </Stack>
                    </Stack>
                    <Divider sx={{marginY: "5px"}}/>

                    <Box sx={{"display": "center", "justifyContent": "center"}}>
                        <Box onClick={()=>{navigate("/book/read/1/1")}}sx={{float: "left", backgroundColor: "cornflowerblue", width: "100px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                            <ArrowBackIosIcon sx={{float: "left", color: "white"}}/>
                            <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>이전 목차</NavText>
                        </Box>

                        <Box onClick={()=>{navigate("/book/read/1/1")}}sx={{marginLeft: "10px", float: "left", backgroundColor: "cornflowerblue", width: "105px", height: "25px", paddingY: "8px", paddingLeft: "12px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                            <NavText sx={{float: "left", marginTop: "2px", marginRight: "10px"}}>다음 목차</NavText>
                            <ArrowForwardIosIcon sx={{float: "left", color: "white"}}/>
                        </Box>
                    </Box>
                </Stack>
            </Box>
        </>
    )
}

export default BookReadPage;