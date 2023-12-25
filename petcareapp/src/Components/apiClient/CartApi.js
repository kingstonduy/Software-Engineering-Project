
import { apiClient } from "./Axios";

const token = localStorage.getItem("token");

export const getProductOnCart = (username) => apiClient.get(`/cart/getItemOnCart/${username}`, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})
export const updateQuantityCartItem = (cartDTO) => apiClient.put('/cart/update/quantity', cartDTO, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})
export const deleteCartItem = (cartDTOId) => apiClient.delete(`/cart/${cartDTOId}`, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})
export const addProductOnCart = (addToCartRequest) => apiClient.post('/cart', addToCartRequest, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})
export const flushCartItemToOrderedProduct = (cartDTOList) => apiClient.patch('/orderedproduct/checkout', cartDTOList, {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})

