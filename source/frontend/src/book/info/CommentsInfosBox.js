import React from 'react';

import { Divider, Stack } from "@mui/material";
import CommentInfoBox from './CommentInfoBox';

const CommentsInfosBox = ({rawCommentInfos, setIsBackdropOpened}) => {
    return (
        <>
            <Stack
                divider={<Divider flexItem/>}
                spacing={3}
                sx={{marginTop: "20px"}}
            >
                {
                    rawCommentInfos.map((rawCommentInfo, index) => {
                        return (
                            <CommentInfoBox key={index} rawCommentInfo={rawCommentInfo} setIsBackdropOpened={setIsBackdropOpened}/>   
                        )
                    })
                }
            </Stack>
        </>
    )
}

export default CommentsInfosBox;