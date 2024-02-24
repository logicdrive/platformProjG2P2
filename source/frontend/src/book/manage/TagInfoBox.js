import React, { useState } from 'react';
import { Box } from "@mui/material";
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';

import BoldText from '../../_global/components/text/BoldText';
import YesNoButton from '../../_global/components/button/YesNoButton';

const TagInfoBox = ({rawTagInfo}) => {
    const [tagInfo] = useState({
        id: rawTagInfo.id,
        name: rawTagInfo.name
    })

    const onClickDeleteButton = () => {
        alert("Delete")
    }

    return (
        <Box sx={{display: "flex", flexDirection: "row", width: "630px", flexWrap: "wrap", marginTop: "10px", marginLeft: "-3px"}}>
            <Box sx={{margin: "5px", backgroundColor: "lightgray", padding: "5px", borderRadius: "5px"}}>
                <BoldText sx={{fontSize: "20px", display: "inline-block", color: "black", borderRadius: "5px", marginTop: "4px", marginLeft: "4px", cursor: "context-menu"}}>{tagInfo.name}</BoldText>

                <Box sx={{marginTop: "5px", float: "right", width: "25px", height: "25px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                    <YesNoButton onClickYes={onClickDeleteButton} title="해당 태그를 삭제시키겠습니까?">
                            <DeleteIcon sx={{float: "left", color: "gray"}}/>
                    </YesNoButton>
                </Box>
                <Box onClick={()=>{alert("Edit")}} sx={{marginLeft: "20px", marginTop: "5px", float: "right", width: "25px", height: "25px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                    <EditIcon sx={{float: "left", color: "gray"}}/>
                </Box>
            </Box>
        </Box>
    )
}

export default TagInfoBox;