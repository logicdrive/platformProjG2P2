import React from 'react';
import { useParams } from 'react-router-dom';
import { Box, Stack, Pagination } from '@mui/material';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookShelfInfoSubAppBar from './BookShelfInfoSubAppBar';
import BookShelfBookSearchInfo from './BookShelfBookSearchInfo';

const BookShelfInfoPage = () => {
    const {bookShelfId} = useParams()
    console.log("bookShelfId :", bookShelfId)

    return (
        <>
            <MainNavAppBar focusedIndex={1} backArrowUrl={-1}/>
            <BookShelfInfoSubAppBar bookShelfTitle={"My BookShelf"}
                searchTypes={[
                    {type: "bookShelfTitle", name: "책장 제목"},
                    {type: "bookCreater", name: "책 작성자"}
                ]}
            />

            <Stack>
                <Stack direction="row" spacing={2}>
                    <BookShelfBookSearchInfo
                        rawBookInfo={{
                            id: 1,
                            title: "점프 투 파이썬",
                            creator: "TestCreater",
                            createdDate: "2024-02-22 12:47",
                            likeCount: 10,
                            tags: ["AAAAA", "BBBBB", "CCCCC", "DDDDD"],
                            imageUrl: ""
                        }}
                    />
                </Stack>
                
                <Box sx={{width: "100%", marginTop: "10px", display: "flex", justifyContent: "center"}}>
                    <Pagination sx={{padding: "auto", margin: "0 auto"}} count={10}/>
                </Box>
            </Stack>
        </>
    )
}

export default BookShelfInfoPage;