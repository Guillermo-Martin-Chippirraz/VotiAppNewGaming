import axios from "axios";

const api = axios.create({
    //Cuando HTTPS, cambiar puerto a 8443
    baseURL: "http://localhost:8080/api",
    withCredentials: false,
});

api.interceptors.request.use((config) => {
    const token = localStorage.getItem("token");

    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
});

api.interceptors.response.use(
    (response) => response,
    (error) => {
        console.error("API error:", error);

        if (error.response?.status === 401) {
            localStorage.removeItem("token");
        }
        
        return Promise.reject(error);
    }
);

export default api;