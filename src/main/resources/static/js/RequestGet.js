import { UrlPath } from "./UrlPath.js";

export class RequestGet {
  //GET
  static async getWallets(proprietary) {
    const url = UrlPath.URL.WALLET + proprietary;
    return await RequestGet._getRequest(url);
  }

  static async getWalletToId(wallet_name) {
    const url = UrlPath.URL.WALLET + "id/" + wallet_name;
    return await RequestGet._getRequest(url);
  }

  static async getTransactions(walletId) {
    const url = UrlPath.URL.TRANSACTIONS + walletId;
    return await RequestGet._getRequest(url);
  }

  static async getTransaction(Id) {
    const url = UrlPath.URL.TRANSACTION + Id;
    return await RequestGet._getRequest(url);
  }

  static async getBalance(walletId) {
    const url = UrlPath.URL.BALANCE + walletId;
    return await RequestGet._getRequest(url);
  }

  static async getBalanceMin(walletId) {
    const url = UrlPath.URL.BALANCE_MIN + walletId;
    return await RequestGet._getRequest(url);
  }

  static async getDivision(walletId) {
    const url = UrlPath.URL.DIVISION + walletId;
    return await RequestGet._getRequest(url);
  }

  static async getMembers(walletId) {
    const url = UrlPath.URL.MEMBERS + walletId;
    return await RequestGet._getRequest(url);
  }

  static async getUserId(user_name) {
    const url = UrlPath.URL.USER + "name/" + user_name;
    return await RequestGet._getRequest(url);
  }

  static async getUsers() {
    const url = UrlPath.URL.USER + "users";
    return await RequestGet._getRequest(url);
  }

  static async getPinId(userId) {
    const url = UrlPath.URL.USER + "pin/" + userId;
    return await RequestGet._getRequest(url);
  }

  static async getIdName(userId) {
    const url = UrlPath.URL.USER + "id/" + userId;
    return await RequestGet._getRequest(url);
  }

  static async getCategories() {
    const url = UrlPath.URL.CATEGORIES + "categories";
    return await RequestGet._getRequest(url);
  }

  //REQUEST

  static async _getRequest(url) {
    try {
      const response = await fetch(url);
      const jsonMessage = await response.json();
      //console.log(jsonMessage)
      return jsonMessage;
    } catch (error) {
      console.log(error);
      return null;
    }
  }
}
