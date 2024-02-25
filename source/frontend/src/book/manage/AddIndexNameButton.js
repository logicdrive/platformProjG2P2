import React, {useState} from 'react';

import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack, Box } from '@mui/material';
import AddIcon from '@mui/icons-material/Add';

import StyledTextField from '../../_global/components/textField/StyledTextField';
import NavText from '../../_global/components/text/NavText';

const AddIndexNameButton = ({onClickAddButton, defaultTitle, ...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [title, setTitle] = useState(defaultTitle)

  const onClickAddButtonHandle = () => {
    onClickAddButton(title);
  }


  setTestAutomationCommands(isDialogOpend, setTitle)
  return (
    <>
    <Box onClick={()=>{setIsDialogOpend(true);setTitle(defaultTitle)}} sx={{marginRight: "5px", float: "right", backgroundColor: "cornflowerblue", width: "63px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
        <AddIcon sx={{float: "left", color: "white"}}/>
        <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>추가</NavText>
    </Box>

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>목차 추가</DialogTitle>
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
            onClickAddButtonHandle();
            setIsDialogOpend(false);
          }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>추가</Button>
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
        setTitle("Introduction to Python")
      }
      else if(e.code.startsWith("Digit2") && e.altKey)
      {
        setTitle("Python Syntax and Variables")
      }
      else if(e.code.startsWith("Digit3") && e.altKey)
      {
        setTitle("Data Types in Python")
      }
      else if(e.code.startsWith("Digit4") && e.altKey)
      {
        setTitle("Control Flow in Python")
      }
      else if(e.code.startsWith("Digit5") && e.altKey)
      {
        setTitle("Functions in Python")
      }
      else if(e.code.startsWith("Digit6") && e.altKey)
      {
        setTitle("Lists, Tuples, and Sets in Python")
      }
      else if(e.code.startsWith("Digit7") && e.altKey)
      {
        setTitle("Dictionaries in Python")
      }
      else if(e.code.startsWith("Digit8") && e.altKey)
      {
        setTitle("File Handling in Python")
      }
      else if(e.code.startsWith("Digit9") && e.altKey)
      {
        setTitle("Object-Oriented Programming in Python")
      }
      else if(e.code.startsWith("Digit0") && e.altKey)
      {
        setTitle("Exception Handling in Python")
      }
  }
}

export default AddIndexNameButton;