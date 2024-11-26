import axios from "axios";

const apiUnauthorized = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL || "https://inovamed-latest.onrender.com",
    //timeout: 10000, // Tempo limite opcional
   
    headers: {                  
        "Access-Control-Allow-Origin": "*",
        "Access-Control-Allow-Headers": "Authorization", 
        "Access-Control-Allow-Methods": "GET, POST, OPTIONS, PUT, PATCH, DELETE" ,
        "Content-Type": "application/json;charset=UTF-8"                   
    },
    withCredentials: true
});


export default apiUnauthorized;