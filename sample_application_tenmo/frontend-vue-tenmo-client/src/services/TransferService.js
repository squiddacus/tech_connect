import axios from 'axios';

export default {
  getTransfers() {
    return axios.get('/api/transfer');
  },

  getTransfer(id) {
    return axios.get(`/api/transfer/${id}`);
  },

  getPendingTransfers() {
    return axios.get('/api/pending/transfer');
  },

  saveTransfer(transfer){
    return axios.post('/api/transfer', transfer);
  },
  

  updateTransfer(id, transfer) {
    return axios.put(`/api/transfer/${id}`, transfer);
  },

}

