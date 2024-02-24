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

export default AddIndexNameButton;