import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router-dom";

import { Card, CardContent, Box, IconButton, Stack } from '@mui/material';
import ThumbUpIcon from '@mui/icons-material/ThumbUp';
import ShareIcon from '@mui/icons-material/Share';
import EditIcon from '@mui/icons-material/Edit';

import BoldText from '../text/BoldText';
import YesNoButton from '../button/YesNoButton';
import UserProxy from '../../proxy/UserProxy';
import LikeHistoryProxy from '../../proxy/LikeHistoryProxy';
import TagProxy from '../../proxy/TagProxy';
import FileProxy from '../../proxy/FileProxy';
import TimeTool from '../../tool/TimeTool';

const BookSearchInfo = ({rawBookInfo, isEditIconVisible, ...props}) => {
    const navigate = useNavigate()
    const [bookInfo, setBookInfo] = useState({})
    useEffect(() => {
        (async () => {
            const createrData = await UserProxy.searchUserOneByUserId(rawBookInfo.createrId)
            const likeHistoryDatas = (await LikeHistoryProxy.searchLikeHistoryAllByBookId(rawBookInfo.bookId))._embedded.likeHistories
            const tagDatas = (await TagProxy.searchAllTagByBookId(rawBookInfo.bookId))._embedded.tags
            const fileData = await FileProxy.searchFileOneByFileId(rawBookInfo.bookId)
    
            setBookInfo({
                id: rawBookInfo.bookId,
                title: rawBookInfo.title,
                creator: createrData.name,
                createdDate: TimeTool.prettyOnlyDateString(rawBookInfo.createdDate),
                likeCount: likeHistoryDatas.length,
                tags: tagDatas.map((tagData) => tagData.name),
                isShared: rawBookInfo.isShared,
                imageUrl: fileData.url
            })
        })()
    }, [rawBookInfo])


    const onClickLikeButton = () => {
        alert("Liked")
    }

    const onClickSharedButton = (isShared) => {
        alert("Shared: " + isShared)
    }

    
    if(!bookInfo.id) return (<></>)
    return (
        <Card sx={{width: "380px", height: "220px"}} onClick={()=>{navigate(`/book/info/${bookInfo.id}`)}} {...props}>
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
                    <Box sx={{height: "30px"}}>
                        <BoldText sx={{float: "left", fontSize: "18px", cursor: "pointer"}}>{bookInfo.title}</BoldText>
                        {
                            (isEditIconVisible) ? (
                                <Stack sx={{float: "right"}}>
                                    <Box sx={{fontSize: "18px", cursor: "pointer", "&:hover": {opacity: 0.80}, position: "relative", left: "15px"}} onClick={(e)=>{e.stopPropagation()}}>
                                    {
                                        (bookInfo.isShared) ? (
                                            <YesNoButton onClickYes={()=>{onClickSharedButton(false)}} title="해당 책의 공유를 취소하시겠습니까?">
                                                <ShareIcon sx={{color: "black"}}/>
                                            </YesNoButton>
                                        ) : (
                                            <YesNoButton onClickYes={(e)=>{onClickSharedButton(true)}} title="해당 책을 공유시겠습니까?">
                                                <ShareIcon sx={{color: "lightgray"}}/>
                                            </YesNoButton>
                                        )
                                    }
                                    </Box>

                                    <IconButton sx={{position: "relative", bottom: "5px", left: "9px"}} onClick={(e)=>{e.stopPropagation();navigate(`/book/manage/${bookInfo.id}`)}}>
                                        <EditIcon sx={{fontSize: "22px"}}/> 
                                    </IconButton>
                                </Stack>
                            ) : (null)
                        }
                    </Box>


                    <BoldText sx={{fontSize: "15px", color:"lightgray", cursor: "pointer"}}>작성자: {bookInfo.creator}</BoldText>
                    <BoldText sx={{fontSize: "15px", color:"lightgray", cursor: "pointer"}}>작성일: {bookInfo.createdDate}</BoldText>
                    
                    <IconButton onClick={(e)=>{e.stopPropagation();onClickLikeButton()}} sx={{paddingY: "0px", borderRadius: "5px", marginLeft: "-10px", width: "110px"}}>
                        <ThumbUpIcon sx={{fontSize: "20px"}}/> 
                        <BoldText sx={{marginTop: "3px", marginLeft: "3px", fontSize: "15px", color: "gray"}}>{bookInfo.likeCount} LIKES</BoldText>
                    </IconButton>


                    <Box sx={{display: "flex", flexDirection: "row", width: "215px", flexWrap: "wrap-reverse", marginTop: "75px", marginLeft: "-3px"}}>
                        {
                            bookInfo.tags.map((tag, index) => {
                                return (
                                    <BoldText key={index} sx={{fontSize: "10px", backgroundColor: "lightgray", padding: "5px", display: "inline-block", color: "gray", borderRadius: "5px", margin: "2px", cursor: "context-menu"}}>{tag}</BoldText>
                                )
                            })
                        }
                    </Box>
                </Stack>
            </CardContent>
        </Card>
    )
}

export default BookSearchInfo;