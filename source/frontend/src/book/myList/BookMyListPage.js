import React from 'react';
import { Box, Stack, Pagination } from '@mui/material';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookSubAppBar from '../_global/BookSubAppBar';
import BookSearchInfo from '../../_global/components/card/BookSearchInfo';

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
                <Stack direction="row" spacing={2}>
                    <BookSearchInfo
                        rawBookInfo={{
                            id: 1,
                            title: "점프 투 파이썬",
                            creator: "TestCreater",
                            createdDate: "2024-02-22 12:47",
                            likeCount: 10,
                            tags: ["AAAAA", "BBBBB", "CCCCC", "DDDDD"],
                            isShared: false,
                            imageUrl: ""
                        }}
                        isEditIconVisible={true}
                    />
                </Stack>
                
                <Box sx={{width: "100%", marginTop: "10px", display: "flex", justifyContent: "center"}}>
                    <Pagination count={10} onChange={onClickPageNumber} sx={{padding: "auto", margin: "0 auto"}}/>
                </Box>
            </Stack>
        </>
    )
}

export default BookMyListPage;