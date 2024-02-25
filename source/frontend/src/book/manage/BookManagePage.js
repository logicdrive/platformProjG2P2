import React, { useState, useEffect, useContext } from 'react';
import { useParams, useNavigate } from 'react-router-dom';

import { Box, IconButton, Divider, Container, Stack, Backdrop, CircularProgress } from "@mui/material";
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
import SubscribeMessageCreatedSocket from '../../_global/socket/EventHandlerSocket';

const BookManagePage = () => {
    const navigate = useNavigate()
    const {addAlertPopUp} = useContext(AlertPopupContext)
    const {bookId} = useParams()
    const [isBackdropOpened, setIsBackdropOpened] = useState(false)

    const [coverImageUrl, setCoverImageUrl] = useState("")
    const [bookInfo, setBookInfo] = useState({})
    const [loadBookInfo] = useState(() => {
        return async () => {
            try {
    
                const rawBookInfo = await BookProxy.searchBookOneByBookId(bookId)
                const rawTagInfos = (await TagProxy.searchAllTagByBookId(rawBookInfo.bookId))._embedded.tags
                const rawIndexInfos = (await IndexProxy.searchIndexAllByBookId(rawBookInfo.bookId))._embedded.indexes
                const fileData = await FileProxy.searchFileOneByFileId(rawBookInfo.bookId)
    
const tagQuery = `Title: ${rawBookInfo.title}
Indexes
${rawIndexInfos.map((rawIndexInfo, index) => {
    return `${index+1}. ${rawIndexInfo.name}`
  }).join("\n")}
`
const indexQuery = rawBookInfo.title

                setBookInfo({
                    id: rawBookInfo.bookId,
                    title: rawBookInfo.title,
                    imageUrl: fileData.url,
                    rawTagInfos: rawTagInfos,
                    rawIndexInfos: rawIndexInfos,

                    tagQuery: tagQuery,
                    indexQuery: indexQuery
                })
                setCoverImageUrl(fileData.url)
    
            } catch (error) {
                addAlertPopUp("책 정보를 가져오는 과정에서 오류가 발생했습니다!", "error");
                console.error("책 정보를 가져오는 과정에서 오류가 발생했습니다!", error);
            }
        }
    })
    useEffect(() => {
        loadBookInfo()
    }, [addAlertPopUp, bookId, loadBookInfo])


    const onClickCoverImageUploadButton = async (fileName, dataUrl) => {
        try {

            setIsBackdropOpened(true)
            await BookProxy.updateCoverImage(bookId, dataUrl)
    
          } catch(error) {
            addAlertPopUp("책 표지 이미지를 변경하는 도중에 오류가 발생했습니다!", "error")
            console.error("책 표지 이미지를 변경하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }

    const onClickGenerateCoverImageButton = async () => {
        try {

            setIsBackdropOpened(true)
            await BookProxy.generateCoverImage(bookId)
    
          } catch(error) {
            addAlertPopUp("책 표지 이미지를 생성하는 도중에 오류가 발생했습니다!", "error")
            console.error("책 표지 이미지를 생성하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }
    

    const onClickDeleteBookButton = async () => {
        try {

            setIsBackdropOpened(true)
            await BookProxy.deleteBook(bookId)
    
          } catch(error) {
            addAlertPopUp("책을 삭제하는 도중에 오류가 발생했습니다!", "error")
            console.error("책을 삭제하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }

    const onClickEditBookTitleButton = async (title) => {
        try {

            setIsBackdropOpened(true)
            await BookProxy.updateBookTitle(bookId, title)
    
          } catch(error) {
            addAlertPopUp("책 제목을 변경하는 도중에 오류가 발생했습니다!", "error")
            console.error("책 제목을 변경하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }

    
    const onClickAddTagButton = async (title) => {
        try {

            setIsBackdropOpened(true)
            await TagProxy.createTag(bookId, title)
    
          } catch(error) {
            addAlertPopUp("태그를 추가하는 도중에 오류가 발생했습니다!", "error")
            console.error("태그를 추가하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }

    const onClickGenerateTagsButton = async (query) => {
        try {

            setIsBackdropOpened(true)
            await TagProxy.generateTags(bookId, query)

          } catch(error) {
            addAlertPopUp("AI를 기반으로 태그를 생성하는 도중에 오류가 발생했습니다!", "error")
            console.error("AI를 기반으로 태그를 생성하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }


    const onClickAddIndexButton = async (title) => {
        try {

            setIsBackdropOpened(true)
            await IndexProxy.createIndex(bookId, title, bookInfo.rawIndexInfos.length+1)
    
          } catch(error) {
            addAlertPopUp("인덱스를 추가하는 도중에 오류가 발생했습니다!", "error")
            console.error("인덱스를 추가하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }

    const onClickGenerateIndexesButton = async (query) => {
        try {

            setIsBackdropOpened(true)
            await IndexProxy.generateIndexes(bookId, query)

          } catch(error) {
            addAlertPopUp("AI를 기반으로 인덱스를 생성하는 도중에 오류가 발생했습니다!", "error")
            console.error("AI를 기반으로 인덱스를 생성하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }


    SubscribeMessageCreatedSocket(useState(() => {
        return (eventName) => {
        
            let successLog = ""
            if(eventName === "BookTitleUpdated") successLog = "책 제목이 정상적으로 수정되었습니다."
            else if(eventName === "BookDeleted")
            {
                successLog = "책이 정상적으로 삭제되었습니다."
                addAlertPopUp(successLog, "success")
                setIsBackdropOpened(false)
                navigate("/book/myList")
                return
            }

            else if(eventName === "CoverImageInfoUpdated") successLog = "책 표지 이미지가 정상적으로 수정되었습니다."

            else if(eventName === "TagCreated") successLog = "태그가 추가되었습니다."
            else if(eventName === "TagEdited") successLog = "태그가 수정되었습니다."
            else if(eventName === "tagDeleted") successLog = "태그가 삭제되었습니다."

            else if(eventName === "IndexCreated") successLog = "인덱스가 추가되었습니다."
            else if(eventName === "IndexEdited") successLog = "인덱스가 수정되었습니다."
            else if(eventName === "IndexDeleted") successLog = "인덱스가 삭제되었습니다."

            else if(eventName === "ContentUpdated") successLog = "인덱스 내용이 정상적으로 수정되었습니다."
            else if(eventName === "ProblemCreated") successLog = "문제가 정상적으로 생성되었습니다."

            if(successLog.length > 0)
            {
                addAlertPopUp(successLog, "success")
                setIsBackdropOpened(false)
                loadBookInfo()
            }

        }
    })[0])

    
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
                            <GenerateCoverImageButton onClickGenerateButton={onClickGenerateCoverImageButton}/>

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

                            <GenerateTagsButton onClickGenerateButton={onClickGenerateTagsButton} defaultQuery={bookInfo.tagQuery}/>
                            <AddTagNameButton onClickAddButton={onClickAddTagButton} defaultTitle=""/>
                        </Box>

                        <Box sx={{display: "flex", flexDirection: "row", width: "630px", flexWrap: "wrap", marginTop: "10px", marginLeft: "-3px"}}>
                        {
                            bookInfo.rawTagInfos.map((rawTagInfo, index) => {
                                return <TagInfoBox key={index} rawTagInfo={rawTagInfo} setIsBackdropOpened={setIsBackdropOpened}/>
                            })  
                        }          
                        </Box>
                    </Stack>
                </Box>
                <Divider sx={{width: "100%"}}/>

                <Box sx={{width: "100%", height: "30px", marginTop: "5px"}}>
                    <ListIcon sx={{float: "left", marginTop: "5px"}}/>
                    <BoldText sx={{float: "left", fontSize: "20px", marginTop: "5px", marginLeft: "5px"}}>목차</BoldText>

                    <GenerateIndexesButton onClickGenerateButton={onClickGenerateIndexesButton} defaultQuery={bookInfo.indexQuery}/>
                    <AddIndexNameButton onClickAddButton={onClickAddIndexButton} defaultTitle=""/>
                </Box>
                <Stack sx={{width: "100%", marginTop: "16px"}} spacing={1}>
                    {
                        bookInfo.rawIndexInfos.map((rawIndexInfo, index) => {
                            return <IndexInfoBox key={index} rawIndexInfo={rawIndexInfo} priority={index+1} bookTitle={bookInfo.title} setIsBackdropOpened={setIsBackdropOpened}/>
                        })
                    }
                </Stack>
            </Container>

            <Backdrop
                sx={{ color: '#fff', zIndex: (theme) => theme.zIndex.drawer + 1 }}
                open={isBackdropOpened}
            >
                <CircularProgress color="inherit" />
            </Backdrop>
        </>
    )
}

export default BookManagePage;