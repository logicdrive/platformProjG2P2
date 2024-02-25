import React, {useState} from 'react';

import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack, Box } from '@mui/material';
import EditIcon from '@mui/icons-material/Edit';

import StyledTextField from '../../_global/components/textField/StyledTextField';
import NavText from '../../_global/components/text/NavText';

const EditBookTitleButton = ({onClickEditButton, defaultTitle, ...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [title, setTitle] = useState(defaultTitle)

  const onClickEditButtonHandle = () => {
    onClickEditButton(title);
  }

  setTestAutomationCommands(isDialogOpend, setTitle)
  return (
    <>
    <Box onClick={()=>{setIsDialogOpend(true);setTitle(defaultTitle)}} sx={{float: "right", backgroundColor: "cornflowerblue", width: "63px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}} {...props}>
        <EditIcon sx={{float: "left", color: "white"}}/>
        <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>편집</NavText>
    </Box> 

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>책 제목 편집</DialogTitle>
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

function setTestAutomationCommands(isDialogOpend, setTitle) {
  window.onkeydown = (e) => {
      if(!e || !e.code) return
      if(!isDialogOpend) return

      if(e.code.startsWith("Digit1") && e.altKey)
      {
        setTitle("Python")
      }
  }
}

export default EditBookTitleButton;