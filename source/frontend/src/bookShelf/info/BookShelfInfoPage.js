import React from 'react';
import { useParams } from 'react-router-dom';
import { Box, Stack, Pagination } from '@mui/material';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookShelfInfoSubAppBar from './BookShelfInfoSubAppBar';
import BookShelfBookSearchInfos from './BookShelfBookSearchInfos';

const BookShelfInfoPage = () => {
    const {bookShelfId} = useParams()
    console.log("bookShelfId :", bookShelfId)

    const onClickSearchButton = (searchText, searchType) => {
        alert("검색어: " + searchText + ", 검색 대상: " + searchType)
    }
    
    const onClickPageNumber = (_, page) => {
        alert("페이지 번호: " + page)
    }

    return (
        <>
            <MainNavAppBar focusedIndex={1} backArrowUrl={-1}/>
            <BookShelfInfoSubAppBar
                searchTypes={[{type: "bookTitle", name: "책 제목"},
                              {type: "bookCreater", name: "책 작성자"}
                ]}
                handleOnSubmit={onClickSearchButton}
                rawBookShelfInfo={{
                    title: "My BookShelf",
                    isShared: false
                }}
            />

            <Stack>
                <Stack direction="row" spacing={2}>
                    <BookShelfBookSearchInfos
                        rawBookInfos={[
                        {
                            id: 1,
                            title: "점프 투 파이썬",
                            creator: "TestCreater",
                            createdDate: "2024-02-22 12:47",
                            likeCount: 10,
                            tags: ["AAAAA", "BBBBB", "CCCCC", "DDDDD", "EEEEE"],
                            imageUrl: ""
                        }]}
                    />
                </Stack>
                
                <Box sx={{width: "100%", marginTop: "10px", display: "flex", justifyContent: "center"}}>
                    <Pagination count={10} onChange={onClickPageNumber} sx={{padding: "auto", margin: "0 auto"}}/>
                </Box>
            </Stack>
        </>
    )
}

export default BookShelfInfoPage;