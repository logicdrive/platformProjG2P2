import React from 'react';
import { useParams } from 'react-router-dom';

import MainNavAppBar from '../../_global/components/MainNavAppBar';

const BookReadPage = () => {
    const {bookId, indexId} = useParams()
    console.log("BookId :", bookId, "IndexId :", indexId)

    return (
        <>
            <MainNavAppBar focusedIndex={0} backArrowUrl={-1}/>
        </>
    )
}

export default BookReadPage;