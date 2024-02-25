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

    static async generateIndexes(bookId, query) {
        await ServerProxy.request("put", "index", "indexes/generateIndexes", {
            "bookId": bookId,
            "query": query
        })
    }


    static async editIndex(indexId, indexName, indexPriority) {
        await ServerProxy.request("put", "index", "indexes/editIndex", {
            "indexId": indexId, 
            "indexName": indexName,
            "indexPriority": indexPriority
        })
    }


    static async deleteIndex(indexId) {
        await ServerProxy.request("put", "index", "indexes/deleteIndex", {
            "indexId": indexId
        })
    }
}

export default IndexProxy