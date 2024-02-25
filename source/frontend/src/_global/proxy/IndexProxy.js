import ServerProxy from './ServerProxy';

class IndexProxy {
    static async searchIndexAllByBookId(bookId) {
        return (await ServerProxy.request("get", "collectedData", `indexes/search/findByBookIdOrderByPriority?bookId=${bookId}&page=0&size=1000`)).data
    }


    static async createIndex(bookId, name, priority) {
        await ServerProxy.request("put", "index", "indexes/createIndex", {
            "bookId": bookId, 
            "name": name,
            "priority": priority
        })
    }
}

export default IndexProxy