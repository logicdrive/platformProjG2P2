import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";

import { Card, CardContent, Box, IconButton, Stack } from '@mui/material';
import ThumbUpIcon from '@mui/icons-material/ThumbUp';
import ShareIcon from '@mui/icons-material/Share';
import EditIcon from '@mui/icons-material/Edit';

import BoldText from '../text/BoldText';

const BookSearchInfo = ({bookId, isEditIconVisible}) => {
    const navigate = useNavigate()
    const [bookInfo] = useState({
        id: bookId,
        title: "점프 투 파이썬",
        creater: "TestCreater",
        createDate: "2024-02-22 12:47",
        likeCount: 10,
        tags: ["AAAAA", "BBBBB", "CCCCC", "DDDDD"],
        isShared: false,
        imageUrl: ""
    })


    const onClickLikeButton = () => {
        alert("Liked")
    }

    const onClickSharedButton = () => {
        alert("Shared")
    }


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
                    <Box sx={{height: "30px"}}>
                        <BoldText sx={{float: "left", fontSize: "18px", cursor: "pointer"}}>{bookInfo.title}</BoldText>
                        {
                            (isEditIconVisible) ? (
                                <Stack sx={{float: "right"}}>
                                    <BoldText sx={{fontSize: "18px", cursor: "pointer", "&:hover": {opacity: 0.80}, position: "relative", left: "15px"}} onClick={(e)=>{e.stopPropagation(); onClickSharedButton()}}>
                                    {
                                        (bookInfo.isShared) ? (
                                            <ShareIcon sx={{color: "black"}}/>
                                        ) : (
                                            <ShareIcon sx={{color: "lightgray"}}/>
                                        )
                                    }
                                    </BoldText>

                                    <IconButton sx={{position: "relative", bottom: "5px", left: "9px"}} onClick={(e)=>{e.stopPropagation();navigate(`/book/manage/${bookInfo.id}`)}}>
                                        <EditIcon sx={{fontSize: "22px"}}/> 
                                    </IconButton>
                                </Stack>
                            ) : (null)
                        }
                    </Box>


                    <BoldText sx={{fontSize: "15px", color:"lightgray", cursor: "pointer"}}>작성자: {bookInfo.creater}</BoldText>
                    <BoldText sx={{fontSize: "15px", color:"lightgray", cursor: "pointer"}}>작성일: {bookInfo.createDate}</BoldText>
                    
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