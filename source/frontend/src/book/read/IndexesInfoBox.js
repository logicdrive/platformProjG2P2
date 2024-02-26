import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

import { Stack } from "@mui/material";

import NormalText from '../../_global/components/text/NormalText';
import DictionaryTool from '../../_global/tool/DictionaryTool';

const IndexesInfoBox = ({bookId, rawIndexInfos, focusedIndex}) => {
    const navigate = useNavigate();
    const [indexInfos, setIndexInfos] = useState([])
    useEffect(() => {
        (async () => {
            if(DictionaryTool.isEmpty(rawIndexInfos)) return

            setIndexInfos(rawIndexInfos.map((indexInfo) => {
                return {
                    id: indexInfo.indexId,
                    title: indexInfo.name
                }
            }))
        })()
    }, [rawIndexInfos])

    return (
        <Stack
        spacing={0.5}
        sx={{marginTop: "1px", marginLeft: "10px"}}
        >
            {
                indexInfos.map((indexInfo, index) => {
                    return (
                        (index === (Number(focusedIndex)-1)) ? (
                            <NormalText key={index} onClick={()=>{navigate(`/book/read/${bookId}/${indexInfo.id}`)}} sx={{fontSize: "15px", "&:hover": {opacity: 0.50}, cursor: "pointer", color: "cornflowerblue"}}>{index+1}. {indexInfo.title}</NormalText>
                        ) : (
                            <NormalText key={index} onClick={()=>{navigate(`/book/read/${bookId}/${indexInfo.id}`)}} sx={{fontSize: "15px", "&:hover": {opacity: 0.50}, cursor: "pointer"}}>{index+1}. {indexInfo.title}</NormalText>
                        )
                    )
                })
            }
        </Stack>
    )
}

export default IndexesInfoBox;