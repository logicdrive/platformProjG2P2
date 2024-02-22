import React, { useContext, useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";
import { Box, Card, Stack } from '@mui/material';
import ArrowBackIcon from '@mui/icons-material/ArrowBack';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext'
import { JwtTokenContext } from "../../_global/provider/jwtToken/JwtTokenContext";

import TopAppBar from '../../_global/components/TopAppBar';
import BoldText from '../../_global/components/text/BoldText';
import IconNavigationButton from '../../_global/components/button/IconNavigationButton';
import StyledTextField from '../../_global/components/textField/StyledTextField';
import StyledContainedButton from '../../_global/components/button/StyledContainedButton';
import StyledTextButton from '../../_global/components/button/StyledTextButton';

import UserProxy from '../../_global/proxy/UserProxy';

const UserSignUpPage = () => {
    const {addAlertPopUp} = useContext(AlertPopupContext);
    const {jwtTokenState} = useContext(JwtTokenContext);
    const navigate = useNavigate();

    useEffect(() => {
        if(jwtTokenState.jwtToken !== null) {
            navigate("/book/myList");
        }
    }, [jwtTokenState.jwtToken, navigate])


    const [signUpInfo, setSignUpInfo] = useState({
        "email": "", "password": "", "name": ""
    })

    const handleSignUpInfoChange = (event) => {
        const { name, value } = event.target;
        setSignUpInfo((prevData) => ({
            ...prevData,
            [name]: value,
        }));
    }


    const onSubmitSignUp = async () => {
        try {

            await UserProxy.signUp(signUpInfo.email, signUpInfo.password, signUpInfo.name);
            addAlertPopUp("회원가입이 정상적으로 수행되었습니다.", "success");
            navigate("/user/signIn");

        } catch(error) {
            addAlertPopUp("회원가입 도중에 오류가 발생했습니다!", "error");
            console.error("회원가입 도중에 오류가 발생했습니다!", error);
        }
    }


    setTestAutomationCommands(setSignUpInfo);
    return (
        <>
            <TopAppBar title="BOOKGEN" titleLink="/user/signIn">
                <Box sx={{flexGrow: 1}}></Box>
                
                <IconNavigationButton url="/user/signIn">
                    <ArrowBackIcon sx={{fontSize: 40}}/>
                </IconNavigationButton>
            </TopAppBar>
            
            <Box>
                <Card variant="outlined" sx={{ marginX: "auto", marginTop: 3, textAlign: "center", width: "500px" }}>
                    <Stack>
                        <BoldText sx={{fontSize: 25, marginTop: 5}}>회원가입</BoldText>


                        <StyledTextField
                            label="이메일"
                            name="email"
                            type="email"
                            sx={{marginTop: 10, width: 400, marginX: "auto"}}

                            value={signUpInfo.email}
                            onChange={handleSignUpInfoChange}
                        />

                        <StyledTextField
                            label="비밀번호"
                            name="password"
                            type="password"
                            sx={{marginTop: 3, width: 400, marginX: "auto"}}

                            value={signUpInfo.password}
                            onChange={handleSignUpInfoChange}
                        />

                        <StyledTextField
                            label="닉네임"
                            name="name"
                            type="text"
                            sx={{marginTop: 3, width: 400, marginX: "auto"}}

                            value={signUpInfo.name}
                            onChange={handleSignUpInfoChange}
                        />

                        <StyledContainedButton onClick={onSubmitSignUp} variant="contained" sx={{marginTop: 5, width: 400, marginX: "auto"}}>회원가입</StyledContainedButton>
                        <StyledTextButton onClick={() => {navigate("/user/signIn")}} variant="text" sx={{marginTop: 3, marginBottom: 2, width: 400, marginX: "auto"}}>돌아가기</StyledTextButton>
                    </Stack>
                </Card>
            </Box>
        </>
    )
}

function setTestAutomationCommands(setSignUpInfo) {
    window.onkeydown = (e) => {
        if((e.code === "Digit1") && e.altKey)
        {
            setSignUpInfo({
                "email": "testemail1@gmail.com",
                "password": "testpassword1",
                "name": "testname1"
            })
        }
    }
}

export default UserSignUpPage;