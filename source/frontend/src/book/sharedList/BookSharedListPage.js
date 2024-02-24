import React from 'react';
import { Box, Stack, Pagination } from '@mui/material';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookSubAppBar from '../_global/BookSubAppBar';
import BookSearchInfo from '../../_global/components/card/BookSearchInfo';

const BookSharedListPage = () => {
    return (
        <>
            <MainNavAppBar focusedIndex={0}/>
            <BookSubAppBar focusedIndex={1}
                searchTypes={[{type: "bookTitle", name: "책 제목"},
                              {type: "bookCreater", name: "책 작성자"}]}
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
                        isEditIconVisible={false}
                    />
                </Stack>
                
                <Box sx={{width: "100%", marginTop: "10px", display: "flex", justifyContent: "center"}}>
                    <Pagination sx={{padding: "auto", margin: "0 auto"}} count={10}/>
                </Box>
            </Stack>
        </>
    )
}

export default BookSharedListPage;