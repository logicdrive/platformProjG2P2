import ServerProxy from './ServerProxy';

class ContentProxy {
    static async generateContent(indexId, query) {
        await ServerProxy.request("put", "content", "contents/generateContent", {
            "indexId": indexId,
            "query": query
        })
    }


    static async searchContentOneByIndexId(indexId) {
        return (await ServerProxy.request("get", "collectedData", `contents/search/findByIndexId?indexId=${indexId}`)).data
    }

    static async existsByIndexId(indexId) {
        return (await ServerProxy.request("get", "collectedData",  `contents/search/existsByIndexId?indexId=${indexId}`)).data
    }
}

export default ContentProxy