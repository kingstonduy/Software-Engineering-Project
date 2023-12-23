import { useEffect, useState } from 'react';
import { getOrderedProduct, getUserInformation } from '../../apiClient/UserApi';
import { useAuth } from '../../security/AuthContext';
import cs from './Account.module.css';
import LogoIU from '../../../assests/accountpage/logoIU.png';
import { Link, useNavigate } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faUser } from '@fortawesome/free-solid-svg-icons';
import { CommentRating } from '../../HiddenWrapContainer/CommentRating';
import { OrderItem } from './OrderItem';
import { Button, List } from 'antd';
import { SearchBar } from './SearchBar';
import BookingHistory from '../../HiddenWrapContainer/BookingHistory';
import UserChangeInformation from '../../HiddenWrapContainer/UserChangeInformation';

import { useCookies } from 'react-cookie';

const count = 3;
const Account = () => {
    const [initLoading, setInitLoading] = useState(true);
    const [loading, setLoading] = useState(false);
    const [data, setData] = useState([]);
    const [list, setList] = useState([]);
    const authContext = useAuth();
    const navigate = useNavigate();
    const [user, setUser] = useState('');
    const [wordEntered, setWordEntered] = useState('');
    const [open, isOpen] = useState(false);
    const [openChange, isOpenChange] = useState(false);

    const [cookies, setCookie, removeCookie] = useCookies();

    function handleOpenBookingTrue() {
        isOpen(true);
    }

    function handleOpenBookingFalse() {
        isOpen(false);
    }

    function handleOpenChangeTrue() {
        isOpenChange(true);
    }

    function handleOpenChangeFalse() {
        isOpenChange(false);
    }

    function handleLogout() {
        removeCookie('username');
        removeCookie('password');
        authContext.logout();
        navigate('/login');
    }

    useEffect(() => {
        window.scrollTo({
            top: 0,
            left: 0,
            behavior: 'smooth',
        });
        retrieveOrderHistory();
        retrieveUserInformation();
    }, []); // Run once on component mount

    async function retrieveUserInformation() {
        try {
            const response = await getUserInformation(authContext.username);
            setUser(response.data);
        } catch (error) {
            console.error('Failed to retrieve user information:', error);
        }
    }

    async function retrieveOrderHistory() {
        try {
            const response = await getOrderedProduct(authContext.username);
            setInitLoading(false);
            setData(response.data);
            setList(response.data.slice(0, count)); // Initialize the list with the first three items
        } catch (error) {
            console.error('Failed to retrieve order history:', error);
        }
    }

    const onLoadMore = () => {
        setLoading(true);
        const nextItems = data.slice(list.length, list.length + 3); // Get the next three items
        setList((prevList) => [...prevList, ...nextItems]); // Append the next items to the current list
        setLoading(false);
    };

    const loadMore =
        !initLoading && !loading && list.length > 0 && wordEntered === '' && list.length < data.length ? (
            <div className={cs['load-more-btn-container']}>
                <Button className={cs['load-more-btn']} onClick={onLoadMore}>
                    Load more
                </Button>
            </div>
        ) : null;

    function handleSearching() {
        alert('Clicked searching');
    }

    function handleChangeInformation() {
        alert('Clicked change information');
    }

    return (
        <>
            <div className={cs['wrapper']}>
                <div className={cs['info-container']}>
                    <div className={cs['user-info']}>
                        <img src={LogoIU} alt="" />
                        <div>
                            <h4>{user.userFullName}</h4>
                            <p>{user.userEmail}</p>
                        </div>
                    </div>
                    <div className={cs['user-interaction']}>
                        <button onClick={handleOpenBookingTrue} className={cs['booking-history-btn']}>
                            Booking History
                        </button>
                        <button className={cs['change-password-btn']} onClick={handleOpenChangeTrue}>
                            Change password
                        </button>
                    </div>
                </div>
                <SearchBar
                    setData={setList}
                    originalData={data}
                    wordEntered={wordEntered}
                    setWordEntered={setWordEntered}
                />

                <h4 className={cs['puschase-history']}>Purchase History</h4>

                <div className={cs['products-table']}>
                    <div className={cs['table-header']}>
                        <div className={`${cs['product-main-header']} ${cs['centered-text']}`}>Product</div>
                        <div className={`${cs['product-header-price']} ${cs['centered-text']}`}>Price</div>
                        <div className={`${cs['product-header-quantity']} ${cs['centered-text']}`}>Quantity</div>
                        <div className={`${cs['product-header-total']} ${cs['centered-text']}`}>Total</div>
                    </div>

                    {
                        <List
                            loading={initLoading}
                            itemLayout="horizontal"
                            loadMore={loadMore}
                            dataSource={list}
                            renderItem={(item, index) => {
                                return (
                                    <List.Item style={{ borderBlockEnd: 'none' }}>
                                        <OrderItem item={item} />
                                    </List.Item>
                                );
                            }}
                        />
                    }
                </div>
                {open && <BookingHistory handleOpenBookingFalse={handleOpenBookingFalse} />}
            </div>
            {openChange && (
                <UserChangeInformation
                    retrieveUserInformation={retrieveUserInformation}
                    handleOpenChangeFalse={handleOpenChangeFalse}
                />
            )}
        </>
    );
};

//sth
export default Account;
