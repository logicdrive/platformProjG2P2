import ServerProxy from './ServerProxy';

class TagProxy {
    static async searchAllTagByBookId(bookId) {
        return (await ServerProxy.request("get", "collectedData", `tags/search/findByBookIdOrderByName?bookId=${bookId}&page=0&size=1000`)).data
    }
}

export default TagProxy