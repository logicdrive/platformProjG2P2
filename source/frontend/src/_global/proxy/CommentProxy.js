import ServerProxy from './ServerProxy';

class CommentProxy {

    static async createComment(bookId, content) {
        await ServerProxy.request("put", "comment", "comments/createComment", {
            "bookId": bookId, 
            "content": content
        })
    }


    static async searchCommentAllByBookId(bookId, page, size=5) {
        return (await ServerProxy.request("get", "collectedData", `comments/search/findByBookIdOrderByCreatedDateDesc?bookId=${bookId}&page=${page}&size=${size}`)).data
    }

} 

export default CommentProxy