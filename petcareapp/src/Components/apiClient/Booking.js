import { apiClient } from "./Axios";

const token = localStorage.getItem("token");

export const postBooking = (object) => apiClient.post('/bookingdate/post', object, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})

export const getBookingHistoryByUsername = (username) => apiClient.get(`/bookingdate/all/user/${username}`, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})