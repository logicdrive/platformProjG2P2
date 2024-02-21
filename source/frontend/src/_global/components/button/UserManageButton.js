import React, { useState, useContext } from 'react';
import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack } from '@mui/material';
import ManageAccountsIcon from '@mui/icons-material/ManageAccounts';

import { AlertPopupContext } from '../../provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from '../../provider/jwtToken/JwtTokenContext';

import StyledTextField from '../textField/StyledTextField';
import IconButton from './IconButton';

import UserProxy from '../../proxy/UserProxy';

const UserManageButton = ({...props}) => {
  const {addAlertPopUp} = useContext(AlertPopupContext);
  const {jwtTokenState} = useContext(JwtTokenContext);
  const [isDialogOpend, setIsDialogOpend] = useState(false);
  const [userName, setUserName] = useState("")
  

  const onClickDialogOpenButton = async () => {
    try {

        const userDate = await UserProxy.searchUserOneByUserId(jwtTokenState.jwtToken.id, jwtTokenState);

        setUserName(userDate.name);
        setIsDialogOpend(true);

      } catch(error) {
        addAlertPopUp("유저 정보를 불러오는 도중에 오류가 발생했습니다!", "error");
        console.error("유저 정보를 불러오는 도중에 오류가 발생했습니다!", error);
    }
  }

  const onClickSaveButton = async () => {
    try {

        await UserProxy.updateName(userName, jwtTokenState);
        addAlertPopUp("유저 정보가 정상적으로 수행되었습니다.", "success");

    } catch(error) {
        addAlertPopUp("유저 정보 수정 도중에 오류가 발생했습니다!", "error");
        console.error("유저 정보 수정 도중에 오류가 발생했습니다!", error);
    }
  }


  return (
    <>
    <IconButton onClick={onClickDialogOpenButton} {...props}>
      <ManageAccountsIcon sx={{fontSize: 35, paddingTop: 0.3, paddingLeft: 0.3}}/>
    </IconButton>

    <Dialog open={isDialogOpend} onClose={()=>{setIsDialogOpend(false);}}>
      <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>사용자 정보 수정</DialogTitle>
      <DialogContent>
        <Stack>
          <StyledTextField
                name="userName"
                label="유저명"

                margin="normal"
                fullWidth

                sx={{width: 400}}
                value={userName}

                onChange={(e)=>{setUserName(e.target.value)}}
            />
        </Stack>
      </DialogContent>

      <DialogActions>
          <Button onClick={() => {
            setIsDialogOpend(false);
          }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>닫기</Button>
          <Button onClick={() => {
            onClickSaveButton();
            setIsDialogOpend(false);
          }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>저장</Button>
      </DialogActions>
    </Dialog>
    </>
  )
}

export default UserManageButton;