import React from 'react';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookShelfSubAppBar from '../_global/BookShelfSubAppBar';

const BookShelfSharedListPage = () => {
    return (
        <>
            <MainNavAppBar focusedIndex={1}/>
            <BookShelfSubAppBar focusedIndex={1}
                searchTypes={[
                    {type: "bookShelfTitle", name: "책장 제목"},
                    {type: "bookShelfCreater", name: "책장 제작자"}
                ]}
            />
        </>
    )
}

export default BookShelfSharedListPage;