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

    static async updateBookShelfTitle(bookShelfId, bookShelfTitle) {
        await ServerProxy.request("put", "bookShelf", `bookShelfs/updateBookShelfTitle`, {
            "bookShelfId": bookShelfId,
            "bookShelfTitle": bookShelfTitle
        })
    }


    static async deleteBookShelf(bookShelfId) {
        await ServerProxy.request("put", "bookShelf", `bookShelfs/deleteBookShelf`, {
            "bookShelfId": bookShelfId
        })
    }

    
    static async searchBookShelfAllByCreaterId(createrId, page=0, size=6) {
        return (await ServerProxy.request("get", "collectedData", `bookShelfs/search/findByCreaterIdOrderByTitle?createrId=${createrId}&page=${page}&size=${size}`)).data
    }

    static async searchBookShelfAllByCreaterIdAndTitle(createrId, title, page=0, size=6) {
        return (await ServerProxy.request("get", "collectedData", `bookShelfs/search/findByCreaterIdAndTitleContainingIgnoreCaseOrderByCreatedDate?createrId=${createrId}&title=${title}&page=${page}&size=${size}`)).data
    }


    static async searchBookShelfAllByIsShared(isShared, page=0, size=6) {
        return (await ServerProxy.request("get", "collectedData", `bookShelfs/search/findByIsSharedAndTitleContainingIgnoreCaseOrderByCreatedDateDesc?isShared=${isShared}&title=&page=${page}&size=${size}`)).data
    }
    
    static async searchBookShelfAllByIsSharedAndTitle(isShared, title, page=0, size=6) {
        return (await ServerProxy.request("get", "collectedData", `bookShelfs/search/findByIsSharedAndTitleContainingIgnoreCaseOrderByCreatedDateDesc?isShared=${isShared}&title=${title}&page=${page}&size=${size}`)).data
    }

    static async searchBookShelfAllByIsSharedAndCreaterId(isShared, createrId, page=0, size=6) {
        return (await ServerProxy.request("get", "collectedData", `bookShelfs/search/findByIsSharedAndCreaterIdOrderByCreatedDateDesc?isShared=${isShared}&createrId=${createrId}&page=${page}&size=${size}`)).data
    }


    static async searchBookShelfOneByBookShelfId(bookShelfId) {
        return (await ServerProxy.request("get", "collectedData", `bookShelfs/search/findByBookShelfId?bookShelfId=${bookShelfId}`)).data
    }
}

export default BookShelfProxy