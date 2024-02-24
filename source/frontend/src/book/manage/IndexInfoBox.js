import React, { useState } from 'react';
import { Box } from "@mui/material";
import SmartToyIcon from '@mui/icons-material/SmartToy';
import DeleteIcon from '@mui/icons-material/Delete';

import BoldText from '../../_global/components/text/BoldText';
import YesNoButton from '../../_global/components/button/YesNoButton';
import EditIndexNameButton from './EditIndexNameButton';

const IndexInfoBox = ({rawIndexInfo}) => {
    const [indexInfo] = useState({
        id: rawIndexInfo.id,
        name: rawIndexInfo.name,
        isGenerated: rawIndexInfo.isGenerated
    })
    
    const onClickDeleteButton = () => {
        alert("Delete")
    }

    const onClickEditButton = (title) => {
        alert("Edit :" + title)
    }


    return (
        <Box sx={{backgroundColor: "lightgray", borderRadius: "5px", padding: "5px"}}>
            <BoldText sx={{fontSize: "20px", float: "left", marginTop: "2px", marginLeft: "3px"}}>{indexInfo.name}</BoldText>
            
            <Box sx={{marginTop: "3px"}}>
                <Box sx={{float: "right", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                    <YesNoButton onClickYes={onClickDeleteButton} title="해당 목차를 삭제시키겠습니까?">
                        <DeleteIcon sx={{float: "left", color: "gray"}}/>
                    </YesNoButton>
                </Box>
                
                <EditIndexNameButton onClickEditButton={onClickEditButton} defaultTitle={indexInfo.name}/>
                <Box onClick={()=>{alert("Gen")}} sx={{float: "right", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                    <SmartToyIcon sx={((indexInfo.isGenerated) ? {color: "gray"} : {color: "white"})}/>
                </Box>
            </Box>
        </Box>
    )
}

export default IndexInfoBox;