import React, { useState } from 'react';

import { Box, Stack, Pagination } from '@mui/material';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookSubAppBar from '../_global/BookSubAppBar';
import BookSearchInfos from '../../_global/components/card/BookSearchInfos';
import SubscribeMessageCreatedSocket from '../../_global/socket/EventHandlerSocket';

const BookMyListPage = () => {

    const onClickSearchButton = (searchText, searchType) => {
        alert("검색어: " + searchText + ", 검색 대상: " + searchType)
    }

    const onClickPageNumber = (_, page) => {
        alert("페이지 번호: " + page)
    }

    return (
        <>
            <MainNavAppBar focusedIndex={0}/>
            <BookSubAppBar focusedIndex={0} 
                searchTypes={[{type: "bookTitle", name: "책 제목"}]}
                handleOnSubmit={onClickSearchButton}
            />

            <Stack>
                <BookSearchInfos
                    rawBookInfos={[
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
                    ]}
                    isEditIconVisible={true}
                />
                
                <Box sx={{width: "100%", marginTop: "10px", display: "flex", justifyContent: "center"}}>
                    <Pagination count={10} onChange={onClickPageNumber} sx={{padding: "auto", margin: "0 auto"}}/>
                </Box>
            </Stack>
        </>
    )
}

export default BookMyListPage;