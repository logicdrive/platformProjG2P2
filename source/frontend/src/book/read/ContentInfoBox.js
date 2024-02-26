import React, { useState, useEffect } from 'react';

import { Box } from "@mui/material";

import NormalText from '../../_global/components/text/NormalText';
import FileProxy from '../../_global/proxy/FileProxy';
import DictionaryTool from '../../_global/tool/DictionaryTool';

const ContentInfoBox = ({rawContentInfo}) => {
    const [contentInfo, setContentInfo] = useState({})
    useEffect(() => {
        (async () => {
            if(DictionaryTool.isEmpty(rawContentInfo)) return

            const fileData = await FileProxy.searchFileOneByFileId(rawContentInfo.imageFileId)
            setContentInfo({
                id: rawContentInfo.contentId,
                imageUrl: fileData.url,
                content: rawContentInfo.content
            })
        })()
    }, [rawContentInfo])


    return (
        <>
            <Box
                component="img"
                sx={{
                    height: 250,
                    width: 500,
                    backgroundColor: "lightgray",
                    borderRadius: 3,
                    border: "1px solid lightgray",
                    float:"left",
                    marginX: "auto"

                }}
                alt="업로드된 이미지가 표시됩니다."
                src={(contentInfo.imageUrl) ? contentInfo.imageUrl : "/src/NoImage.jpg"}
            />
            
            <NormalText sx={{width: "840px", fontSize: "15px", marginTop: "5px", textOverflow: "ellipsis"}}>
                {((contentInfo.content) ? contentInfo.content : "아직 내용이 생성되지 않았습니다!")}
            </NormalText>
        </>
    )
}

export default ContentInfoBox;