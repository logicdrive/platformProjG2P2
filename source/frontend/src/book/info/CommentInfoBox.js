import React, {useState, useEffect} from 'react';

import { Stack, Box, IconButton } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';

import BoldText from '../../_global/components/text/BoldText';
import NormalText from '../../_global/components/text/NormalText';
import EditCommentButton from './EditCommentButton';
import YesNoButton from '../../_global/components/button/YesNoButton';
import UserProxy from '../../_global/proxy/UserProxy';
import DictionaryTool from '../../_global/tool/DictionaryTool';
import TimeTool from '../../_global/tool/TimeTool';

const CommentInfoBox = ({rawCommentInfo}) => {
    const [commentInfo, setCommentInfo] = useState({})
    useEffect(() => {
        (async () => {
            if(DictionaryTool.isEmpty(rawCommentInfo)) return

            const createrData = await UserProxy.searchUserOneByUserId(rawCommentInfo.createrId)
            setCommentInfo({
                id: rawCommentInfo.commentId,
                creator: createrData.name,
                createdDate: TimeTool.prettyDateString(rawCommentInfo.createdDate),
                content: rawCommentInfo.content
            })
        })()
    }, [rawCommentInfo])


    const onClickEditCommentButton = (comment) => {
        alert(comment)
    }

    const onClickDeleteCommentButton = () => {
        alert("Delete")
    }


    if(!commentInfo.id) return <></>
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