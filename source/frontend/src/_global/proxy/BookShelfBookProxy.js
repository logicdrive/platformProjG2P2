import ServerProxy from './ServerProxy';

class BookShelfBookProxy {
    static async searchBookShelfBooksByBookShelfId(bookShelfId, page=0, size=6) {
        return (await ServerProxy.request("get", "collectedData", `bookShelfBooks/search/findByBookShelfIdOrderByCreatedDateDesc?bookShelfId=${bookShelfId}&page=${page}&size=${size}`)).data
    }
}

export default BookShelfBookProxy