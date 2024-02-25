import React, { useState, useContext } from 'react';
import { useNavigate } from "react-router-dom";

import { Box, Paper, InputBase, MenuItem, Select, Backdrop, CircularProgress } from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import BoldText from '../../_global/components/text/BoldText';
import BookProxy from '../../_global/proxy/BookProxy';
import SubscribeMessageCreatedSocket from '../../_global/socket/EventHandlerSocket';

const BookSubAppBar = ({focusedIndex, searchTypes, handleOnSubmit, sx, ...props}) => {
    const navigate = useNavigate()
    const {addAlertPopUp} = useContext(AlertPopupContext)
    const [isBackdropOpened, setIsBackdropOpened] = useState(false)

    let commonSx = {fontSize: "15px", height: "30px", paddingX: "10px", paddingTop: "10px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}
    let focusedSx = {color: "white", backgroundColor: "royalblue", ...commonSx}
    let normalSx = {color: "black", ...commonSx}

    const [searchText, setSearchText] = useState("")
    const [searchType, setSearchType] = useState((searchTypes) ? searchTypes[0].type : "")


    const onClickCreateEmptyBookButton = async () => {
        try {

            setIsBackdropOpened(true)
            await BookProxy.createEmptyBook()
    
        } catch(error) {
            addAlertPopUp("새로운 빈 책 추가 도중에 오류가 발생했습니다!", "error")
            console.error("새로운 빈 책 추가 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }

    SubscribeMessageCreatedSocket(useState(() => {
        return (eventName, value) => {
          if(eventName === "EmptyBookCreated")
          {
            addAlertPopUp("새로운 빈 책이 추가되었습니다.", "success")
            setIsBackdropOpened(false)
            navigate(`/book/manage/${value.bookId}`)
          }
        }
    })[0])


    return (
        <>
            <Box sx={{width: "100%", height: "50px", padding: "10px", marginTop: "5px", ...sx}} {...props}>
                <BoldText onClick={()=>{navigate("/book/myList")}} sx={(focusedIndex === 0) ? ({float: "left", ...focusedSx}) : ({float: "left", ...normalSx})}>내가 생성한 책</BoldText>
                <BoldText onClick={()=>{navigate("/book/sharedList")}} sx={(focusedIndex === 1) ? ({float: "left", marginLeft: "5px", ...focusedSx}) : ({float: "left", marginLeft: "5px", ...normalSx})}>공유된 책</BoldText>
                <BoldText onClick={()=>{navigate("/book/recommend")}} sx={(focusedIndex === 2) ? ({float: "left", marginLeft: "5px", ...focusedSx}) : ({float: "left", marginLeft: "5px", ...normalSx})}>추천 책 목록</BoldText>
                
                <BoldText onClick={onClickCreateEmptyBookButton} sx={(focusedIndex === 3) ? ({float: "right", marginRight: "10px", ...focusedSx}) : ({float: "right", marginRight: "10px", ...normalSx})}>책 생성하기</BoldText>
                {
                    (searchTypes) ? (
                            <Paper component="form" sx={{float:"right", width: "397px", height: "35px", marginLeft: "5px", marginTop: "2px", ...sx}} {...props}>
                            <Select
                                sx={{
                                    height: "35px", width: "125px", paddingLeft: "10px"
                                }}
                                value={searchType}
                                label="검색 대상"
                                name="검색 대상"
                                onChange={(e)=>{setSearchType(e.target.value)}}
                                disableUnderline={true}
                                variant="standard"
                            >
                                {
                                    searchTypes.map((searchType, index) => {
                                        return <MenuItem key={index} value={searchType.type}>{searchType.name}</MenuItem>
                                    })
                                }
                            </Select>
        
                            <InputBase sx={{marginLeft: "10px", width: "217px"}} value={searchText} onChange={(e)=>{setSearchText(e.target.value)}}/>
                            <Box onClick={()=>{handleOnSubmit(searchText, searchType)}} 
                                sx={{
                                    marginLeft: "10px", float:"right", height: "35px", minHeight: "35px", width: "35px", minWidth: "35px",
                                    borderRadius: "0px 5px 5px 0px", backgroundColor: "royalblue", "&:hover": {opacity: 0.80}, color: "white",
                                    cursor: "pointer"
                            }}>
                                <SearchIcon sx={{
                                        float:"right", height: "30px", minHeight: "30px", width: "30px", minWidth: "30px",
                                        marginTop: "2.5px", marginRight: "2.5px"
                                }}></SearchIcon>
                            </Box>
                        </Paper>
                    ) : (null)
                }
            </Box>

            <Backdrop
                sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
                open={isBackdropOpened}
            >
                <CircularProgress color="inherit" />
            </Backdrop>
        </>
    )
}

export default BookSubAppBar;