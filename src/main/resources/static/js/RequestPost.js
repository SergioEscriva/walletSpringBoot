import { UrlPath } from "./UrlPath.js";

export class RequestPost {
  //POST
  static async postTransaction(transaction) {
    const url = UrlPath.URL.TRANSACTION + "add";
    //console.log(transaction)
    const transactionAdd = {
      method: "POST",
      body: JSON.stringify(transaction),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestPost._postRequest(url, transactionAdd);
  }

  static async postCategory(newCategory) {
    const url = UrlPath.URL.CATEGORIES + newCategory;
    const categoryAdd = {
      method: "POST",
      body: JSON.stringify({ newCategory }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestPost._postRequest(url, categoryAdd);
  }

  static async postUser(newUser, pin) {
    const url = UrlPath.URL.USER + newUser + "/" + pin;
    const userAdd = {
      method: "POST",
      body: JSON.stringify(newUser, pin),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestPost._postRequest(url, userAdd);
  }

  static async postMember(walletId, memberName, pin) {
    const url =
      UrlPath.URL.WALLET + walletId + "/member/" + memberName + "/" + pin;
    const memberAdd = {
      method: "POST",
      body: JSON.stringify({ walletId, memberName }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestPost._postRequest(url, memberAdd);
  }

  static async postWallet(walletName, proprietary) {
    const url = UrlPath.URL.WALLET + walletName + "/" + proprietary;
    const walletAdd = {
      method: "POST",
      body: JSON.stringify({ walletName, proprietary }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestPost._postRequest(url, walletAdd);
  }

  static async postFile(file) {
    const url = UrlPath.URL.FILE;
    let response = {
      method: "POST",
      body: file,
    };
    return await RequestPost._postRequest(url, response);
  }

  //REQUEST

  static async _postRequest(url, data) {
    try {
      const response = await fetch(url, data);
      const jsonMessage = await response.json();
      return jsonMessage;
    } catch (error) {
      console.log(error);
      return null;
    }
  }
}
