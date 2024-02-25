import axios from 'axios';
import ServerProxy from './ServerProxy';

class UserProxy {
    static async signUp(email, password, name) {

        await ServerProxy.request("put", "user", "users/signUp", {
            "email": email,
            "password": password,
            "name": name
        })

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