import React from 'react';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookSubAppBar from '../_global/BookSubAppBar';

const BookSharedListPage = () => {
    return (
        <>
            <MainNavAppBar focusedIndex={0}/>
            <BookSubAppBar focusedIndex={1}
                searchTypes={[{type: "bookTitle", name: "책 제목"},
                              {type: "bookCreater", name: "책 작성자"}]}
            />
        </>
    )
}

export default BookSharedListPage;