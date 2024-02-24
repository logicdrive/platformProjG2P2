import React from 'react';
import { Box, Stack, Pagination } from '@mui/material';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookShelfSubAppBar from '../_global/BookShelfSubAppBar';
import BookShelfSearchInfo from '../_global/BookShelfSearchInfo';

const BookShelfSharedListPage = () => {

    const onClickSearchButton = (searchText, searchType) => {
        alert("검색어: " + searchText + ", 검색 대상: " + searchType)
    }

    const onClickPageNumber = (_, page) => {
        alert("페이지 번호: " + page)
    }

    return (
        <>
            <MainNavAppBar focusedIndex={1}/>
            <BookShelfSubAppBar focusedIndex={1}
                searchTypes={[
                    {type: "bookShelfTitle", name: "책장 제목"},
                    {type: "bookShelfCreater", name: "책장 제작자"}
                ]}
                handleOnSubmit={onClickSearchButton}
            />

            <Stack>
                <Stack direction="row" spacing={2}>
                    <BookShelfSearchInfo
                        rawBookShelfInfo={{
                            id: 1,
                            title: "My BookShelf",
                            creator: "TestCreater",
                            createdDate: "2024-02-22 12:47",
                            bookCount: 3,
                            tags: ["AAAAA", "BBBBB", "CCCCC", "DDDDD"],
                            isShared: false,
                            imageUrls: ["/src/NoImage.jpg", "/src/NoImage.jpg", "/src/NoImage.jpg"]
                        }}
                        isEditIconVisible={false}
                    />
                </Stack>

                <Box sx={{width: "100%", marginTop: "10px", display: "flex", justifyContent: "center"}}>
                    <Pagination count={10} onChange={onClickPageNumber} sx={{padding: "auto", margin: "0 auto"}}/>
                </Box>
            </Stack>
        </>
    )
}

export default BookShelfSharedListPage;