import ServerProxy from './ServerProxy';

class RecommenedBookToBookProxy {
    static async searchRecommendBookToBookAllByBookId(bookId, page=0, size=2) {
        return (await ServerProxy.request("get", "collectedData", `recommenedBookToBooks/search/findByBookIdOrderByPriority?bookId=${bookId}&page=${page}&size=${size}`)).data
    }
}

export default RecommenedBookToBookProxy