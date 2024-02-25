import React, { useState, useEffect, useContext } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

import { Box, IconButton, Divider, Container, Stack } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';
import DoneIcon from '@mui/icons-material/Done';
import EditIcon from '@mui/icons-material/Edit';
import UploadIcon from '@mui/icons-material/Upload';
import ImageIcon from '@mui/icons-material/Image';
import ListIcon from '@mui/icons-material/List';
import LabelIcon from '@mui/icons-material/Label';
import TitleIcon from '@mui/icons-material/Title';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import MainNavAppBar from '../../_global/components/MainNavAppBar';
import BoldText from '../../_global/components/text/BoldText';
import NavText from '../../_global/components/text/NavText';
import TagInfoBox from './TagInfoBox';
import IndexInfoBox from './IndexInfoBox';
import YesNoButton from '../../_global/components/button/YesNoButton';
import EditBookTitleButton from './EditBookTitleButton';
import AddTagNameButton from './AddTagNameButton';
import AddIndexNameButton from './AddIndexNameButton';
import GenerateTagsButton from './GenerateTagsButton';
import GenerateIndexesButton from './GenerateIndexesButton';
import GenerateCoverImageButton from './GenerateCoverImageButton';
import FileUploadButton from '../../_global/components/button/FileUploadButton';
import BookProxy from '../../_global/proxy/BookProxy';
import TagProxy from '../../_global/proxy/TagProxy';
import FileProxy from '../../_global/proxy/FileProxy';
import IndexProxy from '../../_global/proxy/IndexProxy';

const BookManagePage = () => {
    const navigate = useNavigate()
    const {addAlertPopUp} = useContext(AlertPopupContext)
    const {bookId} = useParams()
    console.log("BookId :", bookId)

    const [coverImageUrl, setCoverImageUrl] = useState("")
    const [bookInfo, setBookInfo] = useState({})
    useEffect(() => {
        (async () => {
            try {

                const rawBookInfo = await BookProxy.searchBookOneByBookId(bookId)
                const rawTagInfos = (await TagProxy.searchAllTagByBookId(rawBookInfo.bookId))._embedded.tags
                const rawIndexInfos = (await IndexProxy.searchIndexAllByBookId(rawBookInfo.bookId))._embedded.indexes
                const fileData = await FileProxy.searchFileOneByFileId(rawBookInfo.bookId)

                setBookInfo({
                    id: rawBookInfo.bookId,
                    title: rawBookInfo.title,
                    imageUrl: fileData.url,
                    rawTagInfos: rawTagInfos,
                    rawIndexInfos: rawIndexInfos
                })
                setCoverImageUrl(fileData.url)

            } catch (error) {
                addAlertPopUp("책 정보를 가져오는 과정에서 오류가 발생했습니다!", "error");
                console.error("책 정보를 가져오는 과정에서 오류가 발생했습니다!", error);
            }
        })()
    }, [addAlertPopUp, bookId])


    const onClickCoverImageUploadButton = (fileName, dataUrl) => {
        alert("Upload : "+ fileName + " / " + dataUrl.length)
        setCoverImageUrl(dataUrl)
    }

    const onClickGenerateCoverImageButton = (query) => {
        alert("Generate : "+ query)
    }
    

    const onClickDeleteBookButton = () => {
        alert("Delete")
    }

    const onClickEditBookTitleButton = (title) => {
        alert("Edit : "+ title)
    }

    
    const onClickAddTagButton = (title) => {
        alert("Add : "+ title)
    }

    const onClickGenerateTagsButton = (query) => {
        alert("Generate : "+ query)
    }


    const onClickAddIndexButton = (title) => {
        alert("Add : "+ title)
    }

    const onClickGenerateIndexesButton = (query) => {
        alert("Generate : "+ query)
    }

    
    if(!bookInfo.id) return <></>
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

                    <YesNoButton onClickYes={onClickDeleteBookButton} title="해당 책을 삭제시키겠습니까?">
                        <IconButton sx={{float: "right", marginTop: "-8px", marginRight: "-5px"}}>
                            <DeleteIcon sx={{fontSize: "30px", color: "black"}}/>
                            <BoldText sx={{float: "left", fontSize: "20px", marginTop: "5px"}}>삭제</BoldText>
                        </IconButton>
                    </YesNoButton>
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
                            src={(coverImageUrl && coverImageUrl.length > 0) ? coverImageUrl : "/src/NoImage.jpg"}
                        />
                        
                        <Box sx={{marginTop: "5px", marginLeft: "18px", marginBottom: "5px"}}>
                            <GenerateCoverImageButton onClickGenerateButton={onClickGenerateCoverImageButton} defaultQuery={"CoverImageQuery"}/>

                            <FileUploadButton accept="image/*" onUploadFile={onClickCoverImageUploadButton}>
                                <Box sx={{marginLeft: "5px", float: "left", backgroundColor: "cornflowerblue", width: "82px", height: "25px", padding: "8px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                                    <UploadIcon sx={{float: "left", color: "white"}}/>
                                    <NavText sx={{float: "left", marginTop: "2px", marginLeft: "5px"}}>업로드</NavText>
                                </Box>
                            </FileUploadButton>
                        </Box>
                    </Stack>

                    <Divider orientation="vertical" flexItem/>
                    <Stack sx={{float: "left", flex: "1 0 auto", paddingY: "5px", paddingLeft: "5px"}}>
                        <Box>
                            <TitleIcon sx={{float: "left", color: "black", fontSize: "27px", marginTop: "10px"}}/>
                            <BoldText sx={{float: "left", fontSize: "20px", marginTop: "10px"}}>제목: {bookInfo.title}</BoldText>

                            <EditBookTitleButton onClickEditButton={onClickEditBookTitleButton} defaultTitle={bookInfo.title}/>   
                        </Box>
                        <Divider sx={{marginTop: "5px"}}/>
                        
                        <Box sx={{marginTop: "5px"}}>
                            <LabelIcon sx={{float: "left", color: "black", fontSize: "27px", marginTop: "-1px"}}/>
                            <BoldText sx={{float: "left", fontSize: "20px", marginLeft: "2px"}}>태그</BoldText>

                            <GenerateTagsButton onClickGenerateButton={onClickGenerateTagsButton} defaultQuery={"tagQuery"}/>
                            <AddTagNameButton onClickAddButton={onClickAddTagButton}/>
                        </Box>
                        
                        {
                            bookInfo.rawTagInfos.map((rawTagInfo, index) => {
                                return <TagInfoBox key={index} rawTagInfo={rawTagInfo}/>
                            })  
                        }
                    </Stack>
                </Box>
                <Divider sx={{width: "100%"}}/>

                <Box sx={{width: "100%", height: "30px", marginTop: "5px"}}>
                    <ListIcon sx={{float: "left", marginTop: "5px"}}/>
                    <BoldText sx={{float: "left", fontSize: "20px", marginTop: "5px", marginLeft: "5px"}}>목차</BoldText>

                    <GenerateIndexesButton onClickGenerateButton={onClickGenerateIndexesButton} defaultQuery={"indexQuery"}/>
                    <AddIndexNameButton onClickAddButton={onClickAddIndexButton}/>
                </Box>
                <Stack sx={{width: "100%", marginTop: "16px"}}>
                    {
                        bookInfo.rawIndexInfos.map((rawIndexInfo, index) => {
                            return <IndexInfoBox key={index} rawIndexInfo={rawIndexInfo} priority={index+1}/>
                        })
                    }
                </Stack>
            </Container>
        </>
    )
}

export default BookManagePage;