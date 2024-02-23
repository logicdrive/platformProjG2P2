import React from 'react';
import { Box } from "@mui/material";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

import BoldText from '../../_global/components/text/BoldText';

const TagInfo = ({tagId, tagName}) => {
    return (
        <Box sx={{display: "flex", flexDirection: "row", width: "630px", flexWrap: "wrap", marginTop: "10px", marginLeft: "-3px"}}>
            <Box sx={{margin: "5px", backgroundColor: "lightgray", padding: "5px", borderRadius: "5px"}}>
                <BoldText sx={{fontSize: "20px", display: "inline-block", color: "black", borderRadius: "5px", marginTop: "4px", marginLeft: "4px", cursor: "context-menu"}}>{tagName}</BoldText>

                <Box onClick={()=>{alert("Delete")}} sx={{marginTop: "5px", float: "right", width: "25px", height: "25px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                    <DeleteIcon sx={{float: "left", color: "gray"}}/>
                </Box>
                <Box onClick={()=>{alert("Edit")}} sx={{marginLeft: "20px", marginTop: "5px", float: "right", width: "25px", height: "25px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                    <EditIcon sx={{float: "left", color: "gray"}}/>
                </Box>
            </Box>
        </Box>
    )
}

export default TagInfo;