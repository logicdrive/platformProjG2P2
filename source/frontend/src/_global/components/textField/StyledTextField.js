import { TextField } from '@mui/material';
import { styled } from '@mui/system';

const StyledTextField = styled(TextField)({
    "& label.Mui-focused": {
        color: "midnightblue",
    },

    "& .MuiOutlinedInput-root": {
        "&.Mui-focused fieldset": {
            borderColor: "midnightblue",
        },
    }
});

export default StyledTextField;