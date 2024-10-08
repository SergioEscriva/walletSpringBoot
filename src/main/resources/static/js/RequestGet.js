import { UrlPath } from "./UrlPath.js";

export class RequestGet {
  //GET
  static async getWallets(proprietary) {
    const url = UrlPath.URL.WALLET + proprietary;
    return await RequestGet._getRequest(url);
  }

  static async getWalletToId(walletName) {
    const url = UrlPath.URL.WALLET + "id/" + walletName;
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

  static async getUserIdByName(userName) {
    const url = UrlPath.URL.USER + "name/" + userName;
    return await RequestGet._getRequest(url);
  }

  static async getUsers() {
    const url = UrlPath.URL.USER + "users";
    return await RequestGet._getRequest(url);
  }

  static async getParticipantsNameList(walletId) {
    const url = UrlPath.URL.TRANSACTION + "participants";
    return await RequestGet._getRequest(url);
  }

  static async getPinId(userId) {
    const url = UrlPath.URL.USER + "pin/" + userId;
    return await RequestGet._getRequest(url);
  }

  static async getNameById(userId) {
    const url = UrlPath.URL.USER + "id/" + userId;
    return await RequestGet._getRequest(url);
  }

  static async getCategories() {
    const url = UrlPath.URL.CATEGORIES + "categories";
    return await RequestGet._getRequest(url);
  }

  static async getCategoryById(categoryId) {
    const url = UrlPath.URL.CATEGORIES + categoryId;
    return await RequestGet._getRequest(url);
  }

  static async getCategoryByName(categoryName) {
    const url = UrlPath.URL.CATEGORIES + "name/" + categoryName;
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
