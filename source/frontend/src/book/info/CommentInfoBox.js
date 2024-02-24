import React, {useState} from 'react';

import { Stack, Box, IconButton } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';

import BoldText from '../../_global/components/text/BoldText';
import NormalText from '../../_global/components/text/NormalText';
import EditCommentButton from './EditCommentButton';
import YesNoButton from '../../_global/components/button/YesNoButton';

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

    const onClickDeleteCommentButton = () => {
        alert("Delete")
    }

    return (
        <Stack>
            <Box>
                <BoldText sx={{float: "left", fontSize: "15px"}}>{commentInfo.creator}</BoldText>
                
                
                <YesNoButton onClickYes={onClickDeleteCommentButton} title="해당 댓글을 삭제하시겠습니까?">
                    <IconButton sx={{float: "right", position: "relative", bottom: "5px"}}>
                        <DeleteIcon sx={{fontSize: "15px"}}/> 
                    </IconButton>
                </YesNoButton>
                <EditCommentButton onClickEditButton={onClickEditCommentButton} defaultComment={commentInfo.content}/>

                <NormalText sx={{float: "right", fontSize: "15px"}}>{commentInfo.createdDate}</NormalText>
            </Box>

            <NormalText sx={{fontSize: "15px"}}>{commentInfo.content}</NormalText>
        </Stack>
    )
}

export default CommentInfoBox;