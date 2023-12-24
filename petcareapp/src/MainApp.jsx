import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './Components/Body/Home/Home';
import Service from './Components/Body/Service/Service';
import Header from './Components/Header/Header';
import Cat from './Components/Body/petInformation/Cat';
import Dog from './Components/Body/petInformation/Dog';
import Bird from './Components/Body/petInformation/Bird';
import Hamster from './Components/Body/petInformation/Hamster';
import Account from './Components/Body/account/Account';
import ProductShop from './Components/Body/Products/ProductShop';
import Login from './Components/Body/Login/Login';
import Register from './Components/Body/Login/Register';
import Footer from './Components/Footer/Footer';
import About from './Components/Body/about/About';
import AuthProvider from './Components/security/AuthContext';
import ProductDetail from './Components/Body/Products/ProductDetail';
import CartProvider from './Components/CartControl/CartProvider';
import Checkout from './Components/Body/checkout/Checkout';
import { DefaultLayout } from './Components/layout/defaultLayout';
import { SignInAndSignUpLayout } from './Components/layout/signInLayout';
import { routes } from './routes';

export default function MainApp() {
    return (
        <div>
            <AuthProvider>
                <CartProvider>
                    <BrowserRouter>
                        {/* <Header/>
                            <Routes>
                                <Route path='/' element={<Home/>}/>
                                <Route path='/Home' element={<Home/>}/>
                                <Route path='/Service' element={<Service/>}/>
                                <Route path='/Cat' element={<Cat/>}/>
                                <Route path='/Dog' element={<Dog/>}/>
                                <Route path='/Bird' element={<Bird/>}/>
                                <Route path='/Hamster' element={<Hamster/>}/>
                                <Route path='/Account' element={<Account/>}/>
                                <Route path='/Products/:type' element={<ProductShop />} />
                                <Route path='/Login' element={<Login/>}/>
                                <Route path='/Register' element={<Register/>}/>
                                <Route path='/login' element={<Login/>}/>
                                <Route path='/About' element={<About/>}/>
                                <Route path='/Product/Detail/:id' element={<ProductDetail/>}/>
                                <Route path='/Checkout' element={<Checkout/>}/>
                            </Routes> 
                            <Footer />
                            */}
                        <Routes>
                            {routes.map((route, index) => {
                                const Page = route.component;
                                let Layout = DefaultLayout;

                                if (route.layout) {
                                    Layout = route.layout;
                                } else if (route.layout === null) {
                                    Layout = DefaultLayout;
                                }

                                return (
                                    <Route
                                        key={index}
                                        path={route.path}
                                        element={
                                            <Layout>
                                                <Page />
                                                {Page}
                                            </Layout>
                                        }
                                    />
                                );
                            })}

                            {/* <Route
                               
                                path="/Home"
                                element={
                                    <DefaultLayout>
                                      
                                        <Home />
                                    </DefaultLayout>
                                }
                            /> */}
                        </Routes>
                    </BrowserRouter>
                </CartProvider>
            </AuthProvider>
        </div>
    );
}
