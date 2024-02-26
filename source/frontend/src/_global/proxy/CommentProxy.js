import ServerProxy from './ServerProxy';

class CommentProxy {

    static async createComment(bookId, content) {
        await ServerProxy.request("put", "comment", "comments/createComment", {
            "bookId": bookId, 
            "content": content
        })
    }

}

export default CommentProxy