export class UrlPath {
  static baseUrl() {
    return "http://localhost:8080/api/";
  }
  // return 'http://serjav.mooo.com:8000/'
  static URL = {
    WALLET: UrlPath.baseUrl() + "wallet/",
    USER: UrlPath.baseUrl() + "user/",
    TRANSACTION: UrlPath.baseUrl() + "transaction/",
    BALANCE: UrlPath.baseUrl() + "transaction/balance/",
    BALANCE_MIN: UrlPath.baseUrl() + "transaction/balanceMin/",
    DIVISION: UrlPath.baseUrl() + "settle/",
    MEMBERS: UrlPath.baseUrl() + "wallet/members/",
    CATEGORIES: UrlPath.baseUrl() + "categories/",
    TRANSACTIONS: UrlPath.baseUrl() + "transaction/transactions/",
    FILE: UrlPath.baseUrl() + "file",
  };
}
