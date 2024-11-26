import axios from "axios";

const apiUnauthorized = axios.create({
    //baseURL: import.meta.env.VITE_API_BASE_URL || "https://inovamed-latest.onrender.com"
    baseURL:'http://localhost:8080',
    //timeout: 10000, // Tempo limite opcional
   
    headers: {
        "Content-Type": "application/json",
    },
    withCredentials: true
});


export default apiUnauthorized;