import axios from "axios";

const api = axios.create({
    baseURL: 'https://45wz3wojud.execute-api.us-east-1.amazonaws.com',
})

export default api;
