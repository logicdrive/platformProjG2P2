import React, { useState } from 'react';

import { Box } from "@mui/material";

import NormalText from '../../_global/components/text/NormalText';

const ContentInfoBox = ({rawContentInfo}) => {
    const [contentInfo] = useState({
        id: rawContentInfo.id,
        imageUrl: rawContentInfo.imageUrl,
        content: rawContentInfo.content
    })

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
                src={contentInfo.imageUrl}
            />
            
            <NormalText sx={{width: "840px", fontSize: "15px", marginTop: "5px", textOverflow: "ellipsis"}}>
                {contentInfo.content}
            </NormalText>
        </>
    )
}

export default ContentInfoBox;