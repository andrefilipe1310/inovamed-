import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080",
    //timeout: 10000, // Tempo limite opcional
    headers: {
        "Content-Type": "application/json",
    },
});

export default api;