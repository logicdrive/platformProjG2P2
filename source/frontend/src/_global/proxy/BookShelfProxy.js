import ServerProxy from './ServerProxy';

class BookShelfProxy {
    static async createBookShelf(title, isShared=false) {
        await ServerProxy.request("put", "bookShelf", "bookShelfs/createBookShelf", {
            "title": title, 
            "isShared": isShared
        })
    }


    static async updateIsShared(bookShelfId, isShared) {
        await ServerProxy.request("put", "bookShelf", `bookShelfs/updateIsShared`, {
            "bookShelfId": bookShelfId,
            "isShared": isShared
        })
    }

    
    static async searchBookShelfAllByCreaterId(createrId, page=0, size=6) {
        return (await ServerProxy.request("get", "collectedData", `bookShelfs/search/findByCreaterIdOrderByTitle?createrId=${createrId}&page=${page}&size=${size}`)).data
    }

    static async searchBookShelfAllByCreaterIdAndTitle(createrId, title, page=0, size=6) {
        return (await ServerProxy.request("get", "collectedData", `bookShelfs/search/findByCreaterIdAndTitleContainingIgnoreCaseOrderByCreatedDate?createrId=${createrId}&title=${title}&page=${page}&size=${size}`)).data
    }
}

export default BookShelfProxy