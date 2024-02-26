import React, { useEffect, useState, useContext } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';

import { Box, Stack, Pagination, Backdrop, CircularProgress } from '@mui/material';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from '../../_global/provider/jwtToken/JwtTokenContext';
import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookSubAppBar from '../_global/BookSubAppBar';
import BookSearchInfos from '../../_global/components/card/BookSearchInfos';
import RecommendBookToUserProxy from '../../_global/proxy/RecommendBookToUserProxy';
import SubscribeMessageCreatedSocket from '../../_global/socket/EventHandlerSocket';
import BookProxy from '../../_global/proxy/BookProxy';

const BookRecommendPage = () => {
    const navigate = useNavigate()
    const {addAlertPopUp} = useContext(AlertPopupContext)
    const {jwtTokenState} = useContext(JwtTokenContext)
    const [queryParameters] = useSearchParams()
    const [isBackdropOpened, setIsBackdropOpened] = useState(false)

    const [rawBookInfos, setRawBookInfos] = useState([])
    const [totalPages, setTotalPages] = useState(0)
    const [searchInfo, setSearchInfo] = useState({
        pageNumber: Number(queryParameters.get("pageNumber")) || 1
    })
    useEffect(() => {
        setSearchInfo({
            pageNumber: Number(queryParameters.get("pageNumber")) || 1
        })
    }, [queryParameters])


    const onClickPageNumber = (_, page) => {
        navigate(`/book/recommend?pageNumber=${page}`)
    }


    const [loadBookInfos] = useState(() => {
        return async (searchInfo) => {
            try {

                const recommendBookToUsers = (await RecommendBookToUserProxy.searchRecommendBookToUserAllByUserId(jwtTokenState.jwtToken.id, searchInfo.pageNumber-1))
                const recommendBookToUsersDatas = recommendBookToUsers._embedded.recommendBookToUsers

                let bookInfos = []
                for(let i=0; i<recommendBookToUsersDatas.length; i++) {
                    bookInfos.push(await BookProxy.searchBookOneByBookId(recommendBookToUsersDatas[i].recommendedBookId))
                }

                setTotalPages(recommendBookToUsers.page.totalPages)
                setRawBookInfos(bookInfos)

            } catch (error) {
                addAlertPopUp("책 정보를 가져오는 과정에서 오류가 발생했습니다!", "error");
                console.error("책 정보를 가져오는 과정에서 오류가 발생했습니다!", error);
            }
        }
    })
    useEffect(() => {
        loadBookInfos(searchInfo)
    }, [addAlertPopUp, jwtTokenState, queryParameters, searchInfo, loadBookInfos])


    SubscribeMessageCreatedSocket(useState(() => {
        return (eventName) => {
        
            let successLog = ""
            if(eventName === "BookLiked") successLog = "책에 좋아요를 추가했습니다."

            if(successLog.length > 0)
            {
                addAlertPopUp(successLog, "success")
                setIsBackdropOpened(false)
                loadBookInfos(searchInfo)
            }

        }
    })[0])

    return (
        <>
            <MainNavAppBar focusedIndex={0}/>
            <BookSubAppBar focusedIndex={2}/>

            <Stack>
                <BookSearchInfos
                    rawBookInfos={rawBookInfos}
                    isEditIconVisible={false}
                    setIsBackdropOpened={setIsBackdropOpened}
                />
                
                <Box sx={{width: "100%", marginTop: "10px", display: "flex", justifyContent: "center"}}>
                    <Pagination count={totalPages} onChange={onClickPageNumber} sx={{padding: "auto", margin: "0 auto"}}/>
                </Box>
            </Stack>

            <Backdrop
                sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
                open={isBackdropOpened}
            >
                <CircularProgress color="inherit" />
            </Backdrop>
        </>
    )
}

export default BookRecommendPage;