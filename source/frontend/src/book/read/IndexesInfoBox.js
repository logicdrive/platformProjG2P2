import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { Stack } from "@mui/material";

import NormalText from '../../_global/components/text/NormalText';

const IndexesInfoBox = ({bookId, rawIndexInfos, focusedIndex}) => {
    const navigate = useNavigate();
    const [indexInfos] = useState(
        rawIndexInfos.map((indexInfo) => {
            return {
                id: indexInfo.id,
                title: indexInfo.title
            }
        })
    )

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