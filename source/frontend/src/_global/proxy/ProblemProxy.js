import ServerProxy from './ServerProxy';

class ProblemProxy {
    static async generateProblem(indexId, query) {
        await ServerProxy.request("put", "problem", "problems/generateProblem", {
            "indexId": indexId,
            "query": query
        })
    }


    static async searchProblemAllByIndexId(indexId) {
        return (await ServerProxy.request("get", "collectedData", `problems/search/findByIndexIdOrderByPriority?indexId=${indexId}&page=0&size=1000`)).data
    }
}

export default ProblemProxy