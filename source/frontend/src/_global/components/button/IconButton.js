// Nav에 대한 통합적인 버튼 형식

import React from 'react';
import { Link, Button, Box } from '@mui/material';
import NavText from '../text/NavText';

const IconButton = ({children, onClick, sx, buttonSx, textSx, ...props}) => {
    return (
        <Link sx={{...sx}} {...props}>
            <Button onClick={onClick} sx={{
                width: "50px", minWidth: "50px", height: "50px", minHeight: "50px", backgroundColor: "darkblue", ...buttonSx,
                "&:hover": {opacity: 0.80}
                }}>
                <Box sx={{position: "relative", top: "4px", color: "white", fontWeight: "bolder", fontFamily: "BMDfont", ...textSx}}>
                    {children}
                </Box>
            </Button>
        </Link>
    );
}

export default IconButton;