import React from 'react';
import { Card, CardContent, Box, Stack, IconButton, Pagination } from '@mui/material';
import ThumbUpIcon from '@mui/icons-material/ThumbUp';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BookSubAppBar from '../_global/BookSubAppBar';
import BoldText from '../../_global/components/text/BoldText';

const BookMyListPage = () => {
    return (
        <>
            <MainNavAppBar focusedIndex={0}/>
            <BookSubAppBar focusedIndex={0}/>

            <Stack>
                <Stack direction="row" spacing={2}>
                    <Card sx={{width: "380px", height: "220px"}} onClick={()=>{alert("AAA")}}>
                        <CardContent sx={{padding: "10px"}}>
                            <Box sx={{float: "left"}}>
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
                            <Box sx={{float: "left", marginLeft: "10px"}}>
                                <BoldText sx={{fontSize: "18px"}}>점프 투 파이썬</BoldText>
                                <BoldText sx={{fontSize: "15px", color:"lightgray"}}>작성자: TestCreater</BoldText>
                                <BoldText sx={{fontSize: "15px", color:"lightgray"}}>작성일: 2024-02-22 12:47</BoldText>
                                
                                <IconButton sx={{padding: "0px", borderRadius: "5px"}}>
                                    <ThumbUpIcon sx={{fontSize: "20px"}}/> 
                                    <BoldText onClick={(e)=>{e.stopPropagation(); alert("BBB")}} sx={{marginTop: "3px", marginLeft: "3px", fontSize: "15px", color: "gray"}}>10 LIKES</BoldText>
                                </IconButton>

                                <Box sx={{display: "flex", flexDirection: "row", width: "215px", flexWrap: "wrap-reverse", marginTop: "80px", marginLeft: "-3px"}}>
                                    <BoldText sx={{fontSize: "10px", backgroundColor: "lightgray", padding: "5px", display: "inline-block", color: "gray", borderRadius: "5px", margin: "2px"}}>AAAAA</BoldText>
                                    <BoldText sx={{fontSize: "10px", backgroundColor: "lightgray", padding: "5px", display: "inline-block", color: "gray", borderRadius: "5px", margin: "2px"}}>BBBBB</BoldText>
                                    <BoldText sx={{fontSize: "10px", backgroundColor: "lightgray", padding: "5px", display: "inline-block", color: "gray", borderRadius: "5px", margin: "2px"}}>CCCCC</BoldText>
                                    <BoldText sx={{fontSize: "10px", backgroundColor: "lightgray", padding: "5px", display: "inline-block", color: "gray", borderRadius: "5px", margin: "2px"}}>DDDDD</BoldText>
                                </Box>
                            </Box>
                        </CardContent>
                    </Card>
                </Stack>
                
                <Box sx={{width: "100%", marginTop: "10px", display: "flex", justifyContent: "center"}}>
                    <Pagination sx={{padding: "auto", margin: "0 auto"}} count={10}/>
                </Box>
            </Stack>
        </>
    )
}

export default BookMyListPage;