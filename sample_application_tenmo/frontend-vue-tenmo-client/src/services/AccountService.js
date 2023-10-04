import axios from 'axios';

export default {

  getAccountDetail() {
    return axios.get('/api/account/detail');
  },

  getAccounts() {
    return axios.get('/api/account');
  }

}
