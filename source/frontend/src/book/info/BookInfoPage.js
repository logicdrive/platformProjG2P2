import React, { useEffect, useState, useContext } from 'react';
import { useParams } from 'react-router-dom';

import { Container, Divider, Stack, Box, Pagination, Backdrop, CircularProgress } from "@mui/material";
import ChatBubbleIcon from '@mui/icons-material/ChatBubble';
import CollectionsBookmarkIcon from '@mui/icons-material/CollectionsBookmark';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BoldText from '../../_global/components/text/BoldText';
import BookInfoBox from './BookInfoBox';
import CommentsInfosBox from './CommentsInfosBox';
import AddCommentForm from './AddCommentForm';
import RelatedBookInfosBox from './RelatedBookInfosBox';
import RecommenedBookToBookProxy from '../../_global/proxy/RecommenedBookToBookProxy';
import BookProxy from '../../_global/proxy/BookProxy';
import CommentProxy from '../../_global/proxy/CommentProxy';
import SubscribeMessageCreatedSocket from '../../_global/socket/EventHandlerSocket';

const BookInfoPage = () => {
    const {bookId} = useParams()
    const [isBackdropOpened, setIsBackdropOpened] = useState(false)
    const {addAlertPopUp} = useContext(AlertPopupContext)

    const [rawBookInfo, setRawBookInfo] = useState({})
    const [loadBookInfos] = useState(() => {
        return async (bookId) => {
            try {

                setRawBookInfo(await BookProxy.searchBookOneByBookId(bookId))

            } catch (error) {
                addAlertPopUp("책 정보를 가져오는 과정에서 오류가 발생했습니다!", "error");
                console.error("책 정보를 가져오는 과정에서 오류가 발생했습니다!", error);
            }
        }
    })
    useEffect(() => {
        loadBookInfos(bookId)
    }, [bookId, loadBookInfos])

    const [rawCommentInfos, setRawCommentInfos] = useState([])
    const [totalCommentCount, setTotalCommentCount] = useState(0)
    const [totalPages, setTotalPages] = useState(0)
    
    const [currentPage, setCurrentPage] = useState(1)
    const [loadCommentInfos] = useState(() => {
        return async (bookId, currentPage) => {
            try {

                const res = await CommentProxy.searchCommentAllByBookId(bookId, currentPage-1)
                setRawCommentInfos(res._embedded.comments)
                setTotalCommentCount(res.page.totalElements)
                setTotalPages(res.page.totalPages)

            } catch (error) {
                addAlertPopUp("댓글 정보를 가져오는 과정에서 오류가 발생했습니다!", "error");
                console.error("댓글 정보를 가져오는 과정에서 오류가 발생했습니다!", error);
            }
        }
    })
    useEffect(() => {
        loadCommentInfos(bookId, currentPage)
    }, [bookId, currentPage, loadCommentInfos])


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


    const onClickAddCommentButton = async (commentText) => {
        try {

            setIsBackdropOpened(true)
            await CommentProxy.createComment(bookId, commentText)

          } catch(error) {
            addAlertPopUp("댓글을 추가하는 도중에 오류가 발생했습니다!", "error")
            console.error("댓글을 추가하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }

    const onClickCommentPageNumber = (_, page) => {
        setCurrentPage(page)
    }


    const [socketCallBack, setSocketCallBack] = useState(() => {
        return (eventName) => {
        }
    })
    useEffect(() => {
        setSocketCallBack(() => {
            return (eventName) => {
            
                let successLog = ""
                if(eventName === "BookLiked") successLog = "책에 좋아요를 추가했습니다."
                else if(eventName === "CommentCreated") successLog = "댓글을 추가했습니다."
                else if(eventName === "CommentUpdated") successLog = "댓글을 수정했습니다."
                else if(eventName === "CommentDeleted") successLog = "댓글을 삭제했습니다."
                else if(eventName === "BookShelfBookAdded") successLog = "책장에 책을 추가했습니다."
    
                if(successLog.length > 0)
                {
                    addAlertPopUp(successLog, "success")
                    setIsBackdropOpened(false)
                    loadBookInfos(bookId)

                    setCurrentPage(1)
                    loadCommentInfos(bookId, 1)
                }
    
            }
        })
    }, [bookId, addAlertPopUp, loadBookInfos, loadCommentInfos])
    SubscribeMessageCreatedSocket(socketCallBack)

    
    return (
        <>
            <MainNavAppBar focusedIndex={0} backArrowUrl={-1}/>

            <Container maxWidth="md" sx={{padding: "5px", marginTop: "10px"}}>
                <Stack>
                    <BoldText sx={{fontSize: "25px"}}>{rawBookInfo.title}</BoldText>
                    <Divider sx={{marginTop: "5px"}}/>

                    <BookInfoBox rawBookInfo={rawBookInfo} setIsBackdropOpened={setIsBackdropOpened}/>


                    <Box sx={{marginTop: "15px"}}>
                        <CollectionsBookmarkIcon sx={{float: "left", color: "gray"}}/>
                        <BoldText sx={{float: "left", fontSize: "17px", marginLeft: "5px", color: "gray"}}>비슷한 책 목록</BoldText>
                    </Box>
                    <Divider sx={{marginTop: "5px", marginBottom: "5px", width: "100%"}}/>
                    <RelatedBookInfosBox rawBookInfos={rawRecommendedBookInfos}/>


                    <Box sx={{marginTop: "15px"}}>
                        <ChatBubbleIcon sx={{float: "left", color: "gray"}}/>
                        <BoldText sx={{float: "left", fontSize: "17px", marginLeft: "5px", color: "gray"}}>댓글: {totalCommentCount}개</BoldText>
                    </Box>
                    <Divider sx={{marginTop: "5px", marginBottom: "5px", width: "100%"}}/>
                    
                    <AddCommentForm onClickAddCommentButton={onClickAddCommentButton}/>


                    <CommentsInfosBox rawCommentInfos={rawCommentInfos} setIsBackdropOpened={setIsBackdropOpened}/>

                    <Box sx={{width: "100%", marginTop: "10px", display: "flex", justifyContent: "center"}}>
                        <Pagination page={currentPage} count={totalPages} onChange={onClickCommentPageNumber} sx={{padding: "auto", margin: "0 auto"}}/>
                    </Box>
                </Stack>
            </Container>

            <Backdrop
                sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
                open={isBackdropOpened}
            >
                <CircularProgress color="inherit" />
            </Backdrop>
        </>
    )
}

export default BookInfoPage;