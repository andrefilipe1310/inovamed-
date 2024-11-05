import axios from "axios";



const api = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL || "http://localhost:8080",
    //timeout: 10000, // Tempo limite opcional
   
    headers: {
        "Content-Type": "application/json",
    },
});
if(localStorage.getItem("token") != null && localStorage.getItem("token") !== undefined){
    api.defaults.headers.common['Authorization'] =`Bearer ${localStorage.getItem('token')}`;
}


export default api;