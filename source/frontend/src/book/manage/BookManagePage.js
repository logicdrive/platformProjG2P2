import React from 'react';
import { Box, IconButton, Divider, Container } from "@mui/material";
import { useParams, useNavigate } from 'react-router-dom';
import DoneIcon from '@mui/icons-material/Done';
import EditIcon from '@mui/icons-material/Edit';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BoldText from '../../_global/components/text/BoldText';

const BookManagePage = () => {
    const navigate = useNavigate()
    const {bookId} = useParams()
    console.log("BookId :", bookId)

    return (
        <>
            <MainNavAppBar focusedIndex={0} backArrowUrl="/book/myList"/>

            <Container maxWidth="md" sx={{padding: "5px", marginTop: "10px"}}>
                <Box sx={{width: "100%", height: "30px", marginTop: "5px"}}>
                    <EditIcon sx={{float: "left", marginTop: "5px"}}/>
                    <BoldText sx={{float: "left", fontSize: "20px", marginTop: "5px", marginLeft: "5px"}}>책 편집</BoldText>

                    <IconButton sx={{float: "right", marginTop: "-8px", marginRight: "-5px"}} onClick={(e)=>{e.stopPropagation(); navigate("/book/myList")}}>
                        <DoneIcon sx={{fontSize: "30px", color: "black"}}/>
                        <BoldText sx={{float: "left", fontSize: "20px", marginTop: "5px"}}>완료</BoldText>
                    </IconButton>
                </Box>
                <Divider sx={{width: "100%"}}/>
            </Container>
        </>
    )
}

export default BookManagePage;