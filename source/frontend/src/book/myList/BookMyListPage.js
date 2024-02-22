import React from 'react';
import { Box } from "@mui/material";
import LogoutIcon from '@mui/icons-material/Logout';
import BookIcon from '@mui/icons-material/Book';
import CollectionsBookmarkIcon from '@mui/icons-material/CollectionsBookmark';

import TopAppBar from '../../_global/components/TopAppBar';
import YesNoButton from '../../_global/components/button/YesNoButton';
import IconButton from '../../_global/components/button/IconButton';
import IconNavigationButton from '../../_global/components/button/IconNavigationButton';
import NavText from '../../_global/components/text/NavText';
import BoldText from '../../_global/components/text/BoldText';

const BookMyListPage = () => {
    return (
        <>
            <TopAppBar title="BOOKGEN" titleLink="/book/myList">
                <IconNavigationButton url="/book/myList" sx={{marginLeft: "20px", marginBottom: "10px"}} buttonSx={{width: "75px"}} textSx={{width: "75px"}}>
                    <BookIcon sx={{float: "left", color: "cornflowerblue"}}/>
                    <NavText sx={{width: "75px", marginTop: "2px", color: "cornflowerblue"}}>전자책</NavText>
                </IconNavigationButton>

                <IconNavigationButton url="/bookShelf/myList" sx={{marginLeft: "15px", marginBottom: "10px"}} buttonSx={{width: "60px"}} textSx={{width: "60px"}}>
                    <CollectionsBookmarkIcon sx={{float: "left"}}/>
                    <NavText sx={{width: "60px", marginTop: "2px"}}>책장</NavText>
                </IconNavigationButton>


                <Box sx={{flexGrow: 1}}></Box>
                <YesNoButton onClickYes={() => {}} title="정말로 로그아웃 하시겠습니까?">
                    <IconButton>
                        <LogoutIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
                    </IconButton>
                </YesNoButton>
            </TopAppBar>

            <Box sx={{width: "100%", height: "50px", padding: "10px", marginTop: "5px"}}>
                <BoldText sx={{float: "left", fontSize: "15px", backgroundColor: "royalblue", width: "100px", height: "30px", paddingLeft: "10px", paddingRight: "7px", paddingTop: "10px", borderRadius: "5px", color: "white", cursor: "pointer", "&:hover": {opacity: 0.80}}}>내가 생성한 책</BoldText>
                <BoldText sx={{float: "left", marginLeft: "5px", fontSize: "15px", width: "100px", height: "30px", paddingLeft: "10px", paddingRight: "7px", paddingTop: "10px", borderRadius: "5px", color: "black", cursor: "pointer", "&:hover": {opacity: 0.80}}}>공유된 책</BoldText>
                <BoldText sx={{float: "right", marginLeft: "5px", fontSize: "15px", width: "100px", height: "30px", paddingLeft: "10px", paddingRight: "7px", paddingTop: "10px", borderRadius: "5px", color: "black", cursor: "pointer", "&:hover": {opacity: 0.80}}}>책 생성하기</BoldText>
            </Box>
        </>
    )
}

export default BookMyListPage;