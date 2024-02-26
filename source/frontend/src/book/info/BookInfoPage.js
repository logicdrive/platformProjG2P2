import React, { useEffect, useState } from 'react';
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
import RecommenedBookToBookProxy from '../../_global/proxy/RecommenedBookToBookProxy';
import BookProxy from '../../_global/proxy/BookProxy';

const BookInfoPage = () => {
    const {bookId} = useParams()

    const [rawBookInfo, setRawBookInfo] = useState({})
    useEffect(() => {
        (async () => {
            setRawBookInfo(await BookProxy.searchBookOneByBookId(bookId))
        })()
    }, [bookId])

    const [rawRecommendedBookInfos, setRawRecommendedBookInfos] = useState([])
    useEffect(() => {
        (async () => {
            const recommenedBookToBook = (await RecommenedBookToBookProxy.searchRecommendBookToBookAllByBookId(bookId))._embedded.recommenedBookToBooks
            
            let bookInfos = []
            for(let i=0; i<recommenedBookToBook.length; i++) {
                bookInfos.push(await BookProxy.searchBookOneByBookId(recommenedBookToBook[i].recommendedBookId))
            }
            setRawRecommendedBookInfos(bookInfos)
        })()
    }, [bookId])


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
                    <BoldText sx={{fontSize: "25px"}}>{rawBookInfo.title}</BoldText>
                    <Divider sx={{marginTop: "5px"}}/>

                    <BookInfoBox rawBookInfo={rawBookInfo}/>


                    <Box sx={{marginTop: "15px"}}>
                        <CollectionsBookmarkIcon sx={{float: "left", color: "gray"}}/>
                        <BoldText sx={{float: "left", fontSize: "17px", marginLeft: "5px", color: "gray"}}>비슷한 책 목록</BoldText>
                    </Box>
                    <Divider sx={{marginTop: "5px", marginBottom: "5px", width: "100%"}}/>
                    <RelatedBookInfosBox rawBookInfos={rawRecommendedBookInfos}/>


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