import React, {useState} from 'react';

import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack, IconButton } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';

import StyledTextField from '../../_global/components/textField/StyledTextField';

const EditCommentButton = ({onClickEditButton, defaultComment, ...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [comment, setComment] = useState(defaultComment)

  const onClickEditButtonHandle = () => {
    onClickEditButton(comment);
  }

  return (
    <>
      <IconButton onClick={(e)=>{setIsDialogOpend(true);setComment(defaultComment)}} sx={{float: "right", position: "relative", bottom: "5px"}} {...props}>
          <EditIcon sx={{fontSize: "15px"}}/> 
      </IconButton>

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>댓글 편집</DialogTitle>
      <DialogContent>
        <Stack>
            <StyledTextField
                name="comment"
                label="댓글"

                margin="normal"
                fullWidth

                sx={{width: 400}}
                value={comment}

                onChange={(e)=>{setComment(e.target.value)}}
            />
        </Stack>
      </DialogContent>

      <DialogActions>
          <Button onClick={() => {
            setIsDialogOpend(false);
          }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>닫기</Button>
          <Button onClick={() => {
            onClickEditButtonHandle();
            setIsDialogOpend(false);
          }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>편집</Button>
      </DialogActions>
    </Dialog>
    </>
  )
}

export default EditCommentButton;