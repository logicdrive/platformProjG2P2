import React from 'react';
import { useParams } from 'react-router-dom';
import { Divider, Box, Stack } from "@mui/material";
import ListIcon from '@mui/icons-material/List';
import AutoStoriesIcon from '@mui/icons-material/AutoStories';
import QuizIcon from '@mui/icons-material/Quiz';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BoldText from '../../_global/components/text/BoldText';
import NormalText from '../../_global/components/text/NormalText';
import QuestionInfo from './QuestionInfo';
import IndexesInfoBox from './IndexesInfoBox';
import ContentInfoBox from './ContentInfoBox';
import IndexMoveButtons from './IndexMoveButtons';

const BookReadPage = () => {
    const {bookId, indexId} = useParams()
    console.log("BookId :", bookId, "IndexId :", indexId)

    return (
        <>
            <MainNavAppBar focusedIndex={0} backArrowUrl={-1}/>

            <BoldText sx={{fontSize: "25px", marginTop: "10px", cursor: "context-menu"}}>Python</BoldText>
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

                        <ContentInfoBox rawContentInfo={{
                            id: 1,
                            imageUrl: "/src/NoImage.jpg",
                            content: "Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is Python is "
                        }}/>
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
                            <QuestionInfo/>
                            <QuestionInfo/>
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