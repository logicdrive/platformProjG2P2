import React from 'react';
import { Box } from "@mui/material";
import LogoutIcon from '@mui/icons-material/Logout';
import BookIcon from '@mui/icons-material/Book';
import CollectionsBookmarkIcon from '@mui/icons-material/CollectionsBookmark';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';

import TopAppBar from '../../_global/components/TopAppBar';
import YesNoButton from '../../_global/components/button/YesNoButton';
import IconButton from '../../_global/components/button/IconButton';
import IconNavigationButton from '../../_global/components/button/IconNavigationButton';
import NavText from '../../_global/components/text/NavText';

const MainNavAppBar = ({focusedIndex, backArrowUrl, ...props}) => {
    return (
        <TopAppBar title="BOOKGEN" titleLink="/book/myList" {...props}>
            <IconNavigationButton url="/book/myList" sx={{marginLeft: "20px", marginBottom: "10px"}} buttonSx={{width: "75px"}} textSx={{width: "75px"}}>
                <BookIcon sx={(focusedIndex === 0) ? ({float: "left", color: "cornflowerblue"}) : ({float: "left"})}/>
                <NavText sx={(focusedIndex === 0) ? ({width: "75px", marginTop: "2px", color: "cornflowerblue"}) : ({width: "75px", marginTop: "2px"})}>전자책</NavText>
            </IconNavigationButton>

            <IconNavigationButton url="/bookShelf/myList" sx={{marginLeft: "15px", marginBottom: "10px"}} buttonSx={{width: "60px"}} textSx={{width: "60px"}}>
                <CollectionsBookmarkIcon sx={(focusedIndex === 1) ? ({float: "left", color: "cornflowerblue"}) : ({float: "left"})}/>
                <NavText sx={(focusedIndex === 1) ? ({width: "60px", marginTop: "2px", color: "cornflowerblue"}) : ({width: "60px", marginTop: "2px"})}>책장</NavText>
            </IconNavigationButton>


            <Box sx={{flexGrow: 1}}></Box>
            {
                (backArrowUrl) ? (
                    <IconNavigationButton url={backArrowUrl}>
                        <ArrowBackIcon sx={{fontSize: 40}}/>
                    </IconNavigationButton>
                ) : (
                    <YesNoButton onClickYes={() => {}} title="정말로 로그아웃 하시겠습니까?">
                        <IconButton>
                            <LogoutIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
                        </IconButton>
                    </YesNoButton>
                )
            }
        </TopAppBar>
    )
}

export default MainNavAppBar;