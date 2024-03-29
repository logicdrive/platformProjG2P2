import React from 'react';

import { Stack } from '@mui/material';

import BookSearchInfo from './BookSearchInfo';

const BookSearchInfos = ({rawBookInfos, isEditIconVisible, setIsBackdropOpened}) => {
    return (
    <Stack spacing={0.5}>
        <Stack direction="row" spacing={0.5}>
            {
                rawBookInfos.slice(0, 3).map((rawBookInfo, index) => {
                    return (
                        <BookSearchInfo
                            key={index}
                            rawBookInfo={rawBookInfo}
                            isEditIconVisible={isEditIconVisible}
                            setIsBackdropOpened={setIsBackdropOpened}
                        />
                    )
                })
            }
        </Stack>

        <Stack direction="row" spacing={0.5}>
        {
            rawBookInfos.slice(3, 6).map((rawBookInfo, index) => {
                return (
                    <BookSearchInfo
                        key={index}
                        rawBookInfo={rawBookInfo}
                        isEditIconVisible={isEditIconVisible}
                        setIsBackdropOpened={setIsBackdropOpened}
                    />
                )
            })
        }
        </Stack>
    </Stack>
    )
}

export default BookSearchInfos;