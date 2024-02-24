import React from 'react';

import { Stack } from '@mui/material';

import BookShelfBookSearchInfo from './BookShelfBookSearchInfo';

const BookShelfBookSearchInfos = ({rawBookInfos}) => {
    return (
    <Stack spacing={0.5}>
        <Stack direction="row" spacing={0.5}>
            {
                rawBookInfos.slice(0, 3).map((rawBookInfo, index) => {
                    return (
                        <BookShelfBookSearchInfo
                            key={index}
                            rawBookInfo={rawBookInfo}
                        />
                    )
                })
            }
        </Stack>

        <Stack direction="row" spacing={0.5}>
        {
            rawBookInfos.slice(3, 6).map((rawBookInfo, index) => {
                return (
                    <BookShelfBookSearchInfo
                        key={index}
                        rawBookInfo={rawBookInfo}
                    />
                )
            })
        }
        </Stack>
    </Stack>
    )
}

export default BookShelfBookSearchInfos;