import React, {useState} from 'react';

import { Box, Paper, InputBase } from "@mui/material";
import SendIcon from '@mui/icons-material/Send';

const AddCommentForm = ({onClickAddCommentButton}) => {
    const [commentText, setCommentText] = useState("")

    const handleOnClickAddCommentButton = () => {
        onClickAddCommentButton(commentText)
        setCommentText("")
    }

    return (
        <Paper component="form" sx={{width: "100%", height: "35px", marginTop: "2px",}}>
            <InputBase sx={{marginLeft: "10px", width: "797px"}} value={commentText} onChange={(e)=>{setCommentText(e.target.value)}}/>
            <Box onClick={()=>{handleOnClickAddCommentButton()}} 
                sx={{
                    marginLeft: "10px", float:"right", height: "35px", minHeight: "35px", width: "35px", minWidth: "35px",
                    borderRadius: "0px 5px 5px 0px", backgroundColor: "cornflowerblue", "&:hover": {opacity: 0.80}, color: "white",
                    cursor: "pointer"
            }}>
                <SendIcon sx={{
                        float:"right", height: "30px", minHeight: "30px", width: "30px", minWidth: "30px",
                        marginTop: "2.5px", marginRight: "2.5px"
                }}></SendIcon>
            </Box>
        </Paper>
    )
}

export default AddCommentForm;