import ServerProxy from './ServerProxy';

class BookProxy {
    static async createEmptyBook() {
        await ServerProxy.request("put", "book", "books/createEmptyBook")
    }


    static async searchBookAllByCreaterId(createrId, page, size=6) {
        return (await ServerProxy.request("get", "collectedData", `books/search/findByCreaterIdOrderByCreatedDateDesc?createrId=${createrId}&page=${page}&size=${size}`)).data
    }
}

export default BookProxy