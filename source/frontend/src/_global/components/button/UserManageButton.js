import React, { useState, useContext } from 'react';
import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack, Backdrop, CircularProgress } from '@mui/material';
import ManageAccountsIcon from '@mui/icons-material/ManageAccounts';

import { AlertPopupContext } from '../../provider/alertPopUp/AlertPopUpContext';
import { JwtTokenContext } from '../../provider/jwtToken/JwtTokenContext';

import StyledTextField from '../textField/StyledTextField';
import IconButton from './IconButton';
import UserProxy from '../../proxy/UserProxy';
import SubscribeMessageCreatedSocket from '../../socket/EventHandlerSocket';
import StringTool from '../../tool/StringTool';

const UserManageButton = ({...props}) => {
  const {addAlertPopUp} = useContext(AlertPopupContext)
  const {jwtTokenState} = useContext(JwtTokenContext)
  const [isDialogOpend, setIsDialogOpend] = useState(false)
  const [isBackdropOpened, setIsBackdropOpened] = useState(false)
  const [userName, setUserName] = useState("")
  

  const onClickDialogOpenButton = async () => {
    try {

        if(jwtTokenState.jwtToken === null) {
          addAlertPopUp("유저 토큰 정보가 없습니다! 다시 로그인해주세요.", "error")
          return
        }

        const userData = await UserProxy.searchUserOneByUserId(jwtTokenState.jwtToken.id)
        setUserName(userData.name);

        setIsDialogOpend(true);

      } catch(error) {
        addAlertPopUp("유저 정보를 불러오는 도중에 오류가 발생했습니다!", "error")
        console.error("유저 정보를 불러오는 도중에 오류가 발생했습니다!", error)
    }
  }


  const onClickSaveButton = async () => {
    try {

        setIsDialogOpend(false)
        setIsBackdropOpened(true)
        await UserProxy.updateName(userName)

    } catch(error) {
        addAlertPopUp("유저 정보 수정 도중에 오류가 발생했습니다!", "error")
        console.error("유저 정보 수정 도중에 오류가 발생했습니다!", error)
        setIsBackdropOpened(false)
    }
  }

  SubscribeMessageCreatedSocket(useState(() => {
      return (eventName) => {
        if(eventName === "UserNameUpdated")
        {
          addAlertPopUp("유저 정보가 정상적으로 수정되었습니다.", "success")
          setIsBackdropOpened(false)
        }
      }
  })[0])


  setTestAutomationCommands(isDialogOpend, userName, setUserName)
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
            setIsDialogOpend(false)
          }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>닫기</Button>
          <Button onClick={() => {
            onClickSaveButton()
          }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>저장</Button>
      </DialogActions>
    </Dialog>


    <Backdrop
        sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
        open={isBackdropOpened}
    >
        <CircularProgress color="inherit" />
    </Backdrop>
    </>
  )
}

function setTestAutomationCommands(isDialogOpend, userName, setUserName) {
  window.onkeydown = (e) => {
      if(!e || !e.code) return
      if(!isDialogOpend) return

      if(e.code.startsWith("Digit") && e.altKey)
      {
        setUserName(`updated${StringTool.toTitle(userName)}`)
      }
  }
}

export default UserManageButton;