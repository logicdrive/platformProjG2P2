import React, { useState, useContext, useEffect } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';

import { Box, Stack, Pagination, Backdrop, CircularProgress } from '@mui/material';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookSubAppBar from '../_global/BookSubAppBar';
import BookSearchInfos from '../../_global/components/card/BookSearchInfos';
import BookProxy from '../../_global/proxy/BookProxy';
import UserProxy from '../../_global/proxy/UserProxy';
import SubscribeMessageCreatedSocket from '../../_global/socket/EventHandlerSocket';

const BookSharedListPage = () => {
    const navigate = useNavigate()
    const [isBackdropOpened, setIsBackdropOpened] = useState(false)
    const {addAlertPopUp} = useContext(AlertPopupContext)
    const [queryParameters] = useSearchParams()
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
        navigate(`/book/sharedList?searchType=${searchType}&searchText=${searchText}&pageNumber=1`)
    }

    const onClickPageNumber = (_, page) => {
        navigate(`/book/sharedList?searchType=${searchInfo.searchType}&searchText=${searchInfo.searchText}&pageNumber=${page}`)
    }


    const [loadBookInfos] = useState(() => {
        return async (searchInfo) => {
            try {

                let res = {}
                
                if(searchInfo.searchText.length > 0 && searchInfo.searchType==="bookTitle")
                    res = await BookProxy.searchBookAllByIsSharedAndTitle(true, searchInfo.searchText, searchInfo.pageNumber-1)
                if(searchInfo.searchText.length > 0 && searchInfo.searchType==="bookCreater") {
                    const searchedUsers = (await UserProxy.searchUserAllByName(searchInfo.searchText))._embedded.users
                    if(searchedUsers.length <= 0) {
                        setRawBookInfos([])
                        return
                    }
                    
                    res = await BookProxy.searchBookAllByIsSharedAndCreaterId(true, searchedUsers[0].userId, searchInfo.pageNumber-1)
                }
                else
                    res = await BookProxy.searchBookAllByIsShared(true, searchInfo.pageNumber-1)

                setTotalPages(res.page.totalPages)
                setRawBookInfos(res._embedded.books)

            } catch (error) {
                addAlertPopUp("책 정보를 가져오는 과정에서 오류가 발생했습니다!", "error");
                console.error("책 정보를 가져오는 과정에서 오류가 발생했습니다!", error);
            }
        }
    })
    useEffect(() => {
        loadBookInfos(searchInfo)
    }, [addAlertPopUp, queryParameters, searchInfo, loadBookInfos])


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
            <BookSubAppBar focusedIndex={1}
                searchTypes={[{type: "bookTitle", name: "책 제목"},
                              {type: "bookCreater", name: "책 작성자"}]}
                handleOnSubmit={onClickSearchButton}
            />

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

export default BookSharedListPage;