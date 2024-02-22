import React from 'react';

import MainNavAppBar from '../../_global/components/MainNavAppBar';

const BookManagePage = () => {
    return (
        <>
            <MainNavAppBar focusedIndex={0} backArrowUrl="/book/myList"/>
        </>
    )
}

export default BookManagePage;