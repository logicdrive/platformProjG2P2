import axios from 'axios';

class UserProxy {
    static async signUp(email, password, name) {
        console.log(`[EFFECT] signUp : <email:${email}, password:${password}, name:${name}>`)
        
        const reqDto = {
            "email": email,
            "password": password,
            "name": name
        }
        const response = await axios.put(`http://${window.location.host}/api/user/users/signUp`, reqDto);
        
        console.log(response)
    }

    static async signIn(email, password) {
        console.log(`[EFFECT] signIn : <email:${email}, password:${password}>`)
        
        const reqDto = {
            "email": email,
            "password": password
        }
        const jwtToken = (await axios.put(`http://${window.location.host}/api/user/users/signIn`, reqDto)).headers.authorization;
    
        console.log("jwtToken :" + jwtToken);
        return jwtToken;
    }


    static async updateName(nameToUpdate, jwtTokenState) {
        console.log(`[EFFECT] updateName : <nameToUpdate:${nameToUpdate}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const reqDto = {
            "name": nameToUpdate
        }
        const response = await axios.put(`http://${window.location.host}/api/user/users/updateName`, reqDto, requestHeader);
        
        console.log(response)
    }


    static async searchUserOneByUserId(userId, jwtTokenState) {
        console.log(`[EFFECT] searchUserOneByUserId : <userId:${userId}>`)

        const requestHeader = {headers: {Authorization: jwtTokenState.jwtToken.Authorization}};
        const response = await axios.get(`http://${window.location.host}/api/collectedData/users/search/findByUserId?userId=${userId}`, requestHeader);
        
        console.log(response)
        return response.data
    }
}

export default UserProxy