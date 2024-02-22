import React from 'react';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookShelfSubAppBar from '../_global/BookShelfSubAppBar';

const BookShelfMyListPage = () => {
    return (
        <>
            <MainNavAppBar focusedIndex={1}/>
            <BookShelfSubAppBar focusedIndex={0}/>
        </>
    )
}

export default BookShelfMyListPage;