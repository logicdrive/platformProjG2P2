import React, {useState} from 'react';

import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack, Box } from '@mui/material';
import SmartToyIcon from '@mui/icons-material/SmartToy';

import StyledTextField from '../../_global/components/textField/StyledTextField';
import NavText from '../../_global/components/text/NavText';

const GenerateTagsButton = ({onClickGenerateButton, defaultQuery, ...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [query, setQuery] = useState(defaultQuery)

  const onClickGenerateButtonHandle = () => {
    onClickGenerateButton(query);
  }

  return (
    <>
    <Box onClick={()=>{setIsDialogOpend(true);setQuery(defaultQuery)}} sx={{float: "right", backgroundColor: "cornflowerblue", width: "63px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
        <SmartToyIcon sx={{float: "left", color: "white"}}/>
        <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>생성</NavText>
    </Box>

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>태그 생성</DialogTitle>
      <DialogContent>
        <Stack>
            <StyledTextField
                name="query"
                label="생성 쿼리"

                margin="normal"
                fullWidth

                sx={{width: 400}}
                value={query}

                onChange={(e)=>{setQuery(e.target.value)}}
                multiline={true}
                rows={5}
            />
        </Stack>
      </DialogContent>

      <DialogActions>
          <Button onClick={() => {
            setIsDialogOpend(false);
          }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>닫기</Button>
          <Button onClick={() => {
            onClickGenerateButtonHandle();
            setIsDialogOpend(false);
          }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>생성</Button>
      </DialogActions>
    </Dialog>
    </>
  )
}

export default GenerateTagsButton;