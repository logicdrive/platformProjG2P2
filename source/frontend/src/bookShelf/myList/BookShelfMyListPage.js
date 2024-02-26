import React, { useState, useContext } from 'react';
import { Box, Stack, Pagination, Backdrop, CircularProgress } from '@mui/material';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookShelfSubAppBar from '../_global/BookShelfSubAppBar';
import BookShelfSearchInfos from '../_global/BookShelfSearchInfos';
import SubscribeMessageCreatedSocket from '../../_global/socket/EventHandlerSocket';

const BookShelfMyListPage = () => {
    const {addAlertPopUp} = useContext(AlertPopupContext)
    const [isBackdropOpened, setIsBackdropOpened] = useState(false)

    const onClickSearchButton = (searchText, searchType) => {
        alert("검색어: " + searchText + ", 검색 대상: " + searchType)
    }

    const onClickPageNumber = (_, page) => {
        alert("페이지 번호: " + page)
    }


    SubscribeMessageCreatedSocket(useState(() => {
        return (eventName) => {
        
            let successLog = ""
            if(eventName === "BookShelfCreated") successLog = "책장이 생성되었습니다."

            if(successLog.length > 0)
            {
                addAlertPopUp(successLog, "success")
                setIsBackdropOpened(false)
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
                        rawBookShelfInfos={[{
                            id: 1,
                            title: "My BookShelf",
                            creator: "TestCreater",
                            createdDate: "2024-02-22 12:47",
                            bookCount: 3,
                            tags: ["AAAAA", "BBBBB", "CCCCC", "DDDDD", "EEEEE"],
                            isShared: false,
                            imageUrls: ["/src/NoImage.jpg", "/src/NoImage.jpg", "/src/NoImage.jpg"]
                        }]}
                        isEditIconVisible={true}
                    />
                </Stack>

                <Box sx={{width: "100%", marginTop: "10px", display: "flex", justifyContent: "center"}}>
                    <Pagination count={10} onChange={onClickPageNumber} sx={{padding: "auto", margin: "0 auto"}}/>
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