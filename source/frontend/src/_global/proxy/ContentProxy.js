import ServerProxy from './ServerProxy';

class ContentProxy {
    static async searchContentOneByIndexId(indexId) {
        return (await ServerProxy.request("get", "collectedData", `contents/search/findByIndexId?indexId=${indexId}`)).data
    }

    static async isContentExistByIndexId(indexId) {
        return ((await ServerProxy.request("get", "collectedData", `contents/search/existsByIndexId?indexId=${indexId}`)).status === 200)
    }
}

export default ContentProxy