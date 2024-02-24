import React, {useState} from 'react';

import { Stack, Box, IconButton } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';

import BoldText from '../../_global/components/text/BoldText';
import NormalText from '../../_global/components/text/NormalText';
import EditCommentButton from './EditCommentButton';

const CommentInfoBox = ({rawCommentInfo}) => {
    const [commentInfo] = useState({
        id: rawCommentInfo.id,
        creator: rawCommentInfo.creator,
        createdDate: rawCommentInfo.createdDate,
        content: rawCommentInfo.content
    })

    const onClickEditCommentButton = (comment) => {
        alert(comment)
    }

    return (
        <Stack>
            <Box>
                <BoldText sx={{float: "left", fontSize: "15px"}}>{commentInfo.creator}</BoldText>
                
                
                <IconButton sx={{float: "right", position: "relative", bottom: "5px"}} onClick={(e)=>{e.stopPropagation(); alert("Delete")}}>
                    <DeleteIcon sx={{fontSize: "15px"}}/> 
                </IconButton>
                <EditCommentButton onClickEditButton={onClickEditCommentButton} defaultComment={commentInfo.content}/>

                <NormalText sx={{float: "right", fontSize: "15px"}}>{commentInfo.createdDate}</NormalText>
            </Box>

            <NormalText sx={{fontSize: "15px"}}>{commentInfo.content}</NormalText>
        </Stack>
    )
}

export default CommentInfoBox;