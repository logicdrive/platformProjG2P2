import React, {useState} from 'react';

import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack } from '@mui/material';

import StyledTextField from '../../_global/components/textField/StyledTextField';
import BoldText from '../../_global/components/text/BoldText';

const CreateBookShelfButton = ({onClickCreateButton, defaultTitle, ...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [title, setTitle] = useState(defaultTitle)

  const onClickCreateButtonHandle = () => {
    onClickCreateButton(title);
  }

  return (
    <>
    <BoldText onClick={()=>{setIsDialogOpend(true);setTitle(defaultTitle);}} sx={{float: "right", marginRight: "10px", fontSize: "15px", height: "30px", paddingX: "10px", paddingTop: "10px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}, color: "black"}} {...props}>책장 생성하기</BoldText>
    
    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>새로운 책장 추가</DialogTitle>
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
            onClickCreateButtonHandle();
            setIsDialogOpend(false);
          }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>생성</Button>
      </DialogActions>
    </Dialog>
    </>
  )
}

export default CreateBookShelfButton;