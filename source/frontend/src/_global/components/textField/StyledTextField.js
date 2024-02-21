import { TextField } from '@mui/material';
import { styled } from '@mui/system';

const StyledTextField = styled(TextField)({
    "& label.Mui-focused": {
        color: "blueviolet",
    },

    "& .MuiOutlinedInput-root": {
        "&.Mui-focused fieldset": {
            borderColor: "blueviolet",
        },
    }
});

export default StyledTextField;