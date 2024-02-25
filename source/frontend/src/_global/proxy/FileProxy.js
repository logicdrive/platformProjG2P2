import ServerProxy from './ServerProxy';

class FileProxy {
    static async searchFileOneByFileId(fileId) {
        return (await ServerProxy.request("get", "collectedData", `files/search/findByFileId?fileId=${fileId}`)).data
    }
}

export default FileProxy