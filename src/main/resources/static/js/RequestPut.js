import { UrlPath } from "./UrlPath.js";

export class RequestPut {
  //PUT
  static async putProprietary(walletId, proprietary) {
    const url = UrlPath.URL.WALLET + walletId + "/proprietary/" + proprietary;
    const proprietaryUpdate = {
      method: "PUT",
      body: JSON.stringify(walletId, proprietary),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestPut._putRequest(url, proprietaryUpdate);
  }

  static async putWallet(walletName, nameNew) {
    const url = UrlPath.URL.WALLET + walletName + "/" + nameNew;
    const walletPut = {
      method: "PUT",
      body: JSON.stringify({ walletName, nameNew }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestPut._putRequest(url, walletPut);
  }

  static async editTransaction(transaction) {
    const url = UrlPath.URL.TRANSACTION;
    const transactionAdd = {
      method: "PUT",
      body: JSON.stringify(transaction),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestPut._putRequest(url, transactionAdd);
  }

  static async editCategory(categoryOld, categoryNew) {
    const url = UrlPath.URL.CATEGORIES + categoryOld + "/" + categoryNew;
    const categoryEdit = {
      method: "PUT",
      body: JSON.stringify(categoryOld, categoryNew),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestPut._putRequest(url, categoryEdit);
  }

  static async putNickname(memberName, nameNew) {
    const url = UrlPath.URL.USER + "nickname/" + memberName + "/" + nameNew;
    const memberPut = {
      method: "PUT",
      body: JSON.stringify({ memberName, nameNew }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestPut._putRequest(url, memberPut);
  }

  static async putUser(memberName, nameNew) {
    const url = UrlPath.URL.USER + memberName + "/" + nameNew;
    const memberPut = {
      method: "PUT",
      body: JSON.stringify({ memberName, nameNew }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestPut._putRequest(url, memberPut);
  }

  static async putPin(pinOld, pinNew, userId) {
    const url =
      UrlPath.URL.USER + "pin/" + pinOld + "/" + pinNew + "/" + userId;
    const pinPut = {
      method: "PUT",
      body: JSON.stringify({ pinOld, pinNew, userId }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestPut._putRequest(url, pinPut);
  }

  static async shareWallet(share, walletId) {
    const url = UrlPath.URL.WALLET + "share/" + walletId + "/" + share;
    const sharePut = {
      method: "PUT",
      body: JSON.stringify({ walletId, share }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestPut._putRequest(url, sharePut);
  }

  static async addDescription(walletId, description) {
    const url =
      UrlPath.URL.WALLET + "description/" + walletId + "/" + description;
    const descriptionPut = {
      method: "PUT",
      body: JSON.stringify({ walletId, description }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestPut._putRequest(url, descriptionPut);
  }

  //REQUEST

  static async _putRequest(url, data) {
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
