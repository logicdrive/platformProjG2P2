import React from 'react';
import { useParams } from 'react-router-dom';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookShelfInfoSubAppBar from './BookShelfInfoSubAppBar';

const BookShelfInfoPage = () => {
    const {bookShelfId} = useParams()
    console.log("bookShelfId :", bookShelfId)

    return (
        <>
            <MainNavAppBar focusedIndex={1} backArrowUrl={-1}/>
            <BookShelfInfoSubAppBar bookShelfTitle={"My BookShelf"}
                searchTypes={[
                    {type: "bookShelfTitle", name: "책장 제목"},
                    {type: "bookCreater", name: "책 작성자"}
                ]}
            />
        </>
    )
}

export default BookShelfInfoPage;