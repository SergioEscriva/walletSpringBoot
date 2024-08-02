import { UrlPath } from "./UrlPath.js";

export class RequestDel {
  //DEL
  static async delWallet(walletId) {
    const url = UrlPath.URL.WALLET + walletId;
    const memberAdd = {
      method: "DELETE",
      body: JSON.stringify({ walletId }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestDel.DelRequest(url, memberAdd);
  }

  static async delTransac(Id) {
    const url = UrlPath.URL.TRANSACTION + Id;
    const deleteTransac = {
      method: "DELETE",
      body: JSON.stringify({ Id }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestDel.DelRequest(url, deleteTransac);
  }

  static async delCategory(category) {
    const url = UrlPath.URL.CATEGORIES + category;
    const categoryDel = {
      method: "DELETE",
      body: JSON.stringify(category),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestDel.DelRequest(url, categoryDel);
  }

  static async delMember(walletId, memberName) {
    const url = UrlPath.URL.WALLET + walletId + "/member/" + memberName;
    const memberAdd = {
      method: "DELETE",
      body: JSON.stringify({ walletId, memberName }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestDel.DelRequest(url, memberAdd);
  }

  static async delUser(userId) {
    const url = UrlPath.URL.USER + userId;
    const deleteUser = {
      method: "DELETE",
      body: JSON.stringify({ userId }),
      headers: {
        "Content-type": "application/json; charset=UTF-8",
      },
    };
    return await RequestDel.DelRequest(url, deleteUser);
  }

  //REQUEST

  static async DelRequest(url, data) {
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
