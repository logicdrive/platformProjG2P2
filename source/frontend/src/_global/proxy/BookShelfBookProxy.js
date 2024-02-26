import ServerProxy from './ServerProxy';

class BookShelfBookProxy {
    static async addBookShelfBook(bookShelfId, bookId) {
        await ServerProxy.request("put", "bookShelfBook", "bookShelfBooks/addBookShelfBook", {
            "bookShelfId": bookShelfId,
            "bookId": bookId
        })
    }


    static async deleteBookShelfBook(bookShelfBookId) {
        await ServerProxy.request("put", "bookShelfBook", "bookShelfBooks/deleteBookShelfBook", {
            "bookShelfBookId": bookShelfBookId
        })
    }
    
    
    static async searchBookShelfBooksByBookShelfId(bookShelfId, page=0, size=6) {
        return (await ServerProxy.request("get", "collectedData", `bookShelfBooks/search/findByBookShelfIdOrderByCreatedDateDesc?bookShelfId=${bookShelfId}&page=${page}&size=${size}`)).data
    }
}

export default BookShelfBookProxy