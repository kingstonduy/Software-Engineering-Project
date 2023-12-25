

import { apiClient } from "./Axios"

const token = localStorage.getItem("token");


export const checkLogin = (user) => apiClient.post('/login', user)
export const checkRegister = (user) => apiClient.post('/register', user)
//OTP
export const verifyOTP = (OTP) => apiClient.post('/verify', OTP)
export const resendOTP = (user) => apiClient.post('/resendOtp', user)


export const getUsers = () => apiClient.get('/jpa/users', {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})
export const getUserByUsername = (username) => apiClient.get(`/users/${username}`, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})
export const getOrderedProduct = (username) => apiClient.get(`/users/${username}/orederedProducts`, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})
export const getUserInformation = (username) => apiClient.get(`/users/${username}`, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})
export const getUserChangeInformation = (username) => apiClient.get(`/user/userChangeInformation/${username}`, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})
export const changeUserInformation = (user) => apiClient.put('/user/userChangeInformation/update', user, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})
