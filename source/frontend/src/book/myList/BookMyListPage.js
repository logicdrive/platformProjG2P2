import React, { useEffect, useState, useContext } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';

import { Box, Stack, Pagination, Backdrop, CircularProgress } from '@mui/material';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from '../../_global/provider/jwtToken/JwtTokenContext';
import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookSubAppBar from '../_global/BookSubAppBar';
import BookSearchInfos from '../../_global/components/card/BookSearchInfos';
import BookProxy from '../../_global/proxy/BookProxy';
import SubscribeMessageCreatedSocket from '../../_global/socket/EventHandlerSocket';

const BookMyListPage = () => {
    const navigate = useNavigate()
    const {addAlertPopUp} = useContext(AlertPopupContext)
    const {jwtTokenState} = useContext(JwtTokenContext)
    const [queryParameters] = useSearchParams()
    const [isBackdropOpened, setIsBackdropOpened] = useState(false)
    const [searchInfo, setSearchInfo] = useState({
        searchType: queryParameters.get("searchType") || "",
        searchText: queryParameters.get("searchText") || "",
        pageNumber: Number(queryParameters.get("pageNumber")) || 1
    })
    useEffect(() => {
        setSearchInfo({
            searchType: queryParameters.get("searchType") || "",
            searchText: queryParameters.get("searchText") || "",
            pageNumber: Number(queryParameters.get("pageNumber")) || 1
        })
    }, [queryParameters])


    const [rawBookInfos, setRawBookInfos] = useState([])
    const [totalPages, setTotalPages] = useState(0)


    const onClickSearchButton = (searchText, searchType) => {
        if(searchText.length <= 0) return 
        navigate(`/book/myList?searchType=${searchType}&searchText=${searchText}&pageNumber=1`)
    }

    const onClickPageNumber = (_, page) => {
        navigate(`/book/myList?searchType=${searchInfo.searchType}&searchText=${searchInfo.searchText}&pageNumber=${page}`)
    }


    const [loadBookInfos] = useState(() => {
        return async () => {
            try {

                let res = {}
                
                if(searchInfo.searchText.length > 0 && searchInfo.searchType==="bookTitle")
                    res = await BookProxy.searchBookAllByCreaterIdAndTitle(jwtTokenState.jwtToken.id, searchInfo.searchText, searchInfo.pageNumber-1)
                else
                    res = await BookProxy.searchBookAllByCreaterId(jwtTokenState.jwtToken.id, searchInfo.pageNumber-1)

                setTotalPages(res.page.totalPages)
                setRawBookInfos(res._embedded.books)

            } catch (error) {
                addAlertPopUp("책 정보를 가져오는 과정에서 오류가 발생했습니다!", "error");
                console.error("책 정보를 가져오는 과정에서 오류가 발생했습니다!", error);
            }
        }
    })
    useEffect(() => {
        loadBookInfos()
    }, [addAlertPopUp, jwtTokenState, queryParameters, searchInfo, loadBookInfos])


    SubscribeMessageCreatedSocket(useState(() => {
        return (eventName) => {
        
            let successLog = ""
            if(eventName === "BookLiked") successLog = "책에 좋아요를 추가했습니다."

            if(successLog.length > 0)
            {
                addAlertPopUp(successLog, "success")
                setIsBackdropOpened(false)
                loadBookInfos()
            }

        }
    })[0])


    return (
        <>
            <MainNavAppBar focusedIndex={0}/>
            <BookSubAppBar focusedIndex={0} 
                searchTypes={[{type: "bookTitle", name: "책 제목"}]}
                handleOnSubmit={onClickSearchButton}
            />

            <Stack>
                <BookSearchInfos
                    rawBookInfos={rawBookInfos}
                    isEditIconVisible={true}
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

export default BookMyListPage;