import React, {useState} from 'react';

import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack, Select, MenuItem, Box } from '@mui/material';
import CollectionsBookmarkIcon from '@mui/icons-material/CollectionsBookmark';

import NavText from '../../_global/components/text/NavText';

const AddToBookShelfButton = ({onClickAddButton, ...props}) => {
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [selectedBookShelfId, setSelectedBookShelfId] = useState(1);

  const [bookShelfInfos] = useState([
    {id: 1, title: "책장1"},
    {id: 2, title: "책장2"},
    {id: 3, title: "책장3"}
  ])


  const onClickAddButtonHandle = () => {
    onClickAddButton(selectedBookShelfId);
  }

  return (
    <>
    <Box onClick={()=>{setIsDialogOpend(true);setSelectedBookShelfId(1);}} sx={{float: "left", marginLeft: "5px", backgroundColor: "cornflowerblue", width: "118px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}} {...props}>
        <CollectionsBookmarkIcon sx={{float: "left", color: "white"}}/>
        <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>책장에 추가</NavText>
    </Box>

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>책장에 추가</DialogTitle>
      <DialogContent>
        <Stack>

          <Select
            sx={{
                height: "35px", paddingLeft: "10px", width: "400px"
            }}
            value={selectedBookShelfId}
            label="책장 목록"
            name="책장 목록"
            onChange={(e)=>{setSelectedBookShelfId(e.target.value)}}
            variant="standard"
          >
            {
              bookShelfInfos.map((bookShelfInfo)=>(
                <MenuItem key={bookShelfInfo.id} value={bookShelfInfo.id}>{bookShelfInfo.title}</MenuItem>
              ))
            }
          </Select>

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

export default AddToBookShelfButton;