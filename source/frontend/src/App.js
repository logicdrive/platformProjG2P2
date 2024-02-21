import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Container } from "@mui/material";

import { AlertPopupProvider } from "./_global/provider/alertPopUp/AlertPopUpContext";
import { JwtTokenProvider } from "./_global/provider/jwtToken/JwtTokenContext";

import AlertPopUpList from './_global/provider/alertPopUp/AlertPopUpList';

import UserSignInPage from './user/signIn/UserSignInPage';
import UserSignUpPage from './user/signUp/UserSignUpPage';

function App() {
  return (
    <AlertPopupProvider>
      <JwtTokenProvider>
        <Container maxWidth="sm">
            <Router>
              <Routes>
                    <Route path="/" element={<UserSignInPage/>}/>

                    <Route path="/user/signIn" element={<UserSignInPage/>}/>
                    <Route path="/user/signUp" element={<UserSignUpPage/>}/>
                </Routes>
            </Router>
            <AlertPopUpList/>
          </Container>
      </JwtTokenProvider>
    </AlertPopupProvider>
  )
}

export default App;