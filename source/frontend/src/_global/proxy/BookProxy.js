import ServerProxy from './ServerProxy';

class BookProxy {
    static async createEmptyBook() {
        await ServerProxy.request("put", "book", "books/createEmptyBook")
    }


    static async searchBookAllByCreaterId(createrId, page, size=6) {
        return (await ServerProxy.request("get", "collectedData", `books/search/findByCreaterIdOrderByCreatedDateDesc?createrId=${createrId}&page=${page}&size=${size}`)).data
    }

    static async searchBookAllByCreaterIdAndTitle(createrId, title, page, size=6) {
        return (await ServerProxy.request("get", "collectedData", `books/search/findByCreaterIdAndTitleContainingIgnoreCaseOrderByCreatedDate?createrId=${createrId}&title=${title}&page=${page}&size=${size}`)).data
    }

    static async searchBookOneByBookId(bookId) {
        return (await ServerProxy.request("get", "collectedData", `books/search/findByBookId?bookId=${bookId}`)).data
    }
}

export default BookProxy