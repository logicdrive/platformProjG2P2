import React, { useState, useEffect, useContext } from 'react';
import { useParams } from 'react-router-dom';
import { Divider, Box, Stack } from "@mui/material";
import ListIcon from '@mui/icons-material/List';
import AutoStoriesIcon from '@mui/icons-material/AutoStories';
import QuizIcon from '@mui/icons-material/Quiz';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BoldText from '../../_global/components/text/BoldText';
import NormalText from '../../_global/components/text/NormalText';
import QuestionInfoBox from './QuestionInfoBox';
import IndexesInfoBox from './IndexesInfoBox';
import ContentInfoBox from './ContentInfoBox';
import IndexMoveButtons from './IndexMoveButtons';
import BookProxy from '../../_global/proxy/BookProxy';
import ContentProxy from '../../_global/proxy/ContentProxy';

const BookReadPage = () => {
    const {bookId, indexId} = useParams()
    const {addAlertPopUp} = useContext(AlertPopupContext)


    const [rawBookInfo, setRawBookInfo] = useState({})
    const [rawContentInfo, setRawContentInfo] = useState({})
    const [loadInfos] = useState(() => {
        return async (bookId, indexId) => {
            try {

                setRawBookInfo(await BookProxy.searchBookOneByBookId(bookId))

                if(await ContentProxy.existsByIndexId(indexId))
                    setRawContentInfo(await ContentProxy.searchContentOneByIndexId(indexId))

            } catch (error) {
                addAlertPopUp("관련 정보를 가져오는 과정에서 오류가 발생했습니다!", "error");
                console.error("관련 정보를 가져오는 과정에서 오류가 발생했습니다!", error);
            }
        }
    })
    useEffect(() => {
        loadInfos(bookId, indexId)
    }, [bookId, indexId, loadInfos])


    return (
        <>
            <MainNavAppBar focusedIndex={0} backArrowUrl={-1}/>

            <BoldText sx={{fontSize: "25px", marginTop: "10px", cursor: "context-menu"}}>{rawBookInfo.title}</BoldText>
            <Divider sx={{marginTop: "5px"}}/>

            <Box sx={{display: "flex"}}>
                <Stack sx={{float: "left", width: "300px"}}>
                    <Box sx={{cursor: "context-menu"}}>
                        <ListIcon sx={{float: "left", paddingTop: "2px"}}/>
                        <NormalText sx={{fontSize: "20px", float: "left"}}>목차</NormalText>
                    </Box>
                    
                    <IndexesInfoBox 
                        bookId={bookId} 
                        rawIndexInfos={[
                            {id: 1, title: "Python 소개"},
                            {id: 2, title: "Python 기초 문법"}]}
                        focusedIndex={indexId}
                    />
                </Stack>

                <Divider orientation="vertical" flexItem/>
                <Stack sx={{float: "left", flex: "1 0 auto", paddingX: "5px"}}>
                    <Stack>
                        <Box sx={{cursor: "context-menu"}}>
                            <AutoStoriesIcon sx={{float: "left", paddingTop: "2px"}}/>
                            <NormalText sx={{fontSize: "20px", float: "left", marginLeft: "2px"}}>학습</NormalText>
                        </Box>

                        <ContentInfoBox rawContentInfo={rawContentInfo}/>
                    </Stack>
                    <Divider sx={{marginY: "5px"}}/>

                    <Stack>
                        <Box sx={{cursor: "context-menu"}}>
                            <QuizIcon sx={{float: "left", paddingTop: "2px"}}/>
                            <NormalText sx={{fontSize: "20px", float: "left", marginLeft: "2px"}}>퀴즈</NormalText>
                        </Box>
                        
                        <Stack
                            spacing={2}
                        >
                            <QuestionInfoBox rawProblemInfo={
                                {
                                    id: 1,
                                    content: "Q1. What is the Python?\n\nA. Python is a programming language.\nB. Python is a snake.\nC. Python is a food.",
                                    answer: "A. Python is a programming language."
                                }
                            }/>
                        </Stack>
                    </Stack>
                    <Divider sx={{marginY: "5px"}}/>

                    <IndexMoveButtons bookId={bookId} indexId={indexId} indexCount={2}/>
                </Stack>
            </Box>
        </>
    )
}

export default BookReadPage;