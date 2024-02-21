import { Button } from '@mui/material';
import { styled } from '@mui/system';

const StyledContainedButton = styled(Button)({
    "&&&": {
        backgroundColor: "blueviolet",
        color: "white"
    }
});

export default StyledContainedButton;