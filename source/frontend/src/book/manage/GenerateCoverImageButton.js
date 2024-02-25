import React from 'react';

import { Box } from '@mui/material';
import SmartToyIcon from '@mui/icons-material/SmartToy';

import NavText from '../../_global/components/text/NavText';
import YesNoButton from '../../_global/components/button/YesNoButton';

const GenerateCoverImageButton = ({onClickGenerateButton, ...props}) => {
  return (
    <>
    <YesNoButton onClickYes={onClickGenerateButton} title="책 표지를 생성시키겠습니까?\n표지 이미지는 제목을 기반으로 생성됩니다.">
      <Box sx={{float: "left", backgroundColor: "cornflowerblue", width: "63px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
          <SmartToyIcon sx={{float: "left", color: "white"}}/>
          <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>생성</NavText>
      </Box>
    </YesNoButton>
    </>
  )
}

export default GenerateCoverImageButton;