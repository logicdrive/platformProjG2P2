// Nav에 대한 통합적인 버튼 형식

import React from 'react';
import { Link, Button } from '@mui/material';
import NavText from '../text/NavText';

const IconButton = ({children, onClick, sx, buttonSx, textSx, ...props}) => {
    return (
        <Link sx={{...sx}} {...props}>
            <Button onClick={onClick} sx={{
                width: "50px", minWidth: "50px", height: "50px", minHeight: "50px", backgroundColor: "darkblue", ...buttonSx,
                "&:hover": {opacity: 0.80}
                }}>
                <NavText sx={{position: "relative", top: "4px", ...textSx}}>
                    {children}
                </NavText>
            </Button>
        </Link>
    );
}

export default IconButton;