import { Slider } from '@mui/material';
import { styled } from '@mui/system';

const StyledSlider = styled(Slider)({
    "&&&": {
        "color": "midnightblue"
    },


    "& .MuiSlider-thumb": {
        width: 8,
        height: 8,

        "&.Mui-active": {
            width: 12,
            height: 12
        }
    },


    "& .MuiSlider-thumb:hover": {
        "boxShadow": "0 0 0 10px #8a2be21e !important"
    },

    "& .MuiSlider-thumb.Mui-focusVisible": {
        "boxShadow": "0 0 0 8px #8a2be21e !important"
    },

    "& .MuiSlider-thumb.Mui-active": {
        "boxShadow": "0 0 0 8px #8a2be21e !important"
    }
});

export default StyledSlider;