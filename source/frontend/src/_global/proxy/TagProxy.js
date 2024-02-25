import ServerProxy from './ServerProxy';

class TagProxy {
    static async searchAllTagByBookId(bookId) {
        return (await ServerProxy.request("get", "collectedData", `tags/search/findByBookIdOrderByName?bookId=${bookId}&page=0&size=1000`)).data
    }


    static async createTag(bookId, name) {
        await ServerProxy.request("put", "tag", "tags/createTag", {
            "bookId": bookId, 
            "name": name
        })
    }


    static async editTag(tagId, tagName) {
        await ServerProxy.request("put", "tag", "tags/editTag", {
            "tagId": tagId, 
            "tagName": tagName
        })
    }


    static async deleteTag(tagId) {
        await ServerProxy.request("put", "tag", "tags/deleteTag", {
            "tagId": tagId
        })
    }
}

export default TagProxy