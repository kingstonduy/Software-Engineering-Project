
import axios from 'axios'

export const apiClient = axios.create(
    {
        baseURL: 'http://188.166.204.69:9000/'
    }
)
