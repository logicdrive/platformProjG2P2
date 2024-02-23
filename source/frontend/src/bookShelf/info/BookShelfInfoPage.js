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
                        bookId={1}
                        bookTitle={"점프 투 파이썬"}
                        bookCreater={"TestCreater"}
                        bookCreateDate={"2024-02-22 12:47"}
                        bookLikeCount={10}
                        bookTags={["AAAAA", "BBBBB", "CCCCC", "DDDDD"]}

                        onClickCardUrl={"/book/info/1"}
                        bookImageUrl={""}
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