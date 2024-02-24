import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { Divider, Stack, Box, IconButton } from "@mui/material";
import ThumbUpIcon from '@mui/icons-material/ThumbUp';
import AutoStoriesIcon from '@mui/icons-material/AutoStories';

import BoldText from '../../_global/components/text/BoldText';
import NormalText from '../../_global/components/text/NormalText';
import NavText from '../../_global/components/text/NavText';
import AddToBookShelfButton from './AddToBookShelfButton';

const BookInfoBox = ({rawBookInfo}) => {
    const navigate = useNavigate()
    const [bookInfo] = useState({
        id: rawBookInfo.id,
        imageUrl: rawBookInfo.imageUrl,
        creator: rawBookInfo.creator,
        createdDate: rawBookInfo.createdDate,
        editedDate: rawBookInfo.editedDate,
        indexCount: rawBookInfo.indexCount,
        likeCount: rawBookInfo.likeCount
    })


    const onClickAddToBookShelfButton = (selectedBookShelfId) => {
        alert("Add to BookShelf Button Clicked! Selected BookShelf Id: " + selectedBookShelfId)
    }


    return (
        <Box sx={{marginTop: "15px", padding: "10px"}}>
            <Box
                    component="img"
                    sx={{
                        height: 300,
                        width: 200,
                        backgroundColor: "lightgray",
                        borderRadius: 3,
                        border: "1px solid lightgray",
                        float:"left"

                    }}
                    alt="업로드된 이미지가 표시됩니다."
                    src={bookInfo.imageUrl}
                />
            <Stack sx={{float: "left", marginLeft: "10px"}}>
                <NormalText sx={{fontSize: "20px"}}>작성자: {bookInfo.creator}</NormalText>
                <Divider sx={{marginTop: "5px", marginBottom: "5px", width: "620px"}}/>

                <NormalText sx={{fontSize: "20px"}}>작성일: {bookInfo.createdDate}</NormalText>
                <NormalText sx={{fontSize: "20px"}}>수정일: {bookInfo.editedDate}</NormalText>
                <NormalText sx={{fontSize: "20px"}}>목차수: {bookInfo.indexCount}</NormalText>
                
                <Divider sx={{marginTop: "5px", marginBottom: "5px"}}/>
                <IconButton onClick={(e)=>{e.stopPropagation(); alert("LIKE")}} sx={{paddingY: "0px", borderRadius: "5px", marginLeft: "-10px", width: "120px"}}>
                    <ThumbUpIcon sx={{fontSize: "20px"}}/> 
                    <BoldText sx={{marginTop: "3px", marginLeft: "3px", fontSize: "15px", color: "gray"}}>{bookInfo.likeCount} LIKES</BoldText>
                </IconButton>

                <Divider sx={{marginTop: "5px", marginBottom: "5px"}}/>
                <Box sx={{marginTop: "88px"}}>
                    <Box onClick={()=>{navigate(`/book/read/${bookInfo.id}/1`)}}sx={{float: "left", backgroundColor: "cornflowerblue", width: "85px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                        <AutoStoriesIcon sx={{float: "left", color: "white"}}/>
                        <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>책 읽기</NavText>
                    </Box>

                    <AddToBookShelfButton onClickAddButton={onClickAddToBookShelfButton}/>
                </Box>
            </Stack>
        </Box>
    )
}

export default BookInfoBox;