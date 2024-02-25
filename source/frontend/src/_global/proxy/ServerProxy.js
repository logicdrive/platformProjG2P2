import axios from 'axios';

class ServerProxy {
    static path(service, resource) {
        return `http://${window.location.host}/api/${service}/${resource}`;
    }

    static async request(method, service, resource, data) {
        const jwtTokenValue = (localStorage.getItem("jwtToken") != null) ? `Bearer ${localStorage.getItem("jwtToken")}` : ""
        const requestHeader = {Authorization: `Bearer ${jwtTokenValue}`}

        console.log("[EFFECT] request : <method:" + method + ", service:" + service + ", resource:" + resource + ", data:" + JSON.stringify(data) + ">")
        const response = await axios({
            method: method,
            url: ServerProxy.path(service, resource),
            data: data,
            headers: requestHeader
        })
        console.log(response)

        return response
    }
}

export default ServerProxy;