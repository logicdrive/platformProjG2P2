import React, {useState} from 'react';

import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack, Box } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';

import StyledTextField from '../../_global/components/textField/StyledTextField';

const EditIndexNameButton = ({onClickEditButton, defaultTitle, ...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [title, setTitle] = useState(defaultTitle)

  const onClickEditButtonHandle = () => {
    onClickEditButton(title);
  }

  return (
    <>
    <Box onClick={()=>{setIsDialogOpend(true);setTitle(defaultTitle)}} sx={{float: "right", cursor: "pointer", "&:hover": {opacity: 0.80}}} {...props}>
        <EditIcon sx={{color: "gray"}}/>
    </Box>

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>목차 제목 편집</DialogTitle>
      <DialogContent>
        <Stack>
            <StyledTextField
                name="title"
                label="제목"

                margin="normal"
                fullWidth

                sx={{width: 400}}
                value={title}

                onChange={(e)=>{setTitle(e.target.value)}}
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

export default EditIndexNameButton;