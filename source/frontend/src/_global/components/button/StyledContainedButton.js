import { Button } from '@mui/material';
import { styled } from '@mui/system';

const StyledContainedButton = styled(Button)({
    "&&&": {
        backgroundColor: "midnightblue",
        color: "white"
    }
});

export default StyledContainedButton;