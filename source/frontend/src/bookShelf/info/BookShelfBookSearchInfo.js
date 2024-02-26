import React, { useState, useEffect, useContext } from 'react';
import { useNavigate } from "react-router-dom";

import { Card, CardContent, Box, IconButton, Stack } from '@mui/material';
import ThumbUpIcon from '@mui/icons-material/ThumbUp';
import DeleteIcon from '@mui/icons-material/Delete';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import BoldText from '../../_global/components/text/BoldText';
import YesNoButton from '../../_global/components/button/YesNoButton';
import UserProxy from '../../_global/proxy/UserProxy';
import LikeHistoryProxy from '../../_global/proxy/LikeHistoryProxy';
import TagProxy from '../../_global/proxy/TagProxy';
import FileProxy from '../../_global/proxy/FileProxy';
import BookProxy from '../../_global/proxy/BookProxy';
import TimeTool from '../../_global/tool/TimeTool';

const BookShelfBookSearchInfo = ({rawBookShelfBookInfo, setIsBackdropOpened}) => {
    const navigate = useNavigate()
    const {addAlertPopUp} = useContext(AlertPopupContext)
    const [bookInfo, setBookInfo] = useState({})
    useEffect(() => {
        (async () => {
            const bookData = await BookProxy.searchBookOneByBookId(rawBookShelfBookInfo.bookId)
            const createrData = await UserProxy.searchUserOneByUserId(bookData.createrId)
            const likeHistoryDatas = (await LikeHistoryProxy.searchLikeHistoryAllByBookId(bookData.bookId))._embedded.likeHistories
            const tagDatas = (await TagProxy.searchAllTagByBookId(bookData.bookId))._embedded.tags
            const fileData = await FileProxy.searchFileOneByFileId(bookData.coverImageFileId)

            setBookInfo({
                id: bookData.bookId,
                title: bookData.title,
                creator: createrData.name,
                createdDate: TimeTool.prettyOnlyDateString(bookData.createdDate),
                likeCount: likeHistoryDatas.length,
                tags: tagDatas.map((tagData) => tagData.name),
                imageUrl: fileData.url,
                bookShelfId: rawBookShelfBookInfo.bookShelfId
            })
        })()
    }, [rawBookShelfBookInfo])


    const onClickLikeButton = async () => {
        try {

            setIsBackdropOpened(true)
            await BookProxy.likeBook(bookInfo.id)
    
          } catch(error) {
            addAlertPopUp("책에 좋아요를 추가하는 도중에 오류가 발생했습니다!", "error")
            console.error("책에 좋아요를 추가하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }

    const onClickDeleteButton = () => {
        alert("Deleted")
    }

    
    if(!bookInfo.id) return <></>
    return (
        <Card sx={{width: "380px", height: "220px"}} onClick={()=>{navigate(`/book/info/${bookInfo.id}`)}}>
            <CardContent sx={{padding: "10px"}}>
                <Box sx={{float: "left", cursor: "pointer"}}>
                    <Box
                        component="img"
                        sx={{
                            height: 200,
                            width: 110,
                            backgroundColor: "lightgray",
                            borderRadius: 3,
                            border: "1px solid lightgray",

                        }}
                        alt="업로드된 이미지가 표시됩니다."
                        src={(bookInfo.imageUrl) ? bookInfo.imageUrl : "/src/NoImage.jpg"}
                    />
                </Box>
                <Stack sx={{float: "left", marginLeft: "10px", width: "238px"}}>
                    <Box>
                        <BoldText sx={{float: "left", fontSize: "18px", cursor: "pointer"}}>{bookInfo.title}</BoldText>
                        
                        <Box onClick={(e)=>{e.stopPropagation()}}>
                            <YesNoButton onClickYes={()=>{onClickDeleteButton()}} title="해당 책을 책장에서 삭제하시겠습니까?">
                                <BoldText sx={{float: "right", fontSize: "18px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                                    <DeleteIcon sx={{color: "gray"}}/>
                                </BoldText>
                            </YesNoButton>
                        </Box>
                    </Box>

                    <BoldText sx={{fontSize: "15px", color:"lightgray", cursor: "pointer"}}>작성자: {bookInfo.creator}</BoldText>
                    <BoldText sx={{fontSize: "15px", color:"lightgray", cursor: "pointer"}}>작성일: {bookInfo.createdDate}</BoldText>
                    
                    <IconButton onClick={(e)=>{e.stopPropagation();onClickLikeButton()}} sx={{paddingY: "0px", borderRadius: "5px", marginLeft: "-10px", width: "110px"}}>
                        <ThumbUpIcon sx={{fontSize: "20px"}}/> 
                        <BoldText sx={{marginTop: "3px", marginLeft: "3px", fontSize: "15px", color: "gray"}}>{bookInfo.likeCount} LIKES</BoldText>
                    </IconButton>

                    <Box>
                    {
                        bookInfo.tags.map((tag, index) => {
                            return (
                                <BoldText key={index} sx={{fontSize: "10px", backgroundColor: "lightgray", padding: "5px", display: "inline-block", color: "gray", borderRadius: "5px", marginRight: "4px", cursor: "context-menu"}}>{tag}</BoldText>
                            )
                        })
                    }
                    </Box>
                </Stack>
            </CardContent>
        </Card>
    )
}

export default BookShelfBookSearchInfo;