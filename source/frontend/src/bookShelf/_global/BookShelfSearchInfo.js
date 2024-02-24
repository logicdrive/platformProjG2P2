import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import { Card, CardContent, Box, Stack } from '@mui/material';
import ShareIcon from '@mui/icons-material/Share';

import BoldText from '../../_global/components/text/BoldText';
import YesNoButton from '../../_global/components/button/YesNoButton';

const BookShelfSearchInfo = ({rawBookShelfInfo, isEditIconVisible}) => {
    const navigate = useNavigate()
    const [bookShelfInfo] = useState({
        id: rawBookShelfInfo.id,
        title: rawBookShelfInfo.title,
        creator: rawBookShelfInfo.creator,
        createdDate: rawBookShelfInfo.createdDate,
        bookCount: rawBookShelfInfo.bookCount,
        tags: rawBookShelfInfo.tags,
        isShared: rawBookShelfInfo.isShared,
        imageUrls: rawBookShelfInfo.imageUrls
    })

    const onClickSharedButton = (isShared) => {
        alert("Shared: " + isShared)
    }

    return (
        <Card sx={{width: "380px", height: "220px"}} onClick={()=>{navigate(`/bookShelf/info/${bookShelfInfo.id}`)}}>
            <CardContent sx={{padding: "10px"}}>
                <Box sx={{float: "left", cursor: "pointer", height: 200, width: 110}}>
                {(() => {
                    if(bookShelfInfo.imageUrls && bookShelfInfo.imageUrls.length >= 3) {
                        return (
                            <>
                            <Box
                                component="img"
                                sx={{
                                    height: 160,
                                    width: 70,
                                    backgroundColor: "lightgray",
                                    borderRadius: 3,
                                    border: "1px solid lightgray",
                                }}
                                alt="업로드된 이미지가 표시됩니다."
                                src={bookShelfInfo.imageUrls[0]}
                            />

                            <Box
                                component="img"
                                sx={{
                                    height: 160,
                                    width: 70,
                                    backgroundColor: "lightgray",
                                    borderRadius: 3,
                                    border: "1px solid lightgray",
                                    zIndex: 2,
                                    position: "absolute",
                                    marginLeft: "-57.5px",
                                    marginTop: "20px"
                                }}
                                alt="업로드된 이미지가 표시됩니다."
                                src={bookShelfInfo.imageUrls[1]}
                            />

                            <Box
                                component="img"
                                sx={{
                                    height: 160,
                                    width: 70,
                                    backgroundColor: "lightgray",
                                    borderRadius: 3,
                                    border: "1px solid lightgray",
                                    zIndex: 2,
                                    position: "absolute",
                                    marginLeft: "-40px",
                                    marginTop: "40px"
                                }}
                                alt="업로드된 이미지가 표시됩니다."
                                src={bookShelfInfo.imageUrls[2]}
                            />
                            </>
                        )
                    }else if(bookShelfInfo.imageUrls && bookShelfInfo.imageUrls.length === 2) {
                        return (
                            <>
                            <Box
                                component="img"
                                sx={{
                                    height: 180,
                                    width: 90,
                                    backgroundColor: "lightgray",
                                    borderRadius: 3,
                                    border: "1px solid lightgray",
                                }}
                                alt="업로드된 이미지가 표시됩니다."
                                src={bookShelfInfo.imageUrls[0]}
                            />

                            <Box
                                component="img"
                                sx={{
                                    height: 180,
                                    width: 90,
                                    backgroundColor: "lightgray",
                                    borderRadius: 3,
                                    border: "1px solid lightgray",
                                    zIndex: 2,
                                    position: "absolute",
                                    marginLeft: "-80px",
                                    marginTop: "20px"
                                }}
                                alt="업로드된 이미지가 표시됩니다."
                                src={bookShelfInfo.imageUrls[1]}
                            />
                            </>
                        )
                    } else {
                        return (
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
                                src={(bookShelfInfo.imageUrls && bookShelfInfo.imageUrls.length >= 1) ? bookShelfInfo.imageUrls[0] : "/src/NoImage.jpg"}
                            />
                        )
                    } 
                })()}
                </Box>
                <Stack sx={{float: "left", marginLeft: "12px", width: "238px"}}>
                    <Box sx={{height: "30px"}}>
                        <BoldText sx={{float: "left", fontSize: "18px", cursor: "pointer"}}>{bookShelfInfo.title}</BoldText>
                        {
                            (isEditIconVisible) ? (
                                <Box sx={{float: "right", fontSize: "18px", cursor: "pointer", "&:hover": {opacity: 0.80}, position: "relative", left: "1px"}} onClick={(e)=>{e.stopPropagation();}}>
                                    {
                                        (bookShelfInfo.isShared) ? (
                                            <YesNoButton onClickYes={()=>{onClickSharedButton(false)}} title="해당 책장의 공유를 취소하시겠습니까?">
                                                <ShareIcon sx={{color: "black"}}/>
                                            </YesNoButton>
                                        ) : (
                                            <YesNoButton onClickYes={(e)=>{onClickSharedButton(true)}} title="해당 책장을 공유시겠습니까?">
                                                <ShareIcon sx={{color: "lightgray"}}/>
                                            </YesNoButton>
                                        )
                                    }
                                </Box>
                            ) : (null)
                        }
                    </Box>
                    
                    <BoldText sx={{fontSize: "15px", color:"lightgray", cursor: "pointer"}}>보관된 책 개수: {bookShelfInfo.bookCount}</BoldText>
                    <BoldText sx={{fontSize: "15px", color:"lightgray", cursor: "pointer"}}>작성자: {bookShelfInfo.creator}</BoldText>
                    <BoldText sx={{fontSize: "15px", color:"lightgray", cursor: "pointer"}}>작성일: {bookShelfInfo.createdDate}</BoldText>
                    
                    <Box sx={{display: "flex", flexDirection: "row", width: "215px", flexWrap: "wrap-reverse", marginTop: "78px", marginLeft: "-3px"}}>
                        {
                            bookShelfInfo.tags.map((tag, index) => {
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

export default BookShelfSearchInfo;