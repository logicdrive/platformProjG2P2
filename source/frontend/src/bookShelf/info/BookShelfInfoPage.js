import React, { useEffect, useState, useContext } from 'react';
import { useSearchParams, useNavigate, useParams } from 'react-router-dom';

import { Box, Stack, Pagination, Backdrop, CircularProgress } from '@mui/material';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from '../../_global/provider/jwtToken/JwtTokenContext';
import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookShelfInfoSubAppBar from './BookShelfInfoSubAppBar';
import BookShelfBookSearchInfos from './BookShelfBookSearchInfos';
import SubscribeMessageCreatedSocket from '../../_global/socket/EventHandlerSocket';
import BookShelfProxy from '../../_global/proxy/BookShelfProxy';
import BookShelfBookProxy from '../../_global/proxy/BookShelfBookProxy';

const BookShelfInfoPage = () => {
    const {bookShelfId} = useParams()
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


    const [rawBookShelfInfo, setRawBookShelfInfo] = useState({})
    const [rawBookShelfBookInfos, setRawBookShelfBookInfos] = useState([])
    const [totalPages, setTotalPages] = useState(0)


    const onClickSearchButton = (searchText, searchType) => {
        if(searchText.length <= 0) return 
        navigate(`/bookShelf/info/${bookShelfId}?searchType=${searchType}&searchText=${searchText}&pageNumber=1`)
    }

    const onClickPageNumber = (_, page) => {
        navigate(`/bookShelf/info/${bookShelfId}?searchType=${searchInfo.searchType}&searchText=${searchInfo.searchText}&pageNumber=${page}`)
    }


    const [loadBookInfos] = useState(() => {
        return async (searchInfo) => {
            try {

                setRawBookShelfInfo(await BookShelfProxy.searchBookShelfOneByBookShelfId(bookShelfId))


                let res = {}
                
                // if(searchInfo.searchText.length > 0 && searchInfo.searchType==="bookTitle")
                //     res = await BookProxy.searchBookAllByCreaterIdAndTitle(jwtTokenState.jwtToken.id, searchInfo.searchText, searchInfo.pageNumber-1)
                // else
                    res = await BookShelfBookProxy.searchBookShelfBooksByBookShelfId(bookShelfId, searchInfo.pageNumber-1)

                setTotalPages(res.page.totalPages)
                setRawBookShelfBookInfos(res._embedded.bookShelfBooks)

            } catch (error) {
                addAlertPopUp("책장 정보를 가져오는 과정에서 오류가 발생했습니다!", "error");
                console.error("책장 정보를 가져오는 과정에서 오류가 발생했습니다!", error);
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
            <MainNavAppBar focusedIndex={1} backArrowUrl={-1}/>
            <BookShelfInfoSubAppBar
                searchTypes={[{type: "bookTitle", name: "책 제목"},
                              {type: "bookCreater", name: "책 작성자"}
                ]}
                handleOnSubmit={onClickSearchButton}
                rawBookShelfInfo={rawBookShelfInfo}
                setIsBackdropOpened={setIsBackdropOpened}
            />

            <Stack>
                <Stack direction="row" spacing={2}>
                    <BookShelfBookSearchInfos
                        rawBookShelfBookInfos={rawBookShelfBookInfos}
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

export default BookShelfInfoPage;