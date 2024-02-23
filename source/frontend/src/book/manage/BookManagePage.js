import React from 'react';
import { Box, IconButton, Divider, Container, Stack } from "@mui/material";
import { useParams, useNavigate } from 'react-router-dom';
import DoneIcon from '@mui/icons-material/Done';
import EditIcon from '@mui/icons-material/Edit';
import UploadIcon from '@mui/icons-material/Upload';
import SmartToyIcon from '@mui/icons-material/SmartToy';
import ImageIcon from '@mui/icons-material/Image';
import AddIcon from '@mui/icons-material/Add';
import DeleteIcon from '@mui/icons-material/Delete';

import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BoldText from '../../_global/components/text/BoldText';
import NavText from '../../_global/components/text/NavText';

const BookManagePage = () => {
    const navigate = useNavigate()
    const {bookId} = useParams()
    console.log("BookId :", bookId)

    return (
        <>
            <MainNavAppBar focusedIndex={0} backArrowUrl="/book/myList"/>

            <Container maxWidth="md" sx={{marginTop: "15px"}}>
                <Box sx={{width: "100%", height: "30px", marginTop: "5px"}}>
                    <EditIcon sx={{float: "left", marginTop: "5px"}}/>
                    <BoldText sx={{float: "left", fontSize: "20px", marginTop: "5px", marginLeft: "5px"}}>책 편집</BoldText>

                    <IconButton sx={{float: "right", marginTop: "-8px", marginRight: "-5px"}} onClick={(e)=>{e.stopPropagation(); navigate("/book/myList")}}>
                        <DoneIcon sx={{fontSize: "30px", color: "black"}}/>
                        <BoldText sx={{float: "left", fontSize: "20px", marginTop: "5px"}}>완료</BoldText>
                    </IconButton>
                </Box>
                <Divider sx={{width: "100%"}}/>

                <Box sx={{display: "flex"}}>
                    <Stack sx={{float: "left", width: "220px", display: "flex", justifyContent: "center"}}>
                        <Box sx={{paddingLeft: "7px", paddingTop: "5px"}}>
                            <ImageIcon sx={{float: "left", color: "black"}}/>
                            <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px", color: "black"}}>책 표지 이미지</NavText>
                        </Box>

                        <Box
                            component="img"
                            sx={{
                                height: 300,
                                width: 200,
                                backgroundColor: "lightgray",
                                borderRadius: 3,
                                border: "1px solid lightgray",
                                marginX: "auto",
                                marginTop: "5px"
                            }}
                            alt="업로드된 이미지가 표시됩니다."
                            src={"/src/NoImage.jpg"}
                        />
                        
                        <Box sx={{marginTop: "5px", marginLeft: "18px", marginBottom: "5px"}}>
                            <Box onClick={()=>{alert("Gen")}} sx={{float: "left", backgroundColor: "cornflowerblue", width: "63px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                                <SmartToyIcon sx={{float: "left", color: "white"}}/>
                                <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>생성</NavText>
                            </Box>

                            <Box onClick={()=>{alert("Upload")}} sx={{marginLeft: "5px", float: "left", backgroundColor: "cornflowerblue", width: "82px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                                <UploadIcon sx={{float: "left", color: "white"}}/>
                                <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>업로드</NavText>
                            </Box>
                        </Box>
                    </Stack>

                    <Divider orientation="vertical" flexItem/>
                    <Stack sx={{float: "left", flex: "1 0 auto", paddingY: "5px", paddingLeft: "5px"}}>
                        <Box>
                            <BoldText sx={{float: "left", fontSize: "20px", marginTop: "10px"}}>제목: KKKKK</BoldText>

                            <Box onClick={()=>{alert("Gen")}} sx={{float: "right", backgroundColor: "cornflowerblue", width: "63px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                                <SmartToyIcon sx={{float: "left", color: "white"}}/>
                                <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>생성</NavText>
                            </Box>

                            <Box onClick={()=>{alert("Edit")}} sx={{marginRight: "5px", float: "right", backgroundColor: "cornflowerblue", width: "63px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                                <EditIcon sx={{float: "left", color: "white"}}/>
                                <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>편집</NavText>
                            </Box>    
                        </Box>
                        <Divider sx={{marginTop: "5px"}}/>
                        
                        <Box sx={{marginTop: "5px"}}>
                            <BoldText sx={{float: "left", fontSize: "20px"}}>태그</BoldText>

                            <Box onClick={()=>{alert("Gen")}} sx={{float: "right", backgroundColor: "cornflowerblue", width: "63px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                                <SmartToyIcon sx={{float: "left", color: "white"}}/>
                                <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>생성</NavText>
                            </Box>

                            <Box onClick={()=>{alert("Add")}} sx={{marginRight: "5px", float: "right", backgroundColor: "cornflowerblue", width: "63px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                                <AddIcon sx={{float: "left", color: "white"}}/>
                                <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>추가</NavText>
                            </Box>
                        </Box>

                        <Box sx={{display: "flex", flexDirection: "row", width: "630px", flexWrap: "wrap", marginTop: "10px", marginLeft: "-3px"}}>
                            <Box sx={{margin: "5px", backgroundColor: "lightgray", padding: "5px", borderRadius: "5px"}}>
                                <BoldText sx={{fontSize: "20px", display: "inline-block", color: "gray", borderRadius: "5px", marginTop: "4px", marginLeft: "4px", cursor: "context-menu"}}>IT</BoldText>

                                <Box onClick={()=>{alert("Delete")}} sx={{marginLeft: "10px", marginTop: "5px", float: "right", width: "25px", height: "25px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                                    <DeleteIcon sx={{float: "left", color: "gray"}}/>
                                </Box>
                                <Box onClick={()=>{alert("Edit")}} sx={{marginLeft: "20px", marginTop: "5px", float: "right", width: "25px", height: "25px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                                    <EditIcon sx={{float: "left", color: "gray"}}/>
                                </Box>
                            </Box>
                        </Box>
                    </Stack>
                </Box>
                <Divider sx={{width: "100%"}}/>
            </Container>
        </>
    )
}

export default BookManagePage;