import React from 'react';
import { useParams } from 'react-router-dom';

import { Container, Divider, Stack, Box, Pagination } from "@mui/material";
import ChatBubbleIcon from '@mui/icons-material/ChatBubble';
import CollectionsBookmarkIcon from '@mui/icons-material/CollectionsBookmark';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BoldText from '../../_global/components/text/BoldText';
import BookInfoBox from './BookInfoBox';
import CommentsInfosBox from './CommentsInfosBox';
import AddCommentForm from './AddCommentForm';
import RelatedBookInfosBox from './RelatedBookInfosBox';

const BookInfoPage = () => {
    const {bookId} = useParams()
    console.log("BookId :", bookId)


    const onClickAddCommentButton = (commentText) => {
        alert(commentText)
    }

    const onClickCommentPageNumber = (_, page) => {
        alert("페이지 번호: " + page)
    }

    
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
                        <CollectionsBookmarkIcon sx={{float: "left", color: "gray"}}/>
                        <BoldText sx={{float: "left", fontSize: "17px", marginLeft: "5px", color: "gray"}}>비슷한 책 목록</BoldText>
                    </Box>
                    <Divider sx={{marginTop: "5px", marginBottom: "5px", width: "100%"}}/>
                    <RelatedBookInfosBox rawBookInfos={[
                        {
                            id: 1,
                            title: "점프 투 파이썬",
                            creator: "TestCreater",
                            createdDate: "2024-02-22 12:47",
                            likeCount: 10,
                            tags: ["AAAAA", "BBBBB", "CCCCC", "DDDDD"],
                            isShared: false,
                            imageUrl: ""
                        },

                        {
                            id: 1,
                            title: "점프 투 파이썬",
                            creator: "TestCreater",
                            createdDate: "2024-02-22 12:47",
                            likeCount: 10,
                            tags: ["AAAAA", "BBBBB", "CCCCC", "DDDDD"],
                            isShared: false,
                            imageUrl: ""
                        }
                    ]}/>


                    <Box sx={{marginTop: "15px"}}>
                        <ChatBubbleIcon sx={{float: "left", color: "gray"}}/>
                        <BoldText sx={{float: "left", fontSize: "17px", marginLeft: "5px", color: "gray"}}>댓글: 5개</BoldText>
                    </Box>
                    <Divider sx={{marginTop: "5px", marginBottom: "5px", width: "100%"}}/>
                    
                    <AddCommentForm onClickAddCommentButton={onClickAddCommentButton}/>


                    <CommentsInfosBox rawCommentInfos={[
                        {
                            id: 1,
                            creator: "TestCreater",
                            createdDate: "2024-02-23 11:46",
                            content: "TestContent"
                        }
                    ]}/>

                    <Box sx={{width: "100%", marginTop: "10px", display: "flex", justifyContent: "center"}}>
                        <Pagination count={10} onChange={onClickCommentPageNumber} sx={{padding: "auto", margin: "0 auto"}}/>
                    </Box>
                </Stack>
            </Container>
        </>
    )
}

export default BookInfoPage;