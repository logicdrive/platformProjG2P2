import React from 'react';
import { useNavigate } from 'react-router-dom';

import { Box } from "@mui/material";
import ArrowBackIosIcon from '@mui/icons-material/ArrowBackIos';
import ArrowForwardIosIcon from '@mui/icons-material/ArrowForwardIos';

import NavText from '../../_global/components/text/NavText';

const IndexMoveButtons = ({bookId, indexId, indexCount}) => {
    const navigate = useNavigate();
    indexId = Number(indexId);
    
    return (
        <Box sx={{"display": "center", "justifyContent": "center"}}>
            {
                (indexId === 1) ? (null) : (
                    <Box onClick={()=>{navigate(`/book/read/${bookId}/${indexId-1}`)}}sx={{float: "left", backgroundColor: "cornflowerblue", width: "100px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                        <ArrowBackIosIcon sx={{float: "left", color: "white"}}/>
                        <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>이전 목차</NavText>
                    </Box>
                )
            }

            {
                (indexId === indexCount) ? (null) : (
                    <Box onClick={()=>{navigate(`/book/read/${bookId}/${indexId+1}`)}}sx={{marginLeft: "10px", float: "left", backgroundColor: "cornflowerblue", width: "105px", height: "25px", paddingY: "8px", paddingLeft: "12px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                        <NavText sx={{float: "left", marginTop: "2px", marginRight: "10px"}}>다음 목차</NavText>
                        <ArrowForwardIosIcon sx={{float: "left", color: "white"}}/>
                    </Box>   
                ) 
            }
        </Box>
    )
}

export default IndexMoveButtons;