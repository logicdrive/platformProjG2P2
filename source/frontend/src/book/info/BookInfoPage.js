import React, {useState} from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Container, Divider, Stack, Box, IconButton, Paper, InputBase, Pagination } from "@mui/material";
import ThumbUpIcon from '@mui/icons-material/ThumbUp';
import AutoStoriesIcon from '@mui/icons-material/AutoStories';
import ChatBubbleIcon from '@mui/icons-material/ChatBubble';
import SendIcon from '@mui/icons-material/Send';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BoldText from '../../_global/components/text/BoldText';
import NormalText from '../../_global/components/text/NormalText';
import NavText from '../../_global/components/text/NavText';
import AddToBookShelfButton from './AddToBookShelfButton';

const BookInfoPage = () => {
    const {bookId} = useParams()
    console.log("BookId :", bookId)

    const navigate = useNavigate()
    const [commentText, setCommentText] = useState("")

    const onClickAddToBookShelfButton = (selectedBookShelfId) => {
        alert("Add to BookShelf Button Clicked! Selected BookShelf Id: " + selectedBookShelfId)
    }


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
                                <Box onClick={()=>{navigate("/book/read/1/1")}}sx={{float: "left", backgroundColor: "cornflowerblue", width: "85px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                                    <AutoStoriesIcon sx={{float: "left", color: "white"}}/>
                                    <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>책 읽기</NavText>
                                </Box>

                                <AddToBookShelfButton onClickAddButton={onClickAddToBookShelfButton}/>
                            </Box>
                        </Stack>
                    </Box>
                    
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

                    <Stack
                        divider={<Divider flexItem/>}
                        spacing={3}
                        sx={{marginTop: "20px"}}
                    >
                        <Stack>
                            <Box>
                                <BoldText sx={{float: "left", fontSize: "15px"}}>TestCreater</BoldText>
                                
                                
                                <IconButton sx={{float: "right", position: "relative", bottom: "5px"}} onClick={(e)=>{e.stopPropagation(); alert("Delete")}}>
                                    <DeleteIcon sx={{fontSize: "15px"}}/> 
                                </IconButton>
                                <IconButton sx={{float: "right", position: "relative", bottom: "5px"}} onClick={(e)=>{e.stopPropagation(); alert("Edit")}}>
                                    <EditIcon sx={{fontSize: "15px"}}/> 
                                </IconButton>
                                <NormalText sx={{float: "right", fontSize: "15px"}}>2024-02-23 11:46</NormalText>
                            </Box>

                            <NormalText sx={{fontSize: "15px"}}>댓글내용입니다.</NormalText>
                        </Stack>
                    </Stack>
                    
                    <Box sx={{width: "100%", marginTop: "10px", display: "flex", justifyContent: "center"}}>
                        <Pagination sx={{padding: "auto", margin: "0 auto"}} count={10}/>
                    </Box>
                </Stack>
            </Container>
        </>
    )
}

export default BookInfoPage;