import { utilFetchWrapper } from "../services/utilFetchWrapper";
const fetchWrapper = utilFetchWrapper();


export function getAccountDetail(){
  return fetchWrapper.get('/account/detail');
}

export function getAccounts() {
    return fetchWrapper.get('/account');
}