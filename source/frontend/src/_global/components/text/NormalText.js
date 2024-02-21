 import React from 'react';
import { Typography } from '@mui/material';

const NormalText = ({children, sx, ...props}) => {
    return (
        <Typography variant="body2" sx={{color: "black", fontWeight: "bolder", fontFamily: "BMHfont", ...sx}} {...props}>
            {children}
        </Typography>
    );
}

export default NormalText;