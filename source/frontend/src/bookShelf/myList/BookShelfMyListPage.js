import React, { useEffect, useState, useContext } from 'react';
import { useSearchParams, useNavigate } from 'react-router-dom';

import { Box, Stack, Pagination, Backdrop, CircularProgress } from '@mui/material';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from '../../_global/provider/jwtToken/JwtTokenContext';
import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookShelfSubAppBar from '../_global/BookShelfSubAppBar';
import BookShelfSearchInfos from '../_global/BookShelfSearchInfos';
import SubscribeMessageCreatedSocket from '../../_global/socket/EventHandlerSocket';
import BookShelfProxy from '../../_global/proxy/BookShelfProxy';

const BookShelfMyListPage = () => {
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


    const [rawBookShelfInfos, setRawBookShelfInfos] = useState([])
    const [totalPages, setTotalPages] = useState(0)


    const onClickSearchButton = (searchText, searchType) => {
        if(searchText.length <= 0) return 
        navigate(`/bookShelf/myList?searchType=${searchType}&searchText=${searchText}&pageNumber=1`)
    }

    const onClickPageNumber = (_, page) => {
        navigate(`/bookShelf/myList?searchType=${searchInfo.searchType}&searchText=${searchInfo.searchText}&pageNumber=${page}`)
    }


    const [loadBookShelfs] = useState(() => {
        return async (searchInfo) => {
            try {

                let res = {}
                
                if(searchInfo.searchText.length > 0 && searchInfo.searchType==="bookShelfTitle")
                    res = await BookShelfProxy.searchBookShelfAllByCreaterIdAndTitle(jwtTokenState.jwtToken.id, searchInfo.searchText, searchInfo.pageNumber-1)
                else
                    res = await BookShelfProxy.searchBookShelfAllByCreaterId(jwtTokenState.jwtToken.id, searchInfo.pageNumber-1)

                setTotalPages(res.page.totalPages)
                setRawBookShelfInfos(res._embedded.bookShelfs)

            } catch (error) {
                addAlertPopUp("책장 정보를 가져오는 과정에서 오류가 발생했습니다!", "error");
                console.error("책장 정보를 가져오는 과정에서 오류가 발생했습니다!", error);
            }
        }
    })
    useEffect(() => {
        loadBookShelfs(searchInfo)
    }, [addAlertPopUp, jwtTokenState, queryParameters, searchInfo, loadBookShelfs])


    SubscribeMessageCreatedSocket(useState(() => {
        return (eventName) => {
        
            let successLog = ""
            if(eventName === "BookShelfCreated") successLog = "책장이 생성되었습니다."
            else if(eventName === "BookShelfIsSharedUpdated") successLog = "책장 공유 여부가 변경되었습니다."

            if(successLog.length > 0)
            {
                addAlertPopUp(successLog, "success")
                setIsBackdropOpened(false)
                loadBookShelfs(searchInfo)
            }

        }
    })[0])


    return (
        <>
            <MainNavAppBar focusedIndex={1}/>
            <BookShelfSubAppBar focusedIndex={0}
                searchTypes={[{type: "bookShelfTitle", name: "책장 제목"}]}
                handleOnSubmit={onClickSearchButton}
                setIsBackdropOpened={setIsBackdropOpened}
            />

            <Stack>
                <Stack direction="row" spacing={2}>
                    <BookShelfSearchInfos
                        rawBookShelfInfos={rawBookShelfInfos}
                        isEditIconVisible={true}
                        setIsBackdropOpened={setIsBackdropOpened}
                    />
                </Stack>

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

export default BookShelfMyListPage;