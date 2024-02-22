import React from 'react';
import { Box, Stack, Pagination } from '@mui/material';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookSubAppBar from '../_global/BookSubAppBar';
import BookSearchInfo from '../../_global/components/card/BookSearchInfo';

const BookMyListPage = () => {
    return (
        <>
            <MainNavAppBar focusedIndex={0}/>
            <BookSubAppBar focusedIndex={0}/>

            <Stack>
                <Stack direction="row" spacing={2}>
                    <BookSearchInfo
                        bookId={1}
                        bookTitle={"점프 투 파이썬"}
                        bookCreater={"TestCreater"}
                        bookCreateDate={"2024-02-22 12:47"}
                        bookLikeCount={10}
                        bookTags={["AAAAA", "BBBBB", "CCCCC", "DDDDD"]}

                        onClickCardUrl={"/book/info/1"}
                    />
                </Stack>
                
                <Box sx={{width: "100%", marginTop: "10px", display: "flex", justifyContent: "center"}}>
                    <Pagination sx={{padding: "auto", margin: "0 auto"}} count={10}/>
                </Box>
            </Stack>
        </>
    )
}

export default BookMyListPage;