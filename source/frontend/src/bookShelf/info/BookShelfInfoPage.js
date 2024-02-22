import React from 'react';
import { useParams } from 'react-router-dom';

import MainNavAppBar from '../../_global/components/MainNavAppBar';

const BookShelfInfoPage = () => {
    const {bookShelfId} = useParams()
    console.log("bookShelfId :", bookShelfId)

    return (
        <>
            <MainNavAppBar focusedIndex={1} backArrowUrl={-1}/>
        </>
    )
}

export default BookShelfInfoPage;