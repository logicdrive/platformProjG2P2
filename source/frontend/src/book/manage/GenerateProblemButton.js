import React, {useState} from 'react';

import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack, Box } from '@mui/material';
import QuestionMarkIcon from '@mui/icons-material/QuestionMark';

import StyledTextField from '../../_global/components/textField/StyledTextField';

const GenerateProblemButton = ({isGenerated, onClickGenerateButton, defaultQuery, ...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [query, setQuery] = useState(defaultQuery)

  const onClickGenerateButtonHandle = () => {
    onClickGenerateButton(query);
  }

  return (
    <>
    <Box onClick={()=>{setIsDialogOpend(true);setQuery(defaultQuery)}} sx={{float: "right", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
        <QuestionMarkIcon sx={((isGenerated) ? {color: "gray"} : {color: "white"})}/>
    </Box>

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>문제 생성</DialogTitle>
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

export default GenerateProblemButton;