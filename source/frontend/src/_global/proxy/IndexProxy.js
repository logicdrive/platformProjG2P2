import ServerProxy from './ServerProxy';

class IndexProxy {
    static async searchIndexAllByBookId(bookId) {
        return (await ServerProxy.request("get", "collectedData", `indexes/search/findByBookIdOrderByPriority?bookId=${bookId}&page=0&size=1000`)).data
    }
}

export default IndexProxy