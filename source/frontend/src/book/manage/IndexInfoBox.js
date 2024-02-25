import React, { useState, useEffect, useContext } from 'react';
import { Box } from "@mui/material";
import DeleteIcon from '@mui/icons-material/Delete';

import { AlertPopupContext } from '../../_global/provider/alertPopUp/AlertPopUpContext';
import BoldText from '../../_global/components/text/BoldText';
import YesNoButton from '../../_global/components/button/YesNoButton';
import EditIndexNameButton from './EditIndexNameButton';
import GenerateContentButton from './GenerateContentButton';
import GenerateProblemButton from './GenerateProblemButton';
import ContentProxy from '../../_global/proxy/ContentProxy';
import IndexProxy from '../../_global/proxy/IndexProxy';
import ProblemProxy from '../../_global/proxy/ProblemProxy';

const IndexInfoBox = ({rawIndexInfo, priority, setIsBackdropOpened}) => {
    const {addAlertPopUp} = useContext(AlertPopupContext)
    const [indexInfo, setIndexInfo] = useState({})
    useEffect(() => {
        (async () => {
            setIndexInfo({
                id: rawIndexInfo.indexId,
                name: rawIndexInfo.name,
                isContentGenerated: (await ContentProxy.existsByIndexId(rawIndexInfo.indexId)),
                isProblemGenerated: ((await ProblemProxy.searchProblemAllByIndexId(rawIndexInfo.indexId))._embedded.problems.length > 0),
                priority: priority
            })
        })()
    }, [rawIndexInfo, priority])
    

    const onClickDeleteButton = async () => {
        try {

            setIsBackdropOpened(true)
            await IndexProxy.deleteIndex(indexInfo.id)
    
          } catch(error) {
            addAlertPopUp("인덱스를 삭제하는 도중에 오류가 발생했습니다!", "error")
            console.error("인덱스를 삭제하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }

    const onClickEditButton = async (title) => {
        try {

            setIsBackdropOpened(true)
            await IndexProxy.editIndex(indexInfo.id, title, indexInfo.priority)
    
          } catch(error) {
            addAlertPopUp("인덱스 내용을 변경하는 도중에 오류가 발생했습니다!", "error")
            console.error("인덱스 내용을 변경하는 도중에 오류가 발생했습니다!", error)
            setIsBackdropOpened(false)
        }
    }


    const onClickGenerateContentButton = (query) => {
        alert("Generate : " + query)
    }

    const onClickGenerateProblemButton = (query) => {
        alert("Generate : " + query)
    }


    if(!indexInfo.id) return <></>
    return (
        <Box sx={{backgroundColor: "lightgray", borderRadius: "5px", padding: "5px"}}>
            <BoldText sx={{fontSize: "20px", float: "left", marginTop: "2px", marginLeft: "3px"}}>{indexInfo.name}</BoldText>
            
            <Box sx={{marginTop: "3px"}}>
                <Box sx={{float: "right", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                    <YesNoButton onClickYes={onClickDeleteButton} title="해당 목차를 삭제시키겠습니까?">
                        <DeleteIcon sx={{float: "left", color: "gray"}}/>
                    </YesNoButton>
                </Box>
                
                <EditIndexNameButton onClickEditButton={onClickEditButton} defaultTitle={indexInfo.name}/>
                <GenerateProblemButton isGenerated={indexInfo.isProblemGenerated} onClickGenerateButton={onClickGenerateProblemButton} defaultQuery={"problemQuery"}/>
                <GenerateContentButton isGenerated={indexInfo.isContentGenerated} onClickGenerateButton={onClickGenerateContentButton} defaultQuery={"contentQuery"}/>
            </Box>
        </Box>
    )
}

export default IndexInfoBox;