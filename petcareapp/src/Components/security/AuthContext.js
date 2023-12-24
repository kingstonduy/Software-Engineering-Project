import { createContext, useContext } from "react";
import { useState } from "react";
import { checkLogin } from "../apiClient/UserApi";



export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)


export default function AuthProvider({children}){

    
    const [isAuthenticated, setAuthenticated] = useState(false)
    const [username,setUsername] = useState('')


    async function login(user){
            try{
                const response = await checkLogin(user)
                if(response.status == 200){
                    setAuthenticated(true)
                    setUsername(user.userUserName)
                    return true;
                }
            }
            catch(error){
                return false;
            }
       
    }

    function logout(){
        setAuthenticated(false)
        setUsername(null)
    }




    return(
        <AuthContext.Provider value={{isAuthenticated, setAuthenticated, login, logout,username}}>
            {children}
        </AuthContext.Provider>
    )
}