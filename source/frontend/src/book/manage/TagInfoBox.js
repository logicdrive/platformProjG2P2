import React, { useState, useContext, useEffect } from 'react';
import { Box } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import BoldText from '../../_global/components/text/BoldText';
import YesNoButton from '../../_global/components/button/YesNoButton';
import EditTagNameButton from './EditTagNameButton';
import TagProxy from '../../_global/proxy/TagProxy';

const TagInfoBox = ({rawTagInfo, setIsBackdropOpened}) => {
    const {addAlertPopUp} = useContext(AlertPopupContext)
    const [tagInfo, setTagInfo] = useState({})
    useEffect(() => {
        (async () => {
            setTagInfo({
                id: rawTagInfo.tagId,
                name: rawTagInfo.name
            })
        })()
    }, [rawTagInfo])


    const onClickDeleteButton = async () => {
        try {

            setIsBackdropOpened(true)
            await TagProxy.deleteTag(tagInfo.id)
    
          } catch(error) {
            addAlertPopUp("태그를 삭제하는 도중에 오류가 발생했습니다!", "error")
            console.error("태그를 삭제하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }
    
    const onClickEditButton = async (title) => {
        try {

            setIsBackdropOpened(true)
            await TagProxy.editTag(tagInfo.id, title)
    
          } catch(error) {
            addAlertPopUp("태그 내용을 변경하는 도중에 오류가 발생했습니다!", "error")
            console.error("태그 내용을 변경하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }


    return (
        <>
        <Box sx={{margin: "5px", backgroundColor: "lightgray", padding: "5px", borderRadius: "5px"}}>
            <BoldText sx={{fontSize: "20px", display: "inline-block", color: "black", borderRadius: "5px", marginTop: "4px", marginLeft: "4px", cursor: "context-menu"}}>{tagInfo.name}</BoldText>

            <Box sx={{marginTop: "5px", float: "right", width: "25px", height: "25px", borderRadius: "5px", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                <YesNoButton onClickYes={onClickDeleteButton} title="해당 태그를 삭제시키겠습니까?">
                        <DeleteIcon sx={{float: "left", color: "gray"}}/>
                </YesNoButton>
            </Box>
            <EditTagNameButton onClickEditButton={onClickEditButton} defaultTitle={tagInfo.name}/>
        </Box>
        </>
    )
}

export default TagInfoBox;