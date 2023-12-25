import { apiClient } from "./Axios";
import {apiClientWithToken} from "./Axios";


const token = localStorage.getItem("token");

export const postBooking = (object) => apiClientWithToken.post('/bookingdate/post')

export const getBookingHistoryByUsername = (username) => apiClient.get(`/bookingdate/all/user/${username}`, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})