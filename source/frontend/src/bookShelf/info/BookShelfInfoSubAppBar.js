import React, { useState, useEffect, useContext } from 'react';
import { Box, Paper, InputBase, MenuItem, Select } from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';
import DeleteIcon from '@mui/icons-material/Delete';
import ShareIcon from '@mui/icons-material/Share';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import BoldText from '../../_global/components/text/BoldText';
import EditBookShelfTitleButton from './EditBookShelfTitleButton';
import YesNoButton from '../../_global/components/button/YesNoButton';
import BookShelfProxy from '../../_global/proxy/BookShelfProxy';

const BookShelfInfoSubAppBar = ({rawBookShelfInfo, searchTypes, handleOnSubmit, setIsBackdropOpened, sx, ...props}) => {
    const {addAlertPopUp} = useContext(AlertPopupContext)
    const [searchText, setSearchText] = useState("")
    const [searchType, setSearchType] = useState(searchTypes[0].type)
    
    const [bookShelfInfo, setBookShelfInfo] = useState({})
    useEffect(() => {
        (async () => {
            setBookShelfInfo({
                id: rawBookShelfInfo.bookShelfId,
                title: rawBookShelfInfo.title,
                isShared: rawBookShelfInfo.isShared
            })
        })()
    }, [rawBookShelfInfo])


    const onClickBookShelfTitleEditButton = async (title) => {
        try {

            setIsBackdropOpened(true)
            await BookShelfProxy.updateBookShelfTitle(bookShelfInfo.id, title)
    
          } catch(error) {
            addAlertPopUp("책장의 제목을 변경하는 도중에 오류가 발생했습니다!", "error")
            console.error("책장의 제목을 변경하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }

    const onClickSharedButton = async (isShared) => {
        try {

            setIsBackdropOpened(true)
            await BookShelfProxy.updateIsShared(bookShelfInfo.id, isShared)
    
          } catch(error) {
            addAlertPopUp("책장의 공유 여부를 변경하는 도중에 오류가 발생했습니다!", "error")
            console.error("책장의 공유 여부를 변경하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }

    const onClickDeleteButton = () => {
        alert("Delete")
    }


    return (
        <>
            <Box sx={{width: "100%", height: "50px", padding: "10px", marginTop: "5px", ...sx}} {...props}>
                <BoldText sx={{float: "left", fontSize: "20px", marginTop: "5px"}}>책장: {bookShelfInfo.title}</BoldText>
                <EditBookShelfTitleButton onClickEditButton={onClickBookShelfTitleEditButton} defaultTitle={bookShelfInfo.title}/>

                <Box sx={{float: "right", cursor: "pointer", "&:hover": {opacity: 0.80}, padding: "8px", marginRight: "8px"}}>
                    <YesNoButton onClickYes={onClickDeleteButton} title="해당 책장을 삭제하시겠습니까?">
                        <DeleteIcon sx={{fontSize: "20px", color: "gray"}}/> 
                    </YesNoButton>
                </Box>
                <Box sx={{float: "right", cursor: "pointer", "&:hover": {opacity: 0.80}, padding: "8px", marginRight: "-8px"}}>
                {
                    (bookShelfInfo.isShared) ? (
                        <YesNoButton onClickYes={()=>{onClickSharedButton(false)}} title="해당 책장의 공유를 취소하시겠습니까?">
                            <ShareIcon sx={{fontSize: "20px", color: "gray"}}/>
                        </YesNoButton>
                    ) : (
                        <YesNoButton onClickYes={(e)=>{onClickSharedButton(true)}} title="해당 책장을 공유시겠습니까?">
                            <ShareIcon sx={{fontSize: "20px", color: "lightgray"}}/>
                        </YesNoButton>
                    )
                }
                </Box>

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
            </Box>
        </>
    )
}

export default BookShelfInfoSubAppBar;