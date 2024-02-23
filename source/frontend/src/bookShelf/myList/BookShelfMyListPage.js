import React from 'react';
import { Box, Stack, Pagination } from '@mui/material';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookShelfSubAppBar from '../_global/BookShelfSubAppBar';
import BookShelfSearchInfo from '../_global/BookShelfSearchInfo';

const BookShelfMyListPage = () => {
    return (
        <>
            <MainNavAppBar focusedIndex={1}/>
            <BookShelfSubAppBar focusedIndex={0}
                searchTypes={[
                    {type: "bookShelfTitle", name: "책장 제목"},
                ]}
            />

            <Stack>
                <Stack direction="row" spacing={2}>
                    <BookShelfSearchInfo
                        bookShelfId={1}
                        bookShelfTitle={"My BookShelf"}
                        bookShelfBookCount={3}
                        bookShelfCreater={"TestCreater"}
                        bookShelfCreateDate={"2024-02-22 12:47"}
  
                        bookShelfTags={["AAAAA", "BBBBB", "CCCCC", "DDDDD"]}
                        isShared={false}
                        isSharedIconVisible={true}

                        onClickCardUrl={"/bookShelf/info/1"}
                        bookImageUrls={["/src/NoImage.jpg", "/src/NoImage.jpg", "/src/NoImage.jpg"]}
                    />
                </Stack>

                <Box sx={{width: "100%", marginTop: "10px", display: "flex", justifyContent: "center"}}>
                    <Pagination sx={{padding: "auto", margin: "0 auto"}} count={10}/>
                </Box>
            </Stack>
        </>
    )
}

export default BookShelfMyListPage;