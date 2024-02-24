import React from 'react';

import { Box } from "@mui/material";

import BookSearchInfo from '../../_global/components/card/BookSearchInfo';

const RelatedBookInfosBox = ({rawBookInfos}) => {
    return (
        <Box sx={{display: "flex", justifyContent: "center"}}>
            {
                rawBookInfos.slice(0, 2).map((rawBookInfo, index) => {
                    return (
                        <BookSearchInfo
                            key={index}
                            rawBookInfo={rawBookInfo}
                            isEditIconVisible={true}
                            sx={{margin: "5px"}}
                        />
                    )
                })
            }
        </Box>
    )
}

export default RelatedBookInfosBox;