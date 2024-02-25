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
        const jwtToken = (await ServerProxy.request("put", "user", "users/signIn", {
            "email": email,
            "password": password
        })).headers.authorization
    
        console.log("jwtToken :" + jwtToken);
        return jwtToken; 
    }


    static async updateName(nameToUpdate) {
        await ServerProxy.request("put", "user", "users/updateName", {
            "name": nameToUpdate
        })
    }


    static async searchUserOneByUserId(userId) {
        return (await ServerProxy.request("get", "collectedData", `users/search/findByUserId?userId=${userId}`)).data
    }
}

export default UserProxy