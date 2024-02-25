import ServerProxy from './ServerProxy';

class LikeHistoryProxy {
    static async searchLikeHistoryAllByBookId(bookId) {
        return (await ServerProxy.request("get", "collectedData", `likeHistories/search/findByBookId?bookId=${bookId}&page=0&size=1000`)).data
    }
}

export default LikeHistoryProxy