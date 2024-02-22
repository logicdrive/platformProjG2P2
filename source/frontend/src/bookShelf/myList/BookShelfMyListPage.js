import React from 'react';
import { Box } from "@mui/material";
import BookIcon from '@mui/icons-material/Book';
import CollectionsBookmarkIcon from '@mui/icons-material/CollectionsBookmark';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';

import TopAppBar from '../../_global/components/TopAppBar';
import IconNavigationButton from '../../_global/components/button/IconNavigationButton';
import NavText from '../../_global/components/text/NavText';

const BookShelfMyListPage = () => {
    return (
        <>
            <TopAppBar title="BOOKGEN" titleLink="/book/myList">
                <IconNavigationButton url="/book/myList" sx={{marginLeft: "20px", marginBottom: "10px"}} buttonSx={{width: "75px"}} textSx={{width: "75px"}}>
                    <BookIcon sx={{float: "left"}}/>
                    <NavText sx={{width: "75px", marginTop: "2px"}}>전자책</NavText>
                </IconNavigationButton>

                <IconNavigationButton url="/bookShelf/myList" sx={{marginLeft: "15px", marginBottom: "10px"}} buttonSx={{width: "60px"}} textSx={{width: "60px"}}>
                    <CollectionsBookmarkIcon sx={{float: "left"}}/>
                    <NavText sx={{width: "60px", marginTop: "2px"}}>책장</NavText>
                </IconNavigationButton>


                <Box sx={{flexGrow: 1}}></Box>
                <IconNavigationButton url="/book/myList">
                    <ArrowBackIcon sx={{fontSize: 40}}/>
                </IconNavigationButton>
            </TopAppBar>
        </>
    )
}

export default BookShelfMyListPage;