import ServerProxy from './ServerProxy';

class BookProxy {
    static async createEmptyBook() {
        await ServerProxy.request("put", "book", "books/createEmptyBook")
    }
}

export default BookProxy