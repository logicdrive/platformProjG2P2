import React from 'react';
import { Box, Container, Toolbar, Link, AppBar } from '@mui/material';
import { useNavigate } from "react-router-dom";

const TopAppBar = ({children, title, titleLink}) => {
    const navigate = useNavigate()

    return (
        <AppBar position="static" style={{backgroundColor:"darkblue"}}>
            <Container maxWidth="lg">
                <Toolbar disableGutters>
                    <Link onClick={()=>{navigate(titleLink)}} variant="h5" underline="none" sx={{color: "white", fontWeight: "bolder", fontFamily: "BMDfont", cursor: "pointer", "&:hover": {opacity: 0.80}}}>
                        {title}
                    </Link>
                    <Box sx={{flexGrow: 1}}></Box>

                    {children}

                </Toolbar>
            </Container>
        </AppBar>
    );
}

export default TopAppBar;