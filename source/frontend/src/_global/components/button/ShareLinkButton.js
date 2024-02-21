import React, {useState} from 'react';
import { Button, Dialog, DialogTitle, DialogContent, DialogActions, Stack, Box } from '@mui/material';

import StyledTextField from '../textField/StyledTextField';

const ShareLinkButton = ({children, title, shareUrl, ...props}) => {
    const [isSharedLinkDialogOpend, setIsSharedLinkDialogOpend] = useState(false);

    return (
    <>
    <Box onClick={()=>{setIsSharedLinkDialogOpend(true);}}>
        {children}
    </Box>

    <Dialog open={isSharedLinkDialogOpend} onClose={()=>{setIsSharedLinkDialogOpend(false);}} {...props}>
        <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>{title}</DialogTitle>
        <DialogContent>
            <Stack>
                <StyledTextField
                    name="sharedLink"

                    margin="normal"
                    fullWidth

                    sx={{width: 400}}
                    value={shareUrl}
                />
            </Stack>
        </DialogContent>

        <DialogActions>
            <Button onClick={() => {
                setIsSharedLinkDialogOpend(false);
            }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>닫기</Button>
        </DialogActions>
    </Dialog>
    </>
    )
}

export default ShareLinkButton;