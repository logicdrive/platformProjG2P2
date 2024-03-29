import React from 'react';

import { Stack } from '@mui/material';

import BookShelfSearchInfo from './BookShelfSearchInfo';

const BookShelfSearchInfos = ({rawBookShelfInfos, isEditIconVisible, setIsBackdropOpened}) => {
    return (
    <Stack spacing={0.5}>
        <Stack direction="row" spacing={0.5}>
            {
                rawBookShelfInfos.slice(0, 3).map((rawBookShelfInfo, index) => {
                    return (
                        <BookShelfSearchInfo
                            key={index}
                            rawBookShelfInfo={rawBookShelfInfo}
                            isEditIconVisible={isEditIconVisible}
                            setIsBackdropOpened={setIsBackdropOpened}
                        />
                    )
                })
            }
        </Stack>

        <Stack direction="row" spacing={0.5}>
        {
            rawBookShelfInfos.slice(3, 6).map((rawBookShelfInfo, index) => {
                return (
                    <BookShelfSearchInfo
                        key={index}
                        rawBookShelfInfo={rawBookShelfInfo}
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

export default BookShelfSearchInfos;