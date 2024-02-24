import React, {useState} from 'react';
import { useParams } from 'react-router-dom';

import { Container, Divider, Stack, Box, Paper, InputBase, Pagination } from "@mui/material";
import ChatBubbleIcon from '@mui/icons-material/ChatBubble';
import SendIcon from '@mui/icons-material/Send';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BoldText from '../../_global/components/text/BoldText';
import BookInfoBox from './BookInfoBox';
import CommentsInfosBox from './CommentsInfosBox';

const BookInfoPage = () => {
    const {bookId} = useParams()
    console.log("BookId :", bookId)

    
    const [commentText, setCommentText] = useState("")

    return (
        <>
            <MainNavAppBar focusedIndex={0} backArrowUrl={-1}/>

            <Container maxWidth="md" sx={{padding: "5px", marginTop: "10px"}}>
                <Stack>
                    <BoldText sx={{fontSize: "25px"}}>Python</BoldText>
                    <Divider sx={{marginTop: "5px"}}/>

                    <BookInfoBox rawBookInfo={{
                        id: 1,
                        imageUrl: "/src/NoImage.jpg",
                        creator: "TestCreater",
                        createdDate: "2024-02-23 11:46",
                        editedDate: "2024-02-23 11:46",
                        indexCount: 5,
                        likeCount: 10
                    }}/>



                    <Box sx={{marginTop: "15px"}}>
                        <ChatBubbleIcon sx={{float: "left", color: "gray"}}/>
                        <BoldText sx={{float: "left", fontSize: "17px", marginLeft: "5px", color: "gray"}}>댓글: 5개</BoldText>
                    </Box>
                    <Divider sx={{marginTop: "5px", marginBottom: "5px", width: "100%"}}/>

                    <Paper component="form" sx={{width: "100%", height: "35px", marginTop: "2px",}}>
                        <InputBase sx={{marginLeft: "10px", width: "797px"}} value={commentText} onChange={(e)=>{setCommentText(e.target.value)}}/>
                        <Box onClick={()=>{}} 
                            sx={{
                                marginLeft: "10px", float:"right", height: "35px", minHeight: "35px", width: "35px", minWidth: "35px",
                                borderRadius: "0px 5px 5px 0px", backgroundColor: "cornflowerblue", "&:hover": {opacity: 0.80}, color: "white",
                                cursor: "pointer"
                        }}>
                            <SendIcon sx={{
                                    float:"right", height: "30px", minHeight: "30px", width: "30px", minWidth: "30px",
                                    marginTop: "2.5px", marginRight: "2.5px"
                            }}></SendIcon>
                        </Box>
                    </Paper>


                    <CommentsInfosBox rawCommandInfos={[
                        {
                            id: 1,
                            creator: "TestCreater",
                            createdDate: "2024-02-23 11:46",
                            content: "TestContent"
                        }
                    ]}/>

                    <Box sx={{width: "100%", marginTop: "10px", display: "flex", justifyContent: "center"}}>
                        <Pagination sx={{padding: "auto", margin: "0 auto"}} count={10}/>
                    </Box>
                </Stack>
            </Container>
        </>
    )
}

export default BookInfoPage;