export class UrlPath {
    static baseUrl(){
        return 'http://localhost:8080/api/'
    }
    // return 'http://serjav.mooo.com:8000/'
    static URL = {
        WALLET: UrlPath.baseUrl() + 'wallet/',
        USER: UrlPath.baseUrl() + 'user/',
        TRANSACTIONS: UrlPath.baseUrl() + 'transactions/',
        BALANCE: UrlPath.baseUrl() + 'transactions/balance/',
        BALANCE_MIN: UrlPath.baseUrl() + 'transactions/balance_min/',
        DIVISION: UrlPath.baseUrl() + 'settle/',
        MEMBERS: UrlPath.baseUrl() + 'wallet/members/',
        CATEGORIES: UrlPath.baseUrl() + 'categories/',
        TRANSACTIONE: UrlPath.baseUrl() + 'transactions/',
        FILE: UrlPath.baseUrl() + 'file',
        
    }
}
