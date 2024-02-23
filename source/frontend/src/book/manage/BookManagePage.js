import React from 'react';
import { useParams } from 'react-router-dom';

import MainNavAppBar from '../../_global/components/MainNavAppBar';

const BookManagePage = () => {
    const {bookId} = useParams()
    console.log("BookId :", bookId)

    return (
        <>
            <MainNavAppBar focusedIndex={0} backArrowUrl="/book/myList"/>
        </>
    )
}

export default BookManagePage;