import { utilFetchWrapper } from "../services/utilFetchWrapper";
const fetchWrapper = utilFetchWrapper();


export function getTransfers(){
  return fetchWrapper.get('/transfer');
}

export function getTransfer(id){
  return fetchWrapper.get(`/transfer/${id}`);
}
export function getPendingTransfers(){
  return fetchWrapper.get('/pending/transfer');
}
export function saveTransfer(transfer){
  return fetchWrapper.post('/transfer', transfer);
}
export function updateTransfer(id, transfer){
  return fetchWrapper.put(`/transfer/${id}`, transfer);
}
  
