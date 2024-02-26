import ServerProxy from './ServerProxy';

class RecommendBookToUserProxy {
    static async searchRecommendBookToUserAllByUserId(userId, page=0, size=6) {
        return (await ServerProxy.request("get", "collectedData", `recommendBookToUsers/search/findByUserIdOrderByPriority?userId=${userId}&page=${page}&size=${size}`)).data
    }
}

export default RecommendBookToUserProxy