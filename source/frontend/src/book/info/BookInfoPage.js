import React from 'react';
import { useParams } from 'react-router-dom';
import { Container, Divider, Stack, Box, IconButton } from "@mui/material";
import ThumbUpIcon from '@mui/icons-material/ThumbUp';
import CollectionsBookmarkIcon from '@mui/icons-material/CollectionsBookmark';
import AutoStoriesIcon from '@mui/icons-material/AutoStories';
import ChatBubbleIcon from '@mui/icons-material/ChatBubble';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BoldText from '../../_global/components/text/BoldText';
import NormalText from '../../_global/components/text/NormalText';
import NavText from '../../_global/components/text/NavText';

const BookInfoPage = () => {
    const {bookId} = useParams()
    console.log("BookId :", bookId)

    return (
        <>
            <MainNavAppBar focusedIndex={0} backArrowUrl={-1}/>

            <Container maxWidth="md" sx={{padding: "5px", marginTop: "10px"}}>
                <Stack>
                    <BoldText sx={{fontSize: "25px"}}>Python</BoldText>
                    <Divider sx={{marginTop: "5px"}}/>

                    <Box sx={{marginTop: "15px", padding: "10px"}}>
                        <Box
                                component="img"
                                sx={{
                                    height: 300,
                                    width: 200,
                                    backgroundColor: "lightgray",
                                    borderRadius: 3,
                                    border: "1px solid lightgray",
                                    float:"left"

                                }}
                                alt="업로드된 이미지가 표시됩니다."
                                src={"/src/NoImage.jpg"}
                            />
                        <Stack sx={{float: "left", marginLeft: "10px"}}>
                            <NormalText sx={{fontSize: "20px"}}>작성자: TestCreater</NormalText>
                            <Divider sx={{marginTop: "5px", marginBottom: "5px", width: "620px"}}/>

                            <NormalText sx={{fontSize: "20px"}}>작성일: 2024-02-23 11:46</NormalText>
                            <NormalText sx={{fontSize: "20px"}}>수정일: 2024-02-25 11:46</NormalText>
                            <NormalText sx={{fontSize: "20px"}}>목차수: 5</NormalText>
                            
                            <Divider sx={{marginTop: "5px", marginBottom: "5px"}}/>
                            <IconButton onClick={(e)=>{e.stopPropagation(); alert("LIKE")}} sx={{paddingY: "0px", borderRadius: "5px", marginLeft: "-10px", width: "120px"}}>
                                <ThumbUpIcon sx={{fontSize: "20px"}}/> 
                                <BoldText sx={{marginTop: "3px", marginLeft: "3px", fontSize: "15px", color: "gray"}}>25 LIKES</BoldText>
                            </IconButton>

                            <Divider sx={{marginTop: "5px", marginBottom: "5px"}}/>
                            <Box sx={{marginTop: "88px"}}>
                                <Box sx={{float: "left", backgroundColor: "cornflowerblue", width: "85px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                                    <AutoStoriesIcon sx={{float: "left", color: "white"}}/>
                                    <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>책 읽기</NavText>
                                </Box>

                                <Box sx={{float: "left", marginLeft: "5px", backgroundColor: "cornflowerblue", width: "118px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                                    <CollectionsBookmarkIcon sx={{float: "left", color: "white"}}/>
                                    <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>책장에 추가</NavText>
                                </Box>
                            </Box>
                        </Stack>
                    </Box>
                    
                    <Box sx={{marginTop: "15px"}}>
                        <ChatBubbleIcon sx={{float: "left", color: "gray"}}/>
                        <BoldText sx={{float: "left", fontSize: "17px", marginLeft: "5px", color: "gray"}}>댓글: 5개</BoldText>
                    </Box>
                    <Divider sx={{marginTop: "5px", marginBottom: "5px", width: "100%"}}/>


                </Stack>
            </Container>
        </>
    )
}

export default BookInfoPage;