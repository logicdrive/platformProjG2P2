import React, {useState} from 'react';
import { Button, Box, Dialog, DialogTitle, DialogActions } from '@mui/material';

const YesNoButton = ({title, onClickYes, children, ...props}) => {
    const [isSharedLinkDialogOpend, setIsSharedLinkDialogOpend] = useState(false);

    return (
    <>
    <Box onClick={()=>{setIsSharedLinkDialogOpend(true);}}>
        {children}
    </Box>

    <Dialog open={isSharedLinkDialogOpend} onClose={()=>{setIsSharedLinkDialogOpend(false);}} {...props}>
        <DialogTitle sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont", width: "400px"}}>{
              title.split('\\n').map((textLine, key) => {
                return <p key={key}>{textLine}</p>;
              })
        }</DialogTitle>

        <DialogActions>
            <Button onClick={() => {
                setIsSharedLinkDialogOpend(false);
            }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>아니오</Button>
            <Button onClick={() => {
                setIsSharedLinkDialogOpend(false);
                onClickYes();
            }} sx={{color: "black", fontWeight: "bolder", fontFamily: "BMDfont"}}>네</Button>
        </DialogActions>
    </Dialog>
    </>
    )
}

export default YesNoButton;