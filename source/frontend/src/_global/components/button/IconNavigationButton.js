import React from 'react';
import IconButton from './IconButton';
import { useNavigate } from 'react-router-dom';

const IconNavigationButton = ({children, url, sx, ...props}) => {
    const navigate = useNavigate();

    return (
        <IconButton onClick={() => {navigate(url)}} sx={{...sx}} {...props}>
            {children}
        </IconButton>
    );
}

export default IconNavigationButton;