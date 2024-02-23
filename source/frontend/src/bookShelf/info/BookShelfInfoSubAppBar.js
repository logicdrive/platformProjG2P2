import React, {useState} from 'react';
import { Box, Paper, InputBase, MenuItem, Select, IconButton } from "@mui/material";
import SearchIcon from '@mui/icons-material/Search';
import EditIcon from '@mui/icons-material/Edit';

import BoldText from '../../_global/components/text/BoldText';

const BookShelfInfoSubAppBar = ({bookShelfTitle, handleOnSubmit, searchTypes, sx, ...props}) => {
    const [searchText, setSearchText] = useState("")
    const [searchType, setSearchType] = useState(searchTypes[0].type)

    return (
        <>
            <Box sx={{width: "100%", height: "50px", padding: "10px", marginTop: "5px", ...sx}} {...props}>
                <BoldText sx={{float: "left", fontSize: "20px", marginTop: "5px"}}>책장: {bookShelfTitle}</BoldText>
                <IconButton onClick={(e)=>{e.stopPropagation(); alert("Edit")}}>
                        <EditIcon sx={{fontSize: "20px"}}/> 
                </IconButton>

                
                <Paper component="form" sx={{float:"right", width: "397px", height: "35px", marginLeft: "5px", marginTop: "2px", ...sx}} {...props}>
                    <Select
                        sx={{
                            height: "35px", width: "125px", paddingLeft: "10px"
                        }}
                        value={searchType}
                        label="검색 대상"
                        name="검색 대상"
                        onChange={(e)=>{setSearchType(e.target.value)}}
                        disableUnderline={true}
                        variant="standard"
                    >
                        {
                            searchTypes.map((searchType, index) => {
                                return <MenuItem key={index} value={searchType.type}>{searchType.name}</MenuItem>
                            })
                        }
                    </Select>

                    <InputBase sx={{marginLeft: "10px", width: "217px"}} value={searchText} onChange={(e)=>{setSearchText(e.target.value)}}/>
                    <Box onClick={()=>{handleOnSubmit(searchText, searchType)}} 
                        sx={{
                            marginLeft: "10px", float:"right", height: "35px", minHeight: "35px", width: "35px", minWidth: "35px",
                            borderRadius: "0px 5px 5px 0px", backgroundColor: "royalblue", "&:hover": {opacity: 0.80}, color: "white",
                            cursor: "pointer"
                    }}>
                        <SearchIcon sx={{
                                float:"right", height: "30px", minHeight: "30px", width: "30px", minWidth: "30px",
                                marginTop: "2.5px", marginRight: "2.5px"
                        }}></SearchIcon>
                    </Box>
                </Paper>
            </Box>
        </>
    )
}

export default BookShelfInfoSubAppBar;