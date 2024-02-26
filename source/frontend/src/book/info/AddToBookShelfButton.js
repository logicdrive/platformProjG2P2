import React, { useState, useContext, useEffect } from 'react';

import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack, Select, MenuItem, Box } from '@mui/material';
import CollectionsBookmarkIcon from '@mui/icons-material/CollectionsBookmark';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from '../../_global/provider/jwtToken/JwtTokenContext';
import NavText from '../../_global/components/text/NavText';
import BookShelfProxy from '../../_global/proxy/BookShelfProxy';

const AddToBookShelfButton = ({onClickAddButton, ...props}) => {
  const {addAlertPopUp} = useContext(AlertPopupContext)
  const {jwtTokenState} = useContext(JwtTokenContext)
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [selectedBookShelfId, setSelectedBookShelfId] = useState(1);

  const [bookShelfInfos, setBookShelfInfos] = useState([])
  useEffect(() => {
    (async () => {
      try {
        const res = await BookShelfProxy.searchBookShelfAllByCreaterId(jwtTokenState.jwtToken.id, 0, 1000)
        setBookShelfInfos(res._embedded.bookShelfs.map((bookShelfInfo) => {
          return {
            id: bookShelfInfo.bookShelfId,
            title: bookShelfInfo.title
          }
        }))
      } catch(error) {
        addAlertPopUp("책장 목록을 불러오는 도중에 오류가 발생했습니다!", "error")
        console.error("책장 목록을 불러오는 도중에 오류가 발생했습니다!", error)
      }
    })()
  }, [addAlertPopUp, jwtTokenState])


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