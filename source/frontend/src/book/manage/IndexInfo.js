import React from 'react';
import { Box } from "@mui/material";
import EditIcon from '@mui/icons-material/Edit';
import SmartToyIcon from '@mui/icons-material/SmartToy';
import DeleteIcon from '@mui/icons-material/Delete';

import BoldText from '../../_global/components/text/BoldText';

const IndexInfo = ({indexId, indexName, isGeneated}) => {
    return (
        <Box sx={{backgroundColor: "lightgray", borderRadius: "5px", padding: "5px"}}>
            <BoldText sx={{fontSize: "20px", float: "left", marginTop: "2px", marginLeft: "3px"}}>{indexName}</BoldText>
            
            <Box sx={{marginTop: "3px"}}>
                <Box onClick={()=>{alert("Delete")}} sx={{float: "right", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                    <DeleteIcon sx={{color: "gray"}}/>
                </Box>
                <Box onClick={()=>{alert("Edit")}} sx={{float: "right", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                    <EditIcon sx={{color: "gray"}}/>
                </Box>
                <Box onClick={()=>{alert("Gen")}} sx={{float: "right", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                    <SmartToyIcon sx={((isGeneated) ? {color: "gray"} : {color: "white"})}/>
                </Box>
            </Box>
        </Box>
    )
}

export default IndexInfo;