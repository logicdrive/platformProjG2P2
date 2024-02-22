import React from 'react';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookShelfSubAppBar from '../_global/BookShelfSubAppBar';

const BookShelfSharedListPage = () => {
    return (
        <>
            <MainNavAppBar focusedIndex={1}/>
            <BookShelfSubAppBar focusedIndex={1}/>
        </>
    )
}

export default BookShelfSharedListPage;