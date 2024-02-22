import React from 'react';
import { useNavigate } from "react-router-dom";
import { Box } from "@mui/material";
import BoldText from '../../_global/components/text/BoldText';

const BookShelfSubAppBar = ({focusedIndex, sx, ...props}) => {
    const navigate = useNavigate()

    let commonSx = {fontSize: "15px", height: "30px", paddingX: "10px", paddingTop: "10px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}
    let focusedSx = {color: "white", backgroundColor: "royalblue", ...commonSx}
    let normalSx = {color: "black", ...commonSx}

    return (
        <>
            <Box sx={{width: "100%", height: "50px", padding: "10px", marginTop: "5px", ...sx}} {...props}>
                <BoldText onClick={()=>{navigate("/bookShelf/myList")}} sx={(focusedIndex === 0) ? ({float: "left", ...focusedSx}) : ({float: "left", ...normalSx})}>내가 생성한 책장</BoldText>
                <BoldText onClick={()=>{navigate("/bookShelf/sharedList")}} sx={(focusedIndex === 1) ? ({float: "left", marginLeft: "5px", ...focusedSx}) : ({float: "left", marginLeft: "5px", ...normalSx})}>공유된 책장</BoldText>
                <BoldText onClick={()=>{}} sx={(focusedIndex === 2) ? ({float: "right", marginRight: "10px", ...focusedSx}) : ({float: "right", marginRight: "10px", ...normalSx})}>책장 생성하기</BoldText>
            </Box>
        </>
    )
}

export default BookShelfSubAppBar;