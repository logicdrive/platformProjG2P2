import React from 'react';
import { useNavigate } from "react-router-dom";
import { Card, CardContent, Box, IconButton, Stack } from '@mui/material';
import ThumbUpIcon from '@mui/icons-material/ThumbUp';
import ShareIcon from '@mui/icons-material/Share';

import BoldText from '../text/BoldText';

const BookSearchInfo = ({bookId, bookTitle, bookCreater, bookCreateDate, bookLikeCount, bookTags, isShared, onClickCardUrl}) => {
    const navigate = useNavigate()

    return (
        <Card sx={{width: "380px", height: "220px"}} onClick={()=>{navigate(onClickCardUrl)}}>
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
                        src="/src/NoImage.jpg"
                    />
                </Box>
                <Stack sx={{float: "left", marginLeft: "10px", width: "238px"}}>
                    <Box>
                        <BoldText sx={{float: "left", fontSize: "18px", cursor: "pointer"}}>{bookTitle}</BoldText>
                        <BoldText sx={{float: "right", fontSize: "18px", cursor: "pointer"}}>
                            {
                                (isShared) ? (
                                    <ShareIcon sx={{color: "black"}}/>
                                ) : (
                                    <ShareIcon sx={{color: "lightgray"}}/>
                                )
                            }
                        </BoldText>
                    </Box>

                    <BoldText sx={{fontSize: "15px", color:"lightgray", cursor: "pointer"}}>작성자: {bookCreater}</BoldText>
                    <BoldText sx={{fontSize: "15px", color:"lightgray", cursor: "pointer"}}>작성일: {bookCreateDate}</BoldText>
                    
                    <IconButton onClick={(e)=>{e.stopPropagation(); alert("BBB")}} sx={{paddingY: "0px", borderRadius: "5px", marginLeft: "-120px"}}>
                        <ThumbUpIcon sx={{fontSize: "20px"}}/> 
                        <BoldText sx={{marginTop: "3px", marginLeft: "3px", fontSize: "15px", color: "gray"}}>{bookLikeCount} LIKES</BoldText>
                    </IconButton>

                    <Box sx={{display: "flex", flexDirection: "row", width: "215px", flexWrap: "wrap-reverse", marginTop: "75px", marginLeft: "-3px"}}>
                        {
                            bookTags.map((tag, index) => {
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