import axios from "axios";
import { openNotification } from "../App";

const api = axios.create({
    baseURL: "http://localhost:8080",
})

api.interceptors.request.use(config => {

    const token = localStorage.getItem("token");

    if(token && config.headers){
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config;

})

api.interceptors.response.use(function onFulfilled(response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
  
    return response;
  }, function onRejected(error) {
    if(error.status === 401){
        localStorage.clear();
        window.location.href = "/users/login";
        openNotification("error","Token expired")
    }
    return Promise.reject(error);
  });
export default api;