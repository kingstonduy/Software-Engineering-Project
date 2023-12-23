
import axios from 'axios'

export const apiClient = axios.create(
    {
        baseURL: 'http://146.190.195.162/is/v1'
    }
)
