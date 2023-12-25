
import { apiClient, apiClientWithToken } from "./Axios";

const token = localStorage.getItem("token");

export const getProducts = (token) => {
    return apiClient.get('/products', {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
}

export const getProductDetail = (id) => apiClientWithToken.get(`/product/${id}`)

export const getProductByConstraint = (token, Constraint) => {
    return apiClient.get(`/products/constraints/${Constraint}`, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })

}

export const getProductByInStock = () => apiClient.get('/products/inStock', {
    headers: {
        'Authorization': `Bearer ${token}`
    }
})
