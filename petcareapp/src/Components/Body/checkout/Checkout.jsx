import { Link, useNavigate } from 'react-router-dom';
import { useEffect } from 'react';
import { useCart } from '../../CartControl/CartProvider';
import { useAuth } from '../../security/AuthContext';
import cs from './Checkout.module.css';
import { flushCartItemToOrderedProduct } from '../../apiClient/CartApi';

export default function Checkout() {
    const CartContext = useCart();
    const products = CartContext.cart;

    const navigation = useNavigate();

    const CompleteBuy = () => {
        CartContext.AddFromCartToOrderedProduct();
        alert('Buy successfully. Thank you for your purchase');
        navigation('/Home');
    };

    useEffect(() => {
        window.scrollTo({
            top: 0,
            left: 0,
            behavior: 'smooth',
        });
    }, []);

    return (
        <div className={cs['body']}>
            <div className={cs['header-checkout']}>
                <center>
                    <h1>Your Cart</h1>
                </center>
            </div>
            <div className="">
                <div className={cs['products-table']}>
                    <div className={cs['table-header']}>
                        <div className={`${cs['product-main-header']} ${cs['centered-text']}`}>Product</div>

                        <div className={`${cs['product-header-price']} ${cs['centered-text']}`}>Price</div>

                        <div className={`${cs['product-header-quantity']} ${cs['centered-text']}`}>Quantity</div>

                        <div className={`${cs['product-header-total']} ${cs['centered-text']}`}>Total</div>
                    </div>

                    {products.length > 0 &&
                        products.map((item, index) => {
                            return (
                                <>
                                    <div className={`${cs['table-row']} ${cs['black-line']}`}>
                                        <div className={cs['product-main-group']}>
                                            <div className={cs['avatar-img']}>
                                                <img src={item.cartDTOImageUrl} alt="" />
                                            </div>

                                            <div className={cs['name-type']}>
                                                <div className={cs['product-name']}>{item.cartDTOName}</div>
                                                <div className={cs['product-type']}>Type of product: {item.cartDTOCategory}</div>
                                            </div>
                                        </div>

                                        <div className={cs['table-col']}>
                                            <div className={cs['product-price']}>${item.cartDTOPrice}</div>
                                        </div>

                                        <div className={cs['table-col']}>
                                            <div className={cs['product-quantity']}>{item.cartDTOQuantity}</div>
                                        </div>

                                        <div className={cs['table-col']}>
                                            <div className={cs['product-price']}>
                                                ${parseFloat(item.cartDTOPrice) * parseFloat(item.cartDTOQuantity)}
                                            </div>
                                        </div>
                                    </div>
                                </>
                            );
                        })}
                    <div className={cs['ingredient-price-container']}>
                        <div className={cs['ingredient-price']}>
                            <div className={`${cs['col-3']}  ${cs['centered-text']}`}>Subtotal</div>
                            <div className={`${cs['col-4']}  ${cs['centered-text']}`}>$
                                {products.reduce(
                                    (total, item) =>
                                        total + parseFloat(item.cartDTOPrice) * parseFloat(item.cartDTOQuantity),
                                    0,
                                )}
                            </div>
                        </div>
                        <div className={cs['ingredient-price']}>
                            <div className={`${cs['col-3']}  ${cs['centered-text']}`}>Shipping</div>
                            <div className={`${cs['col-4']}  ${cs['centered-text']}`}>
                                {/* {products.reduce(
                                    (total, item) =>
                                        total + parseFloat(item.cartDTOPrice) * parseFloat(item.cartDTOQuantity),
                                    0,
                                )} */}
                                Free
                            </div>
                        </div>
                    </div>
                    <div className={cs['total-price']}>
                        <h4>Total</h4>
                        <p>
                            $
                            {products.reduce(
                                (total, item) =>
                                    total + parseFloat(item.cartDTOPrice) * parseFloat(item.cartDTOQuantity),
                                0,
                            )}
                        </p>
                    </div>
                </div>

                <div className={cs['checkout-div']}>
                    <button className={cs['checkout-btn']} onClick={CompleteBuy}>
                        Confirm
                    </button>
                </div>
            </div>
        </div>
    );
}
