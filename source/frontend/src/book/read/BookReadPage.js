import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { Divider, Box, Stack } from "@mui/material";
import ListIcon from '@mui/icons-material/List';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BoldText from '../../_global/components/text/BoldText';
import NormalText from '../../_global/components/text/NormalText';

const BookReadPage = () => {
    const {bookId, indexId} = useParams()
    console.log("BookId :", bookId, "IndexId :", indexId)

    const navigate = useNavigate();

    return (
        <>
            <MainNavAppBar focusedIndex={0} backArrowUrl={-1}/>

            <BoldText sx={{fontSize: "25px", marginTop: "10px"}}>Python</BoldText>
            <Divider sx={{marginTop: "5px"}}/>

            <Box sx={{display: "flex"}}>
                <Stack sx={{float: "left", width: "300px"}}>
                    <Box sx={{cursor: "context-menu"}}>
                        <ListIcon sx={{float: "left", paddingTop: "2px"}}/>
                        <NormalText sx={{fontSize: "20px", float: "left"}}>목차</NormalText>
                    </Box>
                    
                    <Stack
                        spacing={0.5}
                        sx={{marginTop: "1px", marginLeft: "10px"}}
                    >
                        <NormalText onClick={()=>{navigate("/book/read/1/1")}} sx={{fontSize: "15px", "&:hover": {opacity: 0.50}, cursor: "pointer", color: "cornflowerblue"}}>1. Python 소개</NormalText>
                        <NormalText onClick={()=>{navigate("/book/read/1/2")}} sx={{fontSize: "15px", "&:hover": {opacity: 0.50}, cursor: "pointer"}}>2. Python 기초 문법</NormalText>
                    </Stack>
                </Stack>

                <Divider orientation="vertical" flexItem/>
                <Box sx={{float: "left", flex: "1 0 auto", padding: "5px"}}>
                    1
                </Box>
            </Box>
        </>
    )
}

export default BookReadPage;