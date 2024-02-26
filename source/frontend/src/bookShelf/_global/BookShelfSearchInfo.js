import React, { useState, useEffect, useContext } from 'react';
import { useNavigate } from "react-router-dom";
import { Card, CardContent, Box, Stack } from '@mui/material';
import ShareIcon from '@mui/icons-material/Share';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import BoldText from '../../_global/components/text/BoldText';
import YesNoButton from '../../_global/components/button/YesNoButton';
import UserProxy from '../../_global/proxy/UserProxy';
import TimeTool from '../../_global/tool/TimeTool';
import TagProxy from '../../_global/proxy/TagProxy';
import BookProxy from '../../_global/proxy/BookProxy';
import FileProxy from '../../_global/proxy/FileProxy';
import BookShelfBookProxy from '../../_global/proxy/BookShelfBookProxy';
import BookShelfProxy from '../../_global/proxy/BookShelfProxy';

const BookShelfSearchInfo = ({rawBookShelfInfo, isEditIconVisible, setIsBackdropOpened}) => {
    const {addAlertPopUp} = useContext(AlertPopupContext)
    const navigate = useNavigate()
    const [bookShelfInfo, setBookShelfInfo] = useState({})
    useEffect(() => {
        (async () => {
            const createrData = await UserProxy.searchUserOneByUserId(rawBookShelfInfo.createrId)
            const BookShelfBookData = (await BookShelfBookProxy.searchBookShelfBooksByBookShelfId(rawBookShelfInfo.bookShelfId, 0, 3))._embedded.bookShelfBooks
            
            let tagDatas = []
            if(BookShelfBookData.length > 0) {
                tagDatas = (await TagProxy.searchAllTagByBookId(BookShelfBookData[0].bookId))._embedded.tags
                tagDatas = tagDatas.slice(0, 3)
            }
            
            let imageUrls = []
            if(BookShelfBookData.length > 0) {
                for(let i=0; i<BookShelfBookData.length; i++) {
                    const bookData = await BookProxy.searchBookOneByBookId(BookShelfBookData[i].bookId)
                    const imageUrl = (await FileProxy.searchFileOneByFileId(bookData.coverImageFileId)).url 
                    imageUrls.push(imageUrl)
                }
            }
    
            setBookShelfInfo({
                id: rawBookShelfInfo.bookShelfId,
                title: rawBookShelfInfo.title,
                creator: createrData.name,
                createdDate: TimeTool.prettyOnlyDateString(rawBookShelfInfo.createdDate),
                bookCount: rawBookShelfInfo.bookCount,
                tags: tagDatas.map((tagData) => tagData.name),
                isShared: rawBookShelfInfo.isShared,
                imageUrls: imageUrls
            })
        })()
    }, [rawBookShelfInfo])


    const onClickSharedButton = async (isShared) => {
        try {

            setIsBackdropOpened(true)
            await BookShelfProxy.updateIsShared(bookShelfInfo.id, isShared)
    
          } catch(error) {
            addAlertPopUp("책장의 공유 여부를 변경하는 도중에 오류가 발생했습니다!", "error")
            console.error("책장의 공유 여부를 변경하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }


    if(!bookShelfInfo.id) return <></>
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
                                                <ShareIcon sx={{color: "gray"}}/>
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

                    <Box>
                    {
                        bookShelfInfo.tags.map((tag, index) => {
                            return (
                                <BoldText key={index} sx={{fontSize: "10px", backgroundColor: "lightgray", padding: "5px", display: "inline-block", color: "gray", borderRadius: "5px", marginRight: "4px", cursor: "context-menu"}}>{tag}</BoldText>
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