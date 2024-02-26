import React from 'react';

import { Stack } from '@mui/material';

import BookShelfBookSearchInfo from './BookShelfBookSearchInfo';

const BookShelfBookSearchInfos = ({rawBookShelfBookInfos, setIsBackdropOpened}) => {
    return (
    <Stack spacing={0.5}>
        <Stack direction="row" spacing={0.5}>
            {
                rawBookShelfBookInfos.slice(0, 3).map((rawBookShelfBookInfo, index) => {
                    return (
                        <BookShelfBookSearchInfo
                            key={index}
                            rawBookShelfBookInfo={rawBookShelfBookInfo}
                            setIsBackdropOpened={setIsBackdropOpened}
                        />
                    )
                })
            }
        </Stack>

        <Stack direction="row" spacing={0.5}>
        {
            rawBookShelfBookInfos.slice(3, 6).map((rawBookShelfBookInfo, index) => {
                return (
                    <BookShelfBookSearchInfo
                        key={index}
                        rawBookShelfBookInfo={rawBookShelfBookInfo}
                        setIsBackdropOpened={setIsBackdropOpened}
                    />
                )
            })
        }
        </Stack>
    </Stack>
    )
}

export default BookShelfBookSearchInfos;