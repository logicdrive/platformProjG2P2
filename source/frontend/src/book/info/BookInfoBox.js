import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import { Divider, Stack, Box, IconButton } from "@mui/material";
import ThumbUpIcon from '@mui/icons-material/ThumbUp';
import AutoStoriesIcon from '@mui/icons-material/AutoStories';

import BoldText from '../../_global/components/text/BoldText';
import NormalText from '../../_global/components/text/NormalText';
import NavText from '../../_global/components/text/NavText';
import AddToBookShelfButton from './AddToBookShelfButton';
import UserProxy from '../../_global/proxy/UserProxy';
import LikeHistoryProxy from '../../_global/proxy/LikeHistoryProxy';
import TagProxy from '../../_global/proxy/TagProxy';
import FileProxy from '../../_global/proxy/FileProxy';
import IndexProxy from '../../_global/proxy/IndexProxy';
import TimeTool from '../../_global/tool/TimeTool';
import DictionaryTool from '../../_global/tool/DictionaryTool';

const BookInfoBox = ({rawBookInfo}) => {
    const navigate = useNavigate()
    const [bookInfo, setBookInfo] = useState({})
    useEffect(() => {
        (async () => {
            if(DictionaryTool.isEmpty(rawBookInfo)) return

            const createrData = await UserProxy.searchUserOneByUserId(rawBookInfo.createrId)
            const likeHistoryDatas = (await LikeHistoryProxy.searchLikeHistoryAllByBookId(rawBookInfo.bookId))._embedded.likeHistories
            const tagDatas = (await TagProxy.searchAllTagByBookId(rawBookInfo.bookId))._embedded.tags
            const fileData = await FileProxy.searchFileOneByFileId(rawBookInfo.coverImageFileId)
            const indexDatas = (await IndexProxy.searchIndexAllByBookId(rawBookInfo.bookId))._embedded.indexes
    
            setBookInfo({
                id: rawBookInfo.bookId,
                title: rawBookInfo.title,
                creator: createrData.name,
                createdDate: TimeTool.prettyDateString(rawBookInfo.createdDate),
                editedDate: TimeTool.prettyDateString(rawBookInfo.updatedDate),
                indexCount: indexDatas.length,
                likeCount: likeHistoryDatas.length,
                tags: tagDatas.map((tagData) => tagData.name),
                imageUrl: fileData.url
            })
        })()
    }, [rawBookInfo])


    const onClickAddToBookShelfButton = (selectedBookShelfId) => {
        alert("Add to BookShelf Button Clicked! Selected BookShelf Id: " + selectedBookShelfId)
    }


    if(!bookInfo.id) return <></>
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
                    src={(bookInfo.imageUrl) ? bookInfo.imageUrl : "/src/NoImage.jpg"}
                />
            <Stack sx={{float: "left", marginLeft: "10px"}}>
                <NormalText sx={{fontSize: "20px"}}>작성자: {bookInfo.creator}</NormalText>
                <Divider sx={{marginTop: "5px", marginBottom: "5px", width: "620px"}}/>

                <NormalText sx={{fontSize: "20px"}}>작성 날짜: {bookInfo.createdDate}</NormalText>
                <NormalText sx={{fontSize: "20px"}}>수정 날짜: {bookInfo.editedDate}</NormalText>
                <NormalText sx={{fontSize: "20px"}}>목차수: {bookInfo.indexCount}</NormalText>
                
                <Divider sx={{marginTop: "5px", marginBottom: "5px"}}/>
                <IconButton onClick={(e)=>{e.stopPropagation(); alert("LIKE")}} sx={{paddingY: "0px", borderRadius: "5px", marginLeft: "-10px", width: "120px"}}>
                    <ThumbUpIcon sx={{fontSize: "20px"}}/> 
                    <BoldText sx={{marginTop: "3px", marginLeft: "3px", fontSize: "15px", color: "gray"}}>{bookInfo.likeCount} LIKES</BoldText>
                </IconButton>

                <Divider sx={{marginTop: "5px", marginBottom: "5px"}}/>
                <Box sx={{height: "88px"}}>
                {
                    bookInfo.tags.map((tag, index) => {
                        return (
                            <BoldText key={index} sx={{fontSize: "10px", backgroundColor: "lightgray", padding: "5px", display: "inline-block", color: "gray", borderRadius: "5px", marginRight: "4px", cursor: "context-menu"}}>{tag}</BoldText>
                        )
                    })
                }
                </Box>

                <Box>
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