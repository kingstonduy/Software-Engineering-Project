import { apiClient } from "./Axios";

const token = localStorage.getItem("token");


export const getCommentByProductId = (id) => apiClient.get(`/comments/product/${id}`, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})
export const postComment = (comment) => apiClient.post(`/comment/user/product`, comment, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})