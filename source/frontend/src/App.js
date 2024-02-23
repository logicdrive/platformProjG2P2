import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Container } from "@mui/material";

import { AlertPopupProvider } from "./_global/provider/alertPopUp/AlertPopUpContext";
import { JwtTokenProvider } from "./_global/provider/jwtToken/JwtTokenContext";

import AlertPopUpList from './_global/provider/alertPopUp/AlertPopUpList';

import UserSignInPage from './user/signIn/UserSignInPage';
import UserSignUpPage from './user/signUp/UserSignUpPage';

import BookMyListPage from './book/myList/BookMyListPage';
import BookSharedListPage from './book/sharedList/BookSharedListPage';
import BookInfoPage from './book/info/BookInfoPage';
import BookReadPage from './book/read/BookReadPage';
import BookManagePage from './book/manage/BookManagePage';

import BookShelfMyListPage from './bookShelf/myList/BookShelfMyListPage';
import BookShelfSharedListPage from './bookShelf/sharedList/BookShelfSharedListPage';
import BookShelfInfoPage from './bookShelf/info/BookShelfInfoPage';

function App() {
  return (
    <AlertPopupProvider>
      <JwtTokenProvider>
        <Container maxWidth="lg">
            <Router>
              <Routes>
                    <Route path="/" element={<UserSignInPage/>}/>

                    <Route path="/user/signIn" element={<UserSignInPage/>}/>
                    <Route path="/user/signUp" element={<UserSignUpPage/>}/>

                    <Route path="/book/myList" element={<BookMyListPage/>}/>
                    <Route path="/book/sharedList" element={<BookSharedListPage/>}/>
                    <Route path="/book/info/:bookId" element={<BookInfoPage/>}/>
                    <Route path="/book/read/:bookId/:indexId" element={<BookReadPage/>}/>
                    <Route path="/book/manage" element={<BookManagePage/>}/>
                    
                    <Route path="/bookShelf/myList" element={<BookShelfMyListPage/>}/>
                    <Route path="/bookShelf/sharedList" element={<BookShelfSharedListPage/>}/>
                    <Route path="/bookShelf/info/:bookShelfId" element={<BookShelfInfoPage/>}/>
                </Routes>
            </Router>
            <AlertPopUpList/>
          </Container>
      </JwtTokenProvider>
    </AlertPopupProvider>
  )
}

export default App;