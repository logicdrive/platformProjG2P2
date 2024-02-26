import ServerProxy from './ServerProxy';

class BookProxy {
    static async createEmptyBook() {
        await ServerProxy.request("put", "book", "books/createEmptyBook")
    }

    static async generateCoverImage(bookId) {
        await ServerProxy.request("put", "book", "books/generateCoverImage", {
            "bookId": bookId,
        })
    }


    static async updateBookTitle(bookId, title) {
        await ServerProxy.request("put", "book", "books/updateBookTitle", {
            "bookId": bookId, 
            "bookTitle": title
        })
    }

    static async updateCoverImage(bookId, imageUrl) {
        await ServerProxy.request("put", "book", "books/updateCoverImage", {
            "bookId": bookId, 
            "imageUrl": imageUrl
        })
    }

    static async likeBook(bookId) {
        await ServerProxy.request("put", "book", "books/likeBook", {
            "bookId": bookId
        })
    }

    static async updateIsShared(bookId, isShared) {
        await ServerProxy.request("put", "book", "books/updateIsShared", {
            "bookId": bookId,
            "isShared": isShared
        })
    }


    static async deleteBook(bookId) {
        await ServerProxy.request("put", "book", "books/deleteBook", {
            "bookId": bookId
        })
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


    static async searchBookAllByIsShared(isShared, page, size=6) {
        return (await ServerProxy.request("get", "collectedData", `books/search/findByIsSharedOrderByCreatedDateDesc?isShared=${isShared}&page=${page}&size=${size}`)).data
    }
}

export default BookProxy